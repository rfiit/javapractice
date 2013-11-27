package javapractice.n07;

public class List<E> {

    // глобальный вопрос о копировании списков из параметров при создании новых списков (функциональный стиль)
    // done.(без объектов Е)

    private E value = null;

    private ListElement<E> head = null;

    private final boolean isElementary;

    public boolean isElementary() {
        return this.isElementary && (this.head == null);
    }

    public boolean isList() {
        return !this.isElementary && (this.head != null);
    }

    public static <LE> List<LE> cons(List<LE> b, List<LE> e) {
        List<LE> list = e.cloneWithoutValue();
        if (e.isList()) {
            list.addToBegin(b);
        } else {
            list = new List<LE>(new ListElement<LE>(list));
            list.addToBegin(b.cloneWithoutValue());
        }
        return list;
    }   //+

    private List<E> cloneWithoutValue() {
        //System.out.println("start cloning");
        if (this.isElementary()) {
            //System.out.println("end cloning");
            return new List<E>(this.value);
        }
        ListElement<E> le = new ListElement<E>(head.value.cloneWithoutValue());
        List<E> newlist = new List<E>(le);
        le = this.head.next;
        while (le != null) {
            newlist.add(le.value.cloneWithoutValue());
            le = le.next;
        }
        //System.out.println("end cloning");
        return newlist;
    }

    public static <LE> List<LE> cons(LE b, LE e) {
        return cons(new List<LE>(b), new List<LE>(e));  //double cloning. bad...
    } //+

    public static <LE> List<LE> cons(List<LE> b, LE e) {
        return cons(b, new List<LE>(e));  //double cloning. bad...
    } //+

    public static <LE> List<LE> cons(LE b, List<LE> e) {
        //поведение в данном случае отличается
//        List<LE> begin = new List<LE>(b);
//        List<LE> nl = e.cloneWithoutValue();
//        nl.addToBegin(begin);
//        return nl;
        return cons(new List<LE>(b), e);
    } //+

    public static <LE> LE val(List<LE> list) {
        return (list == null || !list.isElementary()) ? null : list.value;
    }

    public static <LE> List<LE> car(List<LE> list) {
        ListElement<LE> le = list.head;
        return le.value.cloneWithoutValue();
    }  //+

    public static <LE> List<LE> cdr(List<LE> list) {
        if (list.isElementary()) return null;
        ListElement<LE> le = list.head.next;
        List<LE> newlist = list.cloneWithoutValue();
        newlist.head = newlist.head.next;
        // if next == null - create elementary; else create list
        return newlist;
    }  //+

    public static <LE> List<LE> reverse(List<LE> list) {
//        if (list.isElementary()) return list.cloneWithoutValue();
//        ListElement<LE> le = list.head;
//        ListElement<LE> pr = null;
//        while(le != null) {
//            le.prev = pr;
//            pr = le;
//            le = le.next;
//        }
//        List<LE> newlist = new List<LE>(new ListElement<LE>(pr.value.cloneWithoutValue()));
//        pr = pr.prev;
//        while (pr != null) {
//            newlist.a
//            pr = pr.prev;
//        }
        if (list.isElementary()) return list.cloneWithoutValue();
        ListElement<LE> next = list.cloneWithoutValue().head;
        List<LE> newlist = new List<LE>(new ListElement<LE>(next.value));
        next = next.next;
        while (next != null) {
            newlist.addToBegin(next.value);
            next = next.next;
        }
        return newlist;
    } //+

    public static <LE> List<LE> append(List<LE> fl, List<LE> ... listarray) {
        List<LE> nl = new List<LE>(new ListElement<LE>(null));
        List<LE> clonelist;// = fl.cloneWithoutValue();
        ListElement<LE> tail = nl.head;

        if (fl.isElementary()) {
            nl.head.value = fl.cloneWithoutValue();
        } else {
            clonelist = fl.cloneWithoutValue();
            nl.head = clonelist.head;
            tail = nl.head;
            while (tail.next != null) {
                tail = tail.next;
            }
        }

        for (int i = 0; i < listarray.length; i++) {
            clonelist = listarray[i].cloneWithoutValue();
            if (clonelist.isElementary()) {
                tail.next = new ListElement<LE>(clonelist);
            } else {
                tail.next = clonelist.head;
            }
            while (tail.next != null) {
                tail = tail.next;
            }
        }
        return nl;
    }  //+

    @Override
    public String toString() {
        if (this.isElementary()) return "[" + this.value + "]";
        String result = "(";
        ListElement<E> le = this.head;
        while (le != null) {
            result += (le == this.head ? "" : " ") + le.value;
            le = le.next;
        }
        return result + ")";
    }

    private void initElementary(E element) {
        this.value = element;
        this.head = null;
    }

    private void addToBegin(List<E> list) {
        //if (this.isElementary()) throw new Exception("i am elementary (not list)!"); //внутренняя функциональность. ситуация невозможна
        ListElement<E> le = new ListElement<E>(list);
        le.next = this.head;
        this.head = le;
    }

    private void add(List<E> list) {
        //if (this.isElementary()) throw new Exception(); //внутренняя функциональность. ситуация невозможна
        ListElement<E> le = this.head;
        while (le.next != null) {
            le = le.next;
        }
        le.next = new ListElement<E>(list);
    }

    private void initList(ListElement<E> le) {
        this.head = le;
    }

    private List(E element) {
        initElementary(element);
        this.isElementary = true;
    }

    private List(ListElement<E> listelement) {
        if (listelement == null) {
            this.isElementary = true;
            initElementary(null);
            return;
        }
        this.isElementary = false;
        initList(listelement);
    }

    static class ListElement<E> { //ссылка на родителя не нужна
        ListElement<E> next = null;
        ListElement<E> prev = null; // используется только в функции reverse // не контролируется в других функциях
        List<E> value = null;
        ListElement(List<E> l) {
            this.value = l;
        }

    }

}
