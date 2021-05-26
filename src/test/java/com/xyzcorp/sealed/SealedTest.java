package com.xyzcorp.sealed;

import org.junit.jupiter.api.Test;

public class SealedTest {
    @Test
    void testTree() {
        Tree<Integer> myTree = new Branch<>(
            new Branch<>(new Leaf<>(10), new Leaf<>(20)), new Leaf<>(10));
        System.out.println(myTree);
    }
}
