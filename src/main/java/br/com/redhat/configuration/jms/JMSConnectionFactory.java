package br.com.redhat.configuration.jms;

;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JMSConnectionFactory {

    public PooledConnectionFactory getPooledConnctionFactory(final String name, final String broker, final String user,
                                                             final String pwd, final Integer jmsMaxPooledConnections) throws Exception {

        ActiveMQConnectionFactory activeMQConnectionFactory = ActiveMQJMSClient.createConnectionFactory(broker, name);
        activeMQConnectionFactory.setUser(user);
        activeMQConnectionFactory.setPassword(pwd);

        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(jmsMaxPooledConnections);

        return pooledConnectionFactory;
    }
}
