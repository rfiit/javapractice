package javapractice.n22;

public class Checker {
    public boolean check(String a) {
        Stack st = new Stack();
        for (int i = 0; i < a.length(); i++) {
            int code = a.codePointAt(i);
            if (code == "[".codePointAt(0) || code == "{".codePointAt(0) || code == "(".codePointAt(0)) st.push(code);
            if ((code == "]".codePointAt(0) || code == "}".codePointAt(0) || code == ")".codePointAt(0)) && !isValidPair(st.pop(), code)) return false;
        }
        return st.pop() == -1;
    }

    private boolean isValidPair(int a, int b) {
        return
                (a == "[".codePointAt(0) && b == "]".codePointAt(0)) ||
                (a == "(".codePointAt(0) && b == ")".codePointAt(0)) ||
                (a == "{".codePointAt(0) && b == "}".codePointAt(0));

    }
}

