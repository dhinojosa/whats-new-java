package com.xyzcorp.sealed.shapes;

public final class Rectangle extends Shape {
    private final int length;
    private final int height;

    public Rectangle(int length, int height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public int area() {
        return length * height;
    }
}
