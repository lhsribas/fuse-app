package br.com.redhat.configuration.jms;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JMSConfigComponent {

    @Value("${amq.user}")
    private String user;

    @Value("${amq.pwd}")
    private String pwd;

    @Value("${amq.host}")
    private String host;

    @Autowired
    private JMSConnectionFactory jmsConnectionFactory;

    @Bean("jmsSmartStart")
    public JmsComponent jmsStartConnection() {
        try {

            PooledConnectionFactory pool =
                    jmsConnectionFactory.getPooledConnctionFactory("jmsComponent", host, user, pwd, 10);

            JmsConfiguration jmsConfiguration = new JmsConfiguration();
            jmsConfiguration.setConnectionFactory(pool);

            return new JmsComponent(jmsConfiguration);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
