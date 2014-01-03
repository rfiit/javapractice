package javapractice.test;

import javapractice.n13.IdTab;
import org.junit.Test;
import org.junit.*;
import junit.framework.Assert;

public class TestN13 extends Assert{

    //good site for tests www.oracle.com/index.html

    IdTab<Integer> idtab;

//    @After
//    public void initTest() {
//        idtab = null;
//    }

    @Before
    public void initTest() {
        idtab = new IdTab<Integer>();
        idtab.add("one", 1);
        idtab.add("two", 2);
        idtab.add("three", 3);
        idtab.add("zero", 0);
        idtab.add("wto", 22);
        idtab.add("eethr", 33);
        idtab.add("etehr", 333);
    }


    @Test
    public void testSizeAndIsEmpty() {
        assertEquals(idtab.isEmpty(), false);
        assertEquals(idtab.size(), 7);
        idtab.add("two", 2222);
        assertEquals(idtab.size(), 7);
        idtab.add("owt", 222);
        assertEquals(idtab.size(), 8);
        idtab = new IdTab<Integer>();
        assertEquals(idtab.size(), 0);
        assertEquals(idtab.isEmpty(), true);
    }

    @Test
    public void testSearch() throws Exception {
        assertEquals(idtab.search("two"), new Integer(2));
        assertEquals(idtab.search("zero"), new Integer(0));
        assertEquals(idtab.search("wto"), new Integer(22));
        assertEquals(idtab.search("nulltest"),  null);

        idtab.add("wto", 222);

        assertEquals(idtab.search("wto"), new Integer(222));
    }

}
