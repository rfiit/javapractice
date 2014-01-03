package javapractice.n13;


public class IdTab<T>{ //try to do "implements Map<String, T>" but no..

    private final int C = 255; // константа деления

    private Element[] table; // массив-таблица с ссылками на списки элементов

    private int size = 0;  //текущий размер таблицы

    public IdTab() {
        table = new IdTab.Element[C];//(Element[])(new Object[C]);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(String key) {
        return searchElement(key) != null;
    }

    public T search(String key) {
        Element e = searchElement(key);
        return (e == null) ? null : (T) e.value;
    }

    private Element searchElement(String key) {
        int hash = hashKey(key);
        int len = key.length();
        Element e = table[hash];
        while (e != null && (e.keylen != len || !e.key.equals(key)))
            e = e.next;
        return e;
    }

    public T add(String key, T value) {
        Element e;
        if (containsKey(key)) { // not good performance
            e = searchElement(key);
            e.value = value;
            return value;
        }
        this.size += 1;
        int hash = hashKey(key);
        e = table[hash];
        if (e == null) {
            table[hash] = new Element(key, value);
            return value;
        }
        while (e.next != null) {
            e = e.next;
        }
        e.next = new Element(key, value);
        return value;
    }

    public int hashKey(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash = (hash + key.codePointAt(i)) % C;
        return hash;
    }

//    public T remove(String key) { // dont need by specification
//        return null;
//    }


    private class Element {
        //final String key;
        //final int keylen; // ? but specification

        String key;
        int keylen; // ? but specification
        T value;
        Element next;
        public Element(String key, T val) {
            this.key = key;
            this.keylen = key.length();
            this.value = val;
        }
        public Element() {
        }
    }
}
