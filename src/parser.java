import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

/**
 * @author lukew4lker
 *
 *
 * SortedMap key = MessageID SortedMap value = messagearray
 *
 * Message array format
 *
 * 0 = UserID 1 = Time 2 = User 3 = Message
 *
 *w
 * SortedMap key = UserID SortedMap value = Userarray
 *
 * User array formatting
 *
 * 0 = username 1 = muted
 *
 */
public class parser {

    public static String[] moa;
    public static String[] uoa;

    public SortedMap[] parser(JSONObject json) throws JSONException {
        //System.out.println(json); //DUMP JSON
        SortedMap mapuser = new TreeMap();
        SortedMap mapmessages = new TreeMap();
        Iterator i = json.keys();
        while (i.hasNext()) {
            String key = (String) i.next();
            switch (key) {
                case "lmid":
                    Client.lastMessageID = Integer.parseInt(json.get(key).toString());
                    //System.out.println(Client.lastMessageID);
                    break;
                case "messages":
                    /* Works for the most part, adds each message to the map as a separate entry.
                     * Actively sorts key value pairs as they are inserted into the map.  
                     */
                    JSONObject mo = json.getJSONObject(key);
                    Iterator mc = mo.keys();
                    while (mc.hasNext()) {
                        String mkey = (String) mc.next();
                        JSONObject m = (JSONObject) mo.get(mkey);
                        //Move JSON objects to java array
                        moa = new String[4];
                        moa[0] = (m.get("user_id").toString());
                        moa[1] = (m.get("time").toString());
                        //parse user key in jsonobject message to escape html
                        // &p=0 doesn't seem to work :? 
                        moa[2] = (Jsoup.parse(m.getString("user")).text());
                        moa[3] = (m.get("message").toString());
                        mapmessages.put(Integer.parseInt(mkey), moa);

                    }
                    break;
                case "users":
                    JSONObject uo = json.getJSONObject(key);
                    Iterator uc = uo.keys();
                    while (uc.hasNext()) {
                        String ukey = (String) uc.next();
                        JSONObject u = (JSONObject) uo.get(ukey);
                        //Remove HTML from Username key
                        uoa = new String[2];
                        uoa[0] = (Jsoup.parse(u.getString("user")).text());
                        uoa[1] = (u.get("muted").toString());
                        mapuser.put(Integer.parseInt(ukey), uoa);
                    }
                    break;
            }
        }
        //System.out.println("Mapmessages: " + mapmessages);
        //System.out.println("Mapuser: " + mapuser);
        return new SortedMap[]{mapuser, mapmessages};
    }
}
