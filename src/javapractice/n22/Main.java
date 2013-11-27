package javapractice.n22;

public class Main {
    public static void main(String[] a) {
        System.out.println((new Checker()).check("[lol{](})"));
        System.out.println((new Checker()).check("[lol{}]()()"));
    }
}
