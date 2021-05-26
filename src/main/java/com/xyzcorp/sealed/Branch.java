package com.xyzcorp.sealed;

public final record Branch<A> (Tree<A> left, Tree<A> right) implements Tree<A> { }
