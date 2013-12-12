package javapractice.n30;

//import java.util.TreeSet

import java.util.Iterator;
import java.util.LinkedList;

public class Set<T> {

//    лучшим вариантом было бы использовать для внутреннего хранения HashSet или нечто подобное из стандартной библиотеки,
//    но так как ничего не известно про объекты типа Т (у нас нет никакой информации о них, в частности, неизвестно,
//    реализуют ли они корректно метод hashCode) кроме того, что они корректно реализуют метод equals, придется сооружать
//    множество без оптимизаций хранения и поиска.

//    будем реализовывать через LinkedList

    LinkedList<T> linkedlist;

    private Set(T e) {
        init(e);
    }

    private void init(T e) {
        this.linkedlist = new LinkedList<T>();
        linkedlist.add(e);
    }

    private Set() {
        //this.linkedlist = null;
    }

    private void add(T e) {
        if (this.linkedlist == null) {
            init(e);
        } else {
            this.linkedlist.add(e);
        }
    }

    private boolean isEmpty() {
        return this.linkedlist == null || this.linkedlist.isEmpty();
    }

    private static <T> Set<T> privateIntersection(Set<T> a, Set<T> b) {
        Set<T> result = Set.empty();

        for (T current : a.linkedlist) {
            for (T current2 : b.linkedlist) {
                if ((current == null && current2 == null) || (current != null && current.equals(current2))) result.add(current);
            }
        }

        return result;
    }

//    private void deleteDupl() {
//        Iterator<T> it = this.linkedlist.listIterator();
//        int indx, indx2;
//        while(it.hasNext()) {
//            T cur = it.next();
//            indx = this.linkedlist.indexOf(cur);
//            indx2 = this.linkedlist.lastIndexOf(cur);
//            while (indx != indx2) {
//                this.linkedlist.remove(indx2);
//                indx2 = this.linkedlist.lastIndexOf(cur);
//            }
//        }
//    }

    //-------------------------------------------------------PUBLIC

    public static <T> Set<T> empty() {
        return new Set<T>();
    }

    public static <T> Set<T> singleton(T e) {
        return new Set<T>(e);
    }

    public static <T> Set<T> union(Set<T> ... setarray) {
        if (setarray.length == 1) return setarray[0];
        Set<T> result = Set.empty();
        result.linkedlist = new LinkedList<T>();
        for (int i = 0; i < setarray.length; i++) {
            if (setarray[i] != null && !setarray[i].isEmpty()) {
                result.linkedlist.removeAll(setarray[i].linkedlist);
                result.linkedlist.addAll(setarray[i].linkedlist);
            }
        }
//        result.deleteDupl();
        return result;
    }

    public static <T> Set intersection(Set<T> a, Set<T> b, Set<T> ... setarray) {
        //if (setarray.length < 2) return Set.empty(); // canceled by a and b

        if (a == null || a.isEmpty() || b == null ||  b.isEmpty()) return Set.empty();
        for (int i = 0; i < setarray.length; i++) {
            if (setarray[i] == null || setarray[i].isEmpty()) return Set.empty();
        }

        Set<T> result = privateIntersection(a, b);
        //if (result.isEmpty()) return result;
        for (int i = 0; i < setarray.length && !result.isEmpty(); i++) {
            result = privateIntersection(result, setarray[i]);
        }

        return result;
    }

    public static <T> Set<T> setDifference(Set<T> a, Set<T> b) {
        LinkedList<T> reslist = new LinkedList<T>(a.linkedlist);
        reslist.removeAll(b.linkedlist);
        Set<T> result = new Set<T>();
        result.linkedlist = reslist;
        return result;
    }

    public boolean in(T e) {
        return this.linkedlist.contains(e);
    }

    public String toString() {
        if (this.linkedlist == null) return "[]";
        Iterator<T> it = this.linkedlist.iterator();
        StringBuilder result = new StringBuilder();
        result.append("[");
        while (it.hasNext()) {
            result.append(it.next());
            result.append(it.hasNext() ? ", " : "");
        }
        return result.append("]").toString();
    }

}
