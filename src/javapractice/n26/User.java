package javapractice.n26;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import static javapractice.util.Utilities.*;

public class User {
    public static void main(String[] args) {
        WebSurfer ws = new WebSurfer();
        try {
            List<String> result = ws.getTargetString("http://google.com", "<");
            Iterator<String> i = result.iterator();
            for (String s : result) {
                pln(s);
            }
        } catch (IOException e) {
            pln("IO Exception");
        } catch (Exception e) {
            pln("Other exception");
        }
    }
}
