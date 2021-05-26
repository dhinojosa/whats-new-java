package com.xyzcorp.patternmatching;

public class PatternMatchingExample {

    public String whatIsIt(Object o) {
        if (o instanceof Integer i) {
            return String.format("integer %d, and add 1 results in %d", i, i + 1);
        } else if (o instanceof String s) {
            return s;
        }
        return "Nothing";
    }

    public String compoundMatch(Object o) {
        if (o instanceof String s && !s.isEmpty()) {
            return "Non-empty String";
        }
        return "Nothing";
    }

    //sealed abstract Tree<A> permits Node, Branch
    //final class Node<A>(A value) extends Tree
    //final class Branch(Tree<A> left, Tree<A> right) extends Tree
}
