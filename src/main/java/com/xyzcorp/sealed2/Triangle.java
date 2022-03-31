package com.xyzcorp.sealed2;

public final class Triangle extends Shape {
    private int base;
    private int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public int area() {
        return (int) (.5 * (base * height));
    }
}
