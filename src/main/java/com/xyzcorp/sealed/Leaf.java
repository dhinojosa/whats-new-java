package com.xyzcorp.sealed;

public final record Leaf<A>(A value) implements Tree<A> {
    public Leaf {
        if (value == null)
            throw new NullPointerException("Leaf values cannot be null");
    }
}
