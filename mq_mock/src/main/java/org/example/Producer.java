package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;

public class Producer extends Broker{
    Producer() throws jakarta.jms.JMSException {
        super();
    }
    public void sendMessage(String message)throws jakarta.jms.JMSException{

        startConnection();
        Session ses = createSession();
        Destination dest = createQueue(ses);
        MessageProducer prod = ses.createProducer(dest);
        TextMessage mess = ses.createTextMessage(message);
        prod.send(mess);
        System.out.println(mess.getText());
        ses.close();
        stopConnection();

    }

    public static void main(String[] args) throws jakarta.jms.JMSException, InterruptedException {

        Producer prod = new Producer();
        for (int i=0;i<500;i++){
        prod.sendMessage("kuuk"+ i);
    }
        System.exit(0);
    }

}


