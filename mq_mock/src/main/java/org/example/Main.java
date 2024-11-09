package org.example;


import  javax.jms.ConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.Destination;
import  jakarta.jms.MessageProducer;
import  jakarta.jms.Session;
import  jakarta.jms.TextMessage;
import  org.apache.activemq.ActiveMQConnectionFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) throws jakarta.jms.JMSException, InterruptedException, IOException {

    Broker.props.load(new FileInputStream("resources.properties"));
    Broker.url= Broker.props.getProperty("ProviderUrl");
   Consumer cons = new Consumer();
    cons.multiReceiveMessageNotExit(5);

//        System.exit(0);

    }
}