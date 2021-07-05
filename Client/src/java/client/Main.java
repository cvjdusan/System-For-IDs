/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import entity.Documentrequest;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSConsumer;
import javax.jms.Topic;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.ws.rs.core.HttpHeaders;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.eclipse.persistence.config.QueryHints;
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
    @Resource(lookup = "queueDoc")
    private static Queue queue;
    @Resource(lookup = "topicDoc")
    public static Topic topic;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClientPU");       
    JMSContext context = connectionFactory.createContext();
    JMSProducer producer = context.createProducer();
    
    private static JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private Documentrequest req;
    JTextField jmbg = new JTextField("3030303030303");
    JTextField name = new JTextField("levo");
    JTextField surname = new JTextField("desno");
    JTextField motherName = new JTextField("aha");
    JTextField fatherName = new JTextField("sda");
    JTextField motherSurname = new JTextField("fff");
    JTextField fatherSurname = new JTextField("fff");
    JTextField gender = new JTextField("yolo");
    JTextField birth = new JTextField("1997-11-30");
    JTextField nat = new JTextField("srbenda");
    JTextField prof = new JTextField("programer");
    JTextField place = new JTextField("Holawola");
    JTextField street = new JTextField("zeam");
    JTextField stNum = new JTextField("MMMM1");
    
    JTextField checkId = new JTextField("171690000000");
    JLabel showObj = new JLabel();
    
    JCheckBox ch1 = new JCheckBox("ozenjen/Udata");
    JCheckBox ch2 = new JCheckBox("neozenjen/a");
    JCheckBox ch3 = new JCheckBox("udovac/Udovica");
    JCheckBox ch4 = new JCheckBox("razveden/a");
    ButtonGroup bg = new ButtonGroup();
    
    JLabel timeOk = new JLabel();
    
    private JButton refresh = new JButton("Osvezi");
    private JButton evident = new JButton("Uruci");
    JMSConsumer consumer;
    private Listener listener;
        
    public Main() throws IOException, ParseException{
       ent = emf.createEntityManager();
       ch1.setSelected(true);
       
       consumer = context.createConsumer(topic);
              
       initFrame();

       frame.addWindowListener(new WindowAdapter(){
       public void windowClosing(WindowEvent e){
            frame.dispose();
           }
       });
    }
    
    void send(){ 
       ObjectMessage object = context.createObjectMessage(req);
       
       ent.getTransaction().begin();
       ent.persist(req);
       ent.flush();
       ent.getTransaction().commit();
       
       System.out.println("Poslao u bazu");
      
       producer.send(queue, object);
       
       System.out.println("Poslao u red " + req.getId());
      
    }
    
    int checkTime() throws IOException, ParseException{
        final String USER_AGENT = "Mozilla/5.0";
        String GET_URL = "http://collabnet.netset.rs:8081/is/terminCentar/checkTimeslotAvailability";
	String GET_URL2 = "http://collabnet.netset.rs:8081/is/terminCentar/getAvailableTimeslots?regionalniCentarId=17169&dan=2020-02-24";
        String regCen = "?regionalniCentarId=";
        String term = "&termin=";
        //String day = "&dan=";
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();  
        
        String date = dtf.format(now);
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        now = LocalDateTime.now(); 
        date += "T"+dtf.format(now);
        
        
        System.out.println(date);  
        
        GET_URL += regCen + "17169";
        GET_URL += term + date;
              //  "2020-02-10T16:45:00";
              
                      System.out.println(GET_URL);  
                
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
	con.setRequestProperty("User-Agent", USER_AGENT);
	int responseCode = con.getResponseCode();
	System.out.println("GET Response Code :: " + responseCode);
	if (responseCode == HttpURLConnection.HTTP_OK) { // success
            JSONParser parse = new JSONParser();
            BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

            
              //JSONArray jArray = (JSONArray) parse.parse(in);
              JSONObject jObject = (JSONObject) parse.parse(in);  
              in.close();
              
              String avail = jObject.get("dostupnost").toString();
              String msg = jObject.get("poruka").toString();
              
              System.out.println(avail);
              System.out.println(msg);
              
              if(avail == "true"){
                  timeOk.setText("                " + msg);
                  return 1;
              }
              else {
                   timeOk.setText("                " + msg);
                   return 0;
              }
              
         /*   
           for(int i = 0; i < jArray.size(); i++){
                JSONObject k = (JSONObject) jArray.get(i);
                String timeTo = k.get("periodDo").toString();
                String timeFrom = k.get("periodOd").toString();
                System.out.println("Termin " + i + " " + timeTo + " " + timeFrom);
            } 
            */
            
          //  StringBuffer response = new StringBuffer();

            //while ((inputLine = in.readLine()) != null) {
		//response.append(inputLine);
                
          }
        
        
	//in.close();

	//System.out.println(response.toString());
	//	} else {
	//System.out.println("GET request not worked");
	//}
        return 0;
    }
    
    
    void initFrame(){
        frame = new JFrame();
        JPanel main = new JPanel(new BorderLayout());
        
        
        JTabbedPane tabbedPane = new JTabbedPane();

        panel1 = new JPanel(new GridLayout(17, 2));
        panel2 = new JPanel(new BorderLayout());
               
        panel1.setPreferredSize(new Dimension(300, 500));
        
        initPanel1();
        initPanel2();
        
        tabbedPane.add("Nov zahtev", panel1);
        tabbedPane.add("Provera zahteva", panel2);
        frame.add(tabbedPane);      
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    private void initPanel1(){       
       JLabel l = new JLabel();
       l.setText("Ime");
       panel1.add(l);
       panel1.add(name);

       l = new JLabel();
       l.setText("Prezime");
       panel1.add(l);
       panel1.add(surname);
       
       l = new JLabel("JMBG");
       panel1.add(l);
       panel1.add(jmbg);

       l = new JLabel();
       l.setText("Ime majke");
       panel1.add(l);
       panel1.add(motherName);
        
       l = new JLabel();
       l.setText("Ime oca");
       panel1.add(l);
       panel1.add(fatherName);
        
       l = new JLabel("Prezime majke");
       panel1.add(l);
       panel1.add(motherSurname);
        
       l = new JLabel("Prezime oca");
       panel1.add(l);
       panel1.add(fatherSurname);
        
       l = new JLabel("Pol");
       panel1.add(l);
       panel1.add(gender);
        

       l = new JLabel("gggg-mm-dd");
       panel1.add(l);
       panel1.add(birth);

       l = new JLabel("Nacionalnost");
       panel1.add(l);
       panel1.add(nat);
        
        
       l = new JLabel("Profesija");
       panel1.add(l);
       panel1.add(prof);
        
       l = new JLabel("Prebivaliste");
       panel1.add(l);
       panel1.add(place);
        
       l = new JLabel("Ulica");
       panel1.add(l);
       panel1.add(street);

       l = new JLabel("Broj");
       panel1.add(l);
       panel1.add(stNum);
              
       bg.add(ch1);
       bg.add(ch2);
       bg.add(ch3);
       bg.add(ch4);
       
       
       panel1.add(ch1);
       panel1.add(ch2);
       panel1.add(ch3);
       panel1.add(ch4);
        
       JButton save = new JButton("Save");
        
       save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonPressed();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
       
        
        panel1.add(save);
        panel1.add(timeOk);
    }
    
    private void buttonPressed() throws IOException, ParseException{
       req = new Documentrequest(); 
       
       if(checkEmpty() == false) {
       Long count = (Long)ent.createQuery("SELECT COUNT(u) from Documentrequest u").getSingleResult();
       Long id;
       
       if(count == 0){
           String idd = "171690000000";
           id = Long.parseLong(idd); 
       }else{
         id = (Long)ent.createQuery("select max(u.id) from Documentrequest u").getSingleResult();
       }
     
       id += 1;
       
      // System.out.println("POSLATO ID JE " + id);
      
       req.setId(id);
       req.setJmbg(jmbg.getText());
       req.setIme(name.getText());
       req.setPrezime(surname.getText());
       req.setImeMajke(motherName.getText());
       req.setImeOca(fatherName.getText());
       req.setPrezimeMajke(motherSurname.getText());
       req.setPrezimeOca(fatherSurname.getText());
       req.setPol(gender.getText());
       req.setDatumRodjenja(birth.getText());
       req.setNacionalnost(nat.getText());
       req.setProfesija(prof.getText());
       req.setOpstinaPrebivalista(place.getText());
       req.setUlicaPrebivalista(street.getText());
       req.setBrojPrebivalista(stNum.getText());
       
       if(ch1.isSelected())
        req.setBracnoStanje(ch1.getText());
       
       else if(ch2.isSelected())
        req.setBracnoStanje(ch2.getText());
              
       else if(ch3.isSelected())
        req.setBracnoStanje(ch3.getText());
                     
       else if(ch4.isSelected())
       req.setBracnoStanje(ch4.getText());
       
       req.setStatus("Kreiran");
       

        if(checkTime() == 1)
          send();

       } else
           timeOk.setText("   Neka polja su prazna");
    }
    
    private boolean checkEmpty(){
        
        
        return jmbg.getText().isEmpty() || name.getText().isEmpty() ||
                surname.getText().isEmpty() || motherName.getText().isEmpty()
                || fatherName.getText().isEmpty() || motherSurname.getText().isEmpty() 
                || fatherSurname.getText().isEmpty() || gender.getText().isEmpty()
                || nat.getText().isEmpty() || birth.getText().isEmpty() ||
                prof.getText().isEmpty() || street.getText().isEmpty() ||
                place.getText().isEmpty() || stNum.getText().isEmpty();
    }
    
    private void initPanel2() {
        JLabel label = new JLabel("Unesite id");
        
        JPanel north = new JPanel(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());
        
        north.add(label);
        north.add(checkId);
           
        JButton show = new JButton("Prikazi");
        north.add(show);
        
        center.add(showObj);
      //  refresh.setVisible(false);
        evident.setVisible(false);
     //   north.add(refresh);
        north.add(evident);
        


        
       show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  refresh.setVisible(false);
                evident.setVisible(false);
                writeToLabel();
            }
        });
       
       
        panel2.add(north, "North");
        panel2.add(center, "Center");
       
        //label = new JLabel() 
    }
    
    
    public void refresh(Documentrequest req2) throws MalformedURLException, IOException, ParseException{
        String GET_URL = "http://collabnet.netset.rs:8081/is/persoCentar/";
        GET_URL += checkId.getText();
        
        System.out.println("Usao u showPressed");
                
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", HttpHeaders.USER_AGENT);
	int responseCode = con.getResponseCode();
	System.out.println("GET Response Code :: " + responseCode);
	if (responseCode == HttpURLConnection.HTTP_OK) { // success
            JSONParser parse = new JSONParser();
            BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

            
              //JSONArray jArray = (JSONArray) parse.parse(in);
              JSONObject jObject = (JSONObject) parse.parse(in);  
              in.close();
               
            //  writeToLabel(jObject);
              
              String status = jObject.get("status").toString();
              
              System.out.println("Status je " + status);
              
              if(status.equals("proizveden")) {
                    req2.setStatus("cekaNaIzvrsenje");
                    ent.getTransaction().begin();
                    ent.merge(req2);
                    ent.flush();
                    ent.getTransaction().commit();
                    writeToLabel();
                    addButtonEvident(req2); 
              }      
        }
             
     
    }
    
    
    private void writeToLabel(){
        List result = ent.createNamedQuery("Documentrequest.findById", Documentrequest.class).setParameter("id", Long.parseLong(checkId.getText())).setHint(QueryHints.REFRESH, true).getResultList();
        if(!result.isEmpty()){
            Documentrequest jObject = (Documentrequest) result.get(0);
            showObj.setText("<html> " + "ID: " + jObject.getId().toString() + "<br/>"
            + "JMBG: " + jObject.getJmbg() + "<br/>" + "Ime: " + jObject.getIme()+ "<br/>"
             + "Prezime: " + jObject.getPrezime()+ "<br/>"
             + "Ime Majke: " + jObject.getImeMajke()+ "<br/>"
             + "Ime oca: " + jObject.getImeOca() + "<br/>"
             + "Prezime majke: " + jObject.getPrezimeMajke()+ "<br/>"
             + "Prezime oca: " + jObject.getPrezimeOca() + "<br/>"
             + "Pol: " + jObject.getPol() + "<br/>"
             + "Datum rodjenja: " + jObject.getDatumRodjenja() + "<br/>"
             + "Nacionalnsot: " + jObject.getNacionalnost() + "<br/>"
             + "Profesija: " + jObject.getProfesija() + "<br/>"
             + "Bracno stanje: " + jObject.getBracnoStanje() + "<br/>"
             + "Opstina prebivalista: " + jObject.getOpstinaPrebivalista() + "<br/>"
             + "Ulica prebivalsita: " + jObject.getUlicaPrebivalista() + "<br/>"
             + "Broj prebivalista: " + jObject.getBrojPrebivalista() + "<br/>"
             + "Status: " + jObject.getStatus() + "<br/></html>");
            
            //if(jObject.getStatus().equals("uProdukciji"))
              // addButtonRefresh(jObject);
            // else 
             if(jObject.getStatus().equals("cekaNaIzvrsenje"))
                 addButtonEvident(jObject);
             else
                listener = new Listener(consumer, topic, this, jObject);
        } else {
            showObj.setText("Nema korisnika sa zadatim ID-om!");
        }
        
        
        
       // showObj = new JLabel();
       /*
        showObj.setText("<html> " + jObject("id").toString() + "<br/>"
        + jObject.get("JMBG").toString() + "<br/>" + jObject.get("ime").toString()+ "<br/>"
         + jObject.get("prezime").toString() + "<br/>"
         + jObject.get("imeMajke").toString() + "<br/>"
         + jObject.get("imeOca").toString() + "<br/>"
         + jObject.get("prezimeMajke").toString() + "<br/>"
         + jObject.get("prezimeOca").toString() + "<br/>"
         + jObject.get("pol").toString() + "<br/>"
         + jObject.get("datumRodjenja").toString() + "<br/>"
         + jObject.get("nacionalnost").toString() + "<br/>"
         + jObject.get("profesija").toString() + "<br/>"
         + jObject.get("bracnoStanje").toString() + "<br/>"
         + jObject.get("opstinaPrebivalista").toString() + "<br/>"
         + jObject.get("ulicaPrebivalista").toString() + "<br/>"
         + jObject.get("brojPrebivalista").toString() + "<br/>"
         + jObject.get("status").toString()+ "<br/></html>");
         */
  
    }
        
    /* 
    private void addButtonRefresh(Documentrequest req2){
      

        refresh.setVisible(true);
        
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                       refresh(req2);                
               } catch (IOException ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ParseException ex) {
                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        });
    }
*/
    
    private void addButtonEvident(Documentrequest req2){
       
        evident.setVisible(true);
       // refresh.setVisible(false);
        
        evident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    req2.setStatus("urucen");
                    ent.getTransaction().begin();
                    ent.merge(req2);
                    ent.flush();
                    ent.getTransaction().commit();
                    writeToLabel();
                    evident.setVisible(false);
            }
        });
    }
    
    public static void main(String[] args) throws IOException, ParseException {
        
        new Main();
    }
}
