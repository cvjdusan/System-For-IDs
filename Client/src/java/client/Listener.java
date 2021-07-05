/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import entity.Documentrequest;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dusan
 */


public class Listener extends Thread{    
    public JMSConsumer consumer;
    public Topic topic;
    public Main main;
    public Documentrequest req2;
    
    public Listener(JMSConsumer con, Topic t, Main main, Documentrequest req2){
        System.out.println("USAO");
        topic = t; consumer = con;
        this.main = main;
        this.req2 = req2;
        this.start();
    }

    
    @Override
    public void run(){
      while(true){
            Message msg=consumer.receive();
            if(msg instanceof TextMessage){
                TextMessage txtmsg=(TextMessage)msg;
                try {
                    try {
                        if(req2.getStatus().equals("uProdukciji"))
                            main.refresh(req2);
                    } catch (IOException ex) {
                        Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.print(txtmsg.getText());
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        JMSContext context=conn.createContext();
        JMSConsumer consumer=context.createConsumer(queue);
        while(true){
            Message msg=consumer.receive();
            if(msg instanceof TextMessage){
                TextMessage txtmsg=(TextMessage)msg;
                try {
                    System.out.print(txtmsg.getText());
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
*/
    
}