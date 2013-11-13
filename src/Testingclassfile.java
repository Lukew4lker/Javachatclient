import java.io.IOException;
import java.net.MalformedURLException;
import org.json.JSONException;
/**
 *
 * @author lukew4lker
 */
public class Testingclassfile {

    public static void main(String arg[]) throws IOException, MalformedURLException, JSONException, CloneNotSupportedException, InterruptedException {
        (new Thread(new poller())).start();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Enter String");
        //String s;
        //Thread.sleep(8000);
        //System.out.println("Ready for input.");
        //while (true) {
            //s = br.readLine();
            //System.out.println(s);
          //  int postMessage = poller.messenger.postMessage(s);
        //}
    }
}