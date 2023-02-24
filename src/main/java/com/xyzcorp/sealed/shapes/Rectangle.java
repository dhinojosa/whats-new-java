package com.xyzcorp.sealed.shapes;

public final class Rectangle extends Shape {
    private int length;
    private int height;

    @Override
    public int area() {
        return length * height;
    }
}
