/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.timermaven;

/**
 *
 * @author Dusan
 */
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@Stateless
public class Timer {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public ConnectionFactory conn;
        
    @Resource(lookup = "topicDoc")
    public Topic topic;
    
    public JMSContext context;
    public JMSProducer producer;
    
    public int vr=0;
    
    @Schedule(second="*/10", minute = "*",  hour = "*")

    public void test(){
        if(conn!=null && topic!=null){
            if(context==null){
                context=conn.createContext();
                producer=context.createProducer();
                System.out.println("Kreiran producer");
            }
           
            TextMessage txtMsg=context.createTextMessage("Test "+vr);
            producer.send(topic, txtMsg);
            vr++;
            System.out.println("timer");
        }
    }
}
