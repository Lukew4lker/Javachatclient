
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lukew4lker
 */
public class InputChat implements Runnable {

    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //System.out.print("Enter String");
            String s;
            System.out.println("Ready for input.");
            while (true) {
                s = br.readLine();
                //System.out.println(s);
                int postMessage = poller.messenger.postMessage(s);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(poller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(poller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
