package javapractice.n07;

import static javapractice.n07.List.*;
import static javapractice.util.Utilities.*;

public class User {

    public static void main(String[] a) {
        //testing
        pln(cons(1,2));
        pln(cons(cons(0,0), cons(5,1)));
        pln(cdr(car(cdr(append(cons(6, 7),cons(cons(1,2), 8))))));
        pln(cons((Integer)null, (Integer)null)); // иначе запустить не получилось // ??
        pln(cons(cons(0,0), reverse(cons(5,1))));

        pln((append(cons(1,2), reverse(cons(4,2)))));

        pln(reverse(append(cons(1,2), cons(cons(7,8), 0))));

        pln(reverse(car(cons(1,2))));
        pln(car(reverse((cons(1,2)))));

        pln(cdr(cons(1,cons(8,9))));
        pln(car(reverse(cdr(cons(1,cons(8,9))))));
    }


}
