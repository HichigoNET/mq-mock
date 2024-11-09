package org.example;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Consumer extends Broker{
    Consumer() throws jakarta.jms.JMSException {
        super();
    }
    public String receiveMessage()throws jakarta.jms.JMSException, InterruptedException{

        startConnection();
        Session ses = createSession();
        Destination dest = createQueue(ses);
        MessageConsumer consumer = ses.createConsumer(dest);
        jakarta.jms.Message mess = consumer.receive();
        if (mess instanceof TextMessage){
            TextMessage textMessage = (TextMessage) mess;
            super.stopConnection();
            return textMessage.getText();
        }
        else {
            TimeUnit.SECONDS.wait(3);
            return receiveMessage();
        }
    }
    public void receiveMessageNotExit()throws jakarta.jms.JMSException, InterruptedException{

        startConnection();
        Session ses = createSession();
        Destination dest = createQueue(ses);
        MessageConsumer consumer = ses.createConsumer(dest);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println( ((TextMessage)message).getText().toString());
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }

            }
        });
//        consumer.receive();
//        jakarta.jms.Message mess = consumer.receive();
//        if (mess instanceof TextMessage){
//            TextMessage textMessage = (TextMessage) mess;
//            super.stopConnection();
//            System.out.println(textMessage.getText());
//        }
    }
//    public void multiReceiveMessageNotExit(int threadCount)throws jakarta.jms.JMSException, InterruptedException{
//
//        startConnection();
//        Session ses = createSession();
//        Destination dest = createQueue(ses);
//        MessageConsumer consumer = ses.createConsumer(dest);
//        consumer.setMessageListener(message -> {
//            Executors.newFixedThreadPool(threadCount).execute(() -> {
//                    try {
//                                System.out.println(((TextMessage) message).getText().toString());
//                                TimeUnit.SECONDS.sleep(5);
//                                //wait(5000);
//
//                        }
//                     catch (JMSException | InterruptedException ex){
//                        throw new RuntimeException(ex);
//                    }
//            }
//            );
//
//
//        }
//        );




//    }
//
//}
//    public void multiReceiveMessageNotExit(int threadCount)throws jakarta.jms.JMSException, InterruptedException {
//        ExecutorService serv = Executors.newFixedThreadPool(threadCount);
//        startConnection();
//
//        for (int i = 0; i < threadCount; i++) {
//            serv.execute(() -> {
//                        try {
//                            Session ses = createSession();
//                            Destination dest = createQueue(ses);
//                            MessageConsumer consumer = ses.createConsumer(dest);
//                            consumer.setMessageListener(message -> {
//                                try {
//                                    System.out.println(((TextMessage) message).getText().toString());
//                                    TimeUnit.SECONDS.sleep(5);
////                                    Thread.sleep(5000);
//                                    //wait(5000);
//                                } catch (JMSException | InterruptedException e) {
//                                    throw new RuntimeException(e);
//                                }
//
//                            });
//                        } catch (JMSException ex) {
//                            throw new RuntimeException(ex);
//                        }
//
//                    }
//            );
//
//
////        consumer.receive();
////        jakarta.jms.Message mess = consumer.receive();
////        if (mess instanceof TextMessage){
////            TextMessage textMessage = (TextMessage) mess;
////            super.stopConnection();
////            System.out.println(textMessage.getText());
////        }
//        }
//    }
//
//}
    public void multiReceiveMessageNotExit(int threadCount)throws jakarta.jms.JMSException, InterruptedException {
        ExecutorService serv = Executors.newFixedThreadPool(threadCount);
        startConnection();

//        for (int i = 0; i < threadCount; i++) {
            for (;;) {
            serv.execute(() -> {
    try {
        Session ses = createSession();
        Destination dest = createQueue(ses);
        MessageConsumer consumer = ses.createConsumer(dest);
        consumer.receive();
        jakarta.jms.Message mess = consumer.receive();
        if (mess instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) mess;
            System.out.println(textMessage.getText());
            consumer.close();
            ses.close();
            TimeUnit.SECONDS.sleep(5);

        }
    }
    catch (JMSException | InterruptedException ex){
        throw new RuntimeException(ex);
    }
        });
        }
    }

}