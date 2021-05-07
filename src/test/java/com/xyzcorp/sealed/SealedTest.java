package com.xyzcorp.sealed;

import org.junit.jupiter.api.Test;

public class SealedTest {


    @Test
    void testSealed() {
        MyOption<Integer> mo = new MySome<>(40);
        MyOption<Integer> mo2 = mo.map(x -> x * 3);
        System.out.println(mo2);
    }
}
