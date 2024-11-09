package org.example;

import jakarta.jms.Connection;
import jakarta.jms.Destination;
import jakarta.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jndi.ActiveMQInitialContextFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class Broker {
    public static Properties props = new Properties();
    public static String url ; //"tcp://192.168.56.101:61616";
    protected static String queue = "queue_test_in";
    protected static ActiveMQConnectionFactory connectionFactory;
    protected static Connection connection;
//    public Session ses;
    public Broker() throws jakarta.jms.JMSException{
        connectionFactory =new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();

    }
    protected void startConnection()throws jakarta.jms.JMSException{
        connection.start();
    }
    protected Session createSession() throws jakarta.jms.JMSException{

        return connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
    }
    protected Destination createQueue(Session session) throws jakarta.jms.JMSException{

        return session.createQueue(queue);
    }
    protected void stopConnection()throws jakarta.jms.JMSException{
        //ses.close();
        connection.stop();

    }
//    protected Destination prepToUse()throws jakarta.jms.JMSException{
//        startConnection();
//        ses = createSession();
//        return createQueue(ses);
//
//    }


}
