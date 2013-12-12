package javapractice.n26;

//import javapractice.n07.List;

import java.io.*;
import java.net.*;
import java.util.*;


public class WebSurfer {

    public List<String> getTargetString(String adr, String pattern) throws Exception{
        LinkedList<String> result = new LinkedList<String>();
        URL url = new URL(adr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String str = br.readLine();
        while(str != null){
            if (str.indexOf(pattern) > -1) result.add(str);//System.out.println(str);
            str = br.readLine();
        }
        br.close(); //закрываем поток
        conn.disconnect();
        return result;
    }

}
