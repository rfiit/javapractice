package javapractice.test;

import javapractice.n30.Set;
import org.junit.After;
import org.junit.Test;
import junit.framework.Assert;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static javapractice.n30.Set.*;

public class TestN30 extends Assert{

    Set<Integer>[] si;
    Set<E>[] se;

    @After
    public void initTest() {
//        si =  (Set<Integer>[])(new Object[10]); // ?
//        se =  (Set<E>[])(new Object[10]);
    }

    @Test
    public void TestUnionInteger() {
        assertEquals(union(singleton(5), singleton(7), singleton(-1)).toString(), "[5, 7, -1]");
    }

    @Test
    public void TestUnionE() {
        assertEquals(union(singleton(new E(5)), singleton(new E(8)), singleton(new E(-6))).toString(), "[=5, =8, =-6]");
    }

    @Test
    public void TestUnionEmpty() {
        Set<E> es = empty();
        assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).toString(), "[=5, =-6]");
    }

    @Test
    public void TestUnionNull() {
        Set<E> es = null;
        assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).toString(), "[=5, =-6]");
    }

    @Test
    public void TestUnionNullElement() {
        Set<E> es = singleton(null);
        assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).toString(), "[=5, null, =-6]");
    }

    @Test
    public void TestIntersectionOfUnion() {
        Set<E> es = singleton(null);
        assertEquals(intersection(union(singleton(new E(5)), es, singleton(new E(8))),
                union(singleton(new E(5)), singleton(new E(8)))).toString(), "[=5, =8]");
    }

    @Test
    public void TestIn() {
        Set<E> es = singleton(null);
        assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(new E(8)), false);
        assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(new E(5)), true);
        assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(null), true);
    }

    @Test
    public void TestIntersection() {
        Set<E> es = singleton(null);
        Set<Integer> is = singleton(null);

        Set<E> s1 = union(singleton(new E(5)), singleton(new E(8)), singleton(new E(2)), singleton(new E(0)),
                singleton(new E(5)), singleton(new E(6)), singleton(new E(10000)), singleton(new E(4)), singleton(new E(-1)));

        Set<E> s2 = union(singleton(new E(5)), singleton(new E(-8)), singleton(new E(-2)), singleton(new E(0)),
                singleton(new E(5)), singleton(new E(-6)), singleton(new E(10000)), singleton(new E(4)), singleton(new E(1)));

        Set<E>diff = intersection(s1, s2);

        assertEquals(diff.in(new E(5)) && diff.in(new E(10000)) && diff.in(new E(4)) && diff.in(new E(0)), true);
        //assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(new E(5)), true);
        //assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(null), true);
    }

    @Test
    public void TestSetDifference() {
        Set<E> es = singleton(null);
        Set<Integer> is = singleton(null);

        Set<E> s1 = union(singleton(new E(5)), singleton(new E(8)), singleton(new E(2)), singleton(new E(0)),
                singleton(new E(5)), singleton(new E(6)), singleton(new E(10000)), singleton(new E(4)), singleton(new E(-1)));

        Set<E> s2 = union(singleton(new E(5)), singleton(new E(-8)), singleton(new E(-2)), singleton(new E(0)),
                singleton(new E(5)), singleton(new E(-6)), singleton(new E(10000)), singleton(new E(4)), singleton(new E(1)));

        Set<E>diff = setDifference(s1, s2);

        assertEquals(diff.in(new E(8)) && diff.in(new E(2)) && diff.in(new E(6)) && diff.in(new E(-1)), true);
        //assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(new E(5)), true);
        //assertEquals(union(singleton(new E(5)), es, singleton(new E(-6))).in(null), true);
    }



    private class E {
        private int pv;
        public E(int p) {pv = p;}
        @Override
        public boolean equals(Object e) {
            return (e != null) && (E.class.isInstance(e)) && (((E)e).pv == pv);
        }
        @Override
        public String toString() {
            return "=" + pv;
        }
    }

}
