package javapractice.util;

import java.util.Iterator;
import java.util.List;

public class Utilities {
    public static void printArray(Object[] a) {
        System.out.println(a.getClass().getName());
        if (a == null) {return;}
        p("[ ");
        for (int i = 0; i < a.length-1; i++) {
            p(a[i] + ", ");
        }
        p(a[a.length-1] + " ]");
    }

    public static void p(Object o) {
        System.out.print(o);
    }

    public static void pln(Object o) {
        System.out.println(o);
    }

    public static void pln() {
        System.out.println();
    }

    public static void pList(List list) {
        if (!(list instanceof List)) {
            p(list);
            return;
        }
        Iterator it = list.listIterator();
        while (it.hasNext()) {
            pln(it.next());
        }
    }

    public static void main(String[] args) {
//        Integer[] p = new Integer[8];
//        printArray(p);
    }
}
