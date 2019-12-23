pipeline
{

    agent
    {
        node
        {
            label "maven"
        }
    }

    parameters
    {
        string(name: 'PROJECT_NAME', defaultValue: 'apps' ,description: 'What is the project name?')
    }

    environment
    {
        def version = ""
        def artifactId = ""
        def rollout = true
    }

    stages
    {
    	stage("Get ArtifactID and Version")
        {
            steps
            {
                script
                {
                    version = readMavenPom().getVersion();
                    echo "Version ::: ${version}"

                    artifactId = readMavenPom().getArtifactId();
                    echo "artifactId ::: ${artifactId}"

                }
            }
        }

        stage("Build Project")
        {
            steps
            {
                script
                {
                    withMaven(mavenSettingsConfig: "maven-settings") {
                        sh "mvn clean package"
                    }
                }
            }
        }
        stage('Create Image Builder')
        {
            steps
            {
                script
                {
                    openshift.withCluster()
                    {
                        openshift.withProject("${params.PROJECT_NAME}")
                        {
                            echo "Using project: ${openshift.project()}"
                            if (!openshift.selector("bc", "${artifactId}").exists())
                            {
                                openshift.newBuild("--name=${artifactId}", "--image-stream=openshift/fis-java-openshift:2.0", "--binary")
                            }
                        }
                    }
                }
            }
        }

        stage('Start Build Image')
        {
            steps
            {
                script
                {
                    openshift.withCluster()
                    {
                        openshift.withProject("${params.PROJECT_NAME}")
                        {
                            echo "Using project: ${openshift.project()}"
                            openshift.selector("bc", "${artifactId}").startBuild("--from-file=target/--from-file=target/${artifactId}-${version}.jar", "--wait=true")
                        }
                    }
                }
            }
        }
    }
}