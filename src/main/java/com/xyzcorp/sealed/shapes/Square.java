package com.xyzcorp.sealed.shapes;

public class Square extends Rectangle{
    protected Square(int length, int height) {
        super(length, height);
    }

    public Square(int length) {
        this(length, length);
        if (length < 0) throw new RuntimeException();
    }
}
