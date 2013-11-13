
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lukew4lker
 */
public class poller implements Runnable {

    /**
     *
     * @throws IOException
     * @throws MalformedURLException
     * @throws JSONException
     */
    public static Client messenger = new Client();

    public void run() {
        try {

            CookieManager.setDefault(Client.cookieManager);
            //boolean authbool = messenger.authenticate("Lukew4lker", pwInput);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //System.out.print("Enter String");
            System.out.println("Enter Username");
            System.out.println();
            String username = br.readLine();
            System.out.println("Enter Password");
            System.out.println();
            String p = br.readLine();
            //Console console = System.console();
            boolean authbool = poller.messenger.authenticate(username, p.toCharArray());
            System.in.mark(0);
            (new Thread(new InputChat())).start();
            System.out.println(authbool);
            messenger.channel = 8613406;
            //client.channel= 3;
            parser pars = new parser();
            /* The following three lines of code are are how you recreate 
             * the SortedMap made by the parser, see parser.java.
             */
            SortedMap[] b = (pars.parser(messenger.poll()));
            SortedMap Userlist = new TreeMap(b[0]);
            SortedMap Messagelist = new TreeMap(b[1]);
            LinkedList c = new LinkedList(Messagelist.values());
            Iterator I = c.iterator();
            String[] objects = new String[4];
            while (I.hasNext()) {
                objects = (String[]) (Object[]) I.next();
                System.out.println("[" + objects[1] + "] " + objects[2] + ": " + objects[3]);
            }
            //Review to figure out how to read messages from SortedMap.
     
            //System.out.println("Entryset: " + Messagelist.keySet());
            //String[] objects = (String[]) (Object[]) (Messagelist.get(Messagelist.lastKey()));
            //System.out.println("[" + objects[1] + "] " + objects[2] + ": " + objects[3]);
            
            
            //Put console link here.
            System.gc();
            while (true) {
                /*
                 * 
                 * 
                 * 
                 */
                b = (pars.parser(messenger.poll()));
                Userlist.clear();
                Messagelist.clear();
                Userlist = b[0];
                Messagelist = b[1];
                try {
                    objects = (String[]) Messagelist.get(Messagelist.lastKey());
                    System.out.println("[" + objects[1] + "] " + objects[2] + ": " + objects[3]);
                } catch (NoSuchElementException ex) {
                }
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(poller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(poller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(poller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}