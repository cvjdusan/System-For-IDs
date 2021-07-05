/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Dusan
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.Documentrequest;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import entity.Documentrequest;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dusan
 */
public class Main {
    private EntityManager ent;
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "queueDoc1")
    private static Queue queue;
    
    public Main() throws JMSException, MalformedURLException, IOException{
           get();       
    }
    
    public void get() throws JMSException, MalformedURLException, IOException{
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServerPU");
       ent = emf.createEntityManager();
       JMSContext context = connectionFactory.createContext();
       JMSConsumer consumer = context.createConsumer(queue);

       
      while(true){
        ObjectMessage msg = (ObjectMessage) consumer.receive();
        if(msg instanceof ObjectMessage) {
        Documentrequest req = (Documentrequest) msg.getObject();
        
         System.out.println("Dobio sam " + " id: " + req.getId() +" " +req.getIme() + " " + req.getPrezime());

        URL url = new URL("http://collabnet.netset.rs:8081/is/persoCentar/submit");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        
        con.setDoOutput(true);

        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.writeBytes(convertJSON(req).toString());
            out.flush();
            out.close();
        }

            System.out.println(con.getResponseCode());
            System.out.println(convertJSON(req).toString());

         
        if(con.getResponseCode() == 200) {
            req.setStatus("uProdukciji");
            System.out.println(con.getResponseCode());
            System.out.println(convertJSON(req).toString());
            ent.getTransaction().begin();
            ent.merge(req);
            ent.flush();
            ent.getTransaction().commit();
       } else if(con.getResponseCode() == 400) {
            System.out.println("Greska " + con.getResponseCode());
            ObjectMessage m = context.createObjectMessage(req);
            JMSProducer producer = context.createProducer();
            producer.send(queue, m);
       }         
      }
      }
    }
    
    private Object convertJSON(Documentrequest req) {
       JSONObject obj = new JSONObject();
       
       obj.put("id", req.getId());
       obj.put("JMBG", req.getJmbg());
       obj.put("ime", req.getIme());
       obj.put("prezime", req.getPrezime());
       obj.put("imeMajke", req.getImeMajke());
       obj.put("imeOca", req.getImeOca());
       obj.put("prezimeMajke", req.getPrezimeMajke());
       obj.put("prezimeOca", req.getPrezimeOca());
       obj.put("pol", req.getPol());
       obj.put("datumRodjenja", req.getDatumRodjenja());
       obj.put("nacionalnost", req.getNacionalnost());
       obj.put("profesija", req.getProfesija());
       obj.put("bracnoStanje", req.getBracnoStanje());
       obj.put("opstinaPrebivalista", req.getOpstinaPrebivalista());
       obj.put("ulicaPrebivalista", req.getUlicaPrebivalista());
       obj.put("brojPrebivalista", req.getBrojPrebivalista());
       obj.put("status", req.getStatus());
       
       return obj;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JMSException, MalformedURLException, IOException {
        new Main();
    }

}
