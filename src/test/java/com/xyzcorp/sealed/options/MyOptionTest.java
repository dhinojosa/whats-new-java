package com.xyzcorp.sealed.options;


import com.xyzcorp.sealed.optionals.MyNone;
import com.xyzcorp.sealed.optionals.MySome;
import org.junit.jupiter.api.Test;

public class MyOptionTest {

    public String doMatch(Object o) {
        if (o instanceof MySome<?> ms) {
            return ms.getValue().toString();
        } else if (o instanceof MyNone mn) {
            return mn.toString();
        }
        return "Avoid";
    }


    // What may be possible
    // https://cr.openjdk.java.net/~briangoetz/amber/pattern-match.html
    @Test
    void patternMatchWithSealedClass() {
        String result = doMatch(new MySome<>(40));
        System.out.println(result);
    }
}
