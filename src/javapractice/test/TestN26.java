package javapractice.test;

import javapractice.n26.WebSurfer;
import org.junit.Test;
import org.junit.*;
import junit.framework.Assert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestN26 extends Assert{

    //good site for tests www.oracle.com/index.html

    List<String> ll;

    @After
    public void initTest() {
        ll = null;
    }

    @Test(expected = java.net.MalformedURLException.class)
    public void testNullURL()throws Exception {
        WebSurfer ws = new WebSurfer();
        ws.getTargetString(null, "test");
    }

    @Test(expected = java.net.MalformedURLException.class)
    public void testErrorURL()throws Exception {
        WebSurfer ws = new WebSurfer();
        ws.getTargetString("blablalba", "test");
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void testNullPattern()throws Exception {
        WebSurfer ws = new WebSurfer();
        ws.getTargetString("http://google.com", null);
    }

    @Test
    public void testOracleSiteOneString()throws Exception {
        WebSurfer ws = new WebSurfer();
        ll = ws.getTargetString("http://www.oracle.com/index.html", "http-equiv=");
        assertEquals(ll.size(), 1);
        assertEquals(ll.iterator().next(), "<head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\t\t");
    }

    @Test
    public void testOracleSiteZeroString()throws Exception {
        WebSurfer ws = new WebSurfer();
        ll = ws.getTargetString("http://www.oracle.com/index.html", "http-eiv=");
        //assertEquals(ll.size(), 1);
        assertEquals(ll.iterator().hasNext(), false);
    }

    @Test
    public void testOracleSiteManyString() throws Exception {
        WebSurfer ws = new WebSurfer();

        String[] starr = {
                "\tvar atg_fName = \"\";",
                "\tvar atg_lName = \"\";",
                "\tvar atg_eMail = \"\";",
                "\tvar atg_company = \"\";",
                "\t\tatg_fName = USER.firstname;",
                "\t\tatg_lName = USER.lastname;",
                "\t\tatg_eMail = USER.username;",
                "\t\t\t\tatg_company = cValue; ",
                "\tvar atg_country = \"US\";"
        };

        ll = ws.getTargetString("http://www.oracle.com/index.html", "atg_");
        assertEquals(ll.size(), 9);
        int i = 0;
        Iterator<String> it = ll.iterator();
        for (String s: ll) {
            assertEquals(s, starr[i]);
            i++;
        }
    }

}
