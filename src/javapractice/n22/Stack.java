package javapractice.n22;

public class Stack {
    Element tail = null;
//    public void push(String a) {   // для удобства под задачу
//        push(a.codePointAt(0));
//    }

    public void push(int a) {
        tail = new Element(a, tail);
    }

    public int pop() {
        if (tail == null) return -1;
        int res = tail.getValue();
        tail = tail.getPrevious();
        return res;
    }

}

class Element {
    int val;
    private Element pr;
    Element(int a, Element previous) {
        val = a;
        pr = previous;
    }

    Element getPrevious() {
        return pr;
    }

    int getValue() {
        return val;
    }
}
