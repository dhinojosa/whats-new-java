package com.xyzcorp.sealed.shapes;

public final class Triangle extends Shape {
    private final int base;
    private final int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public int area() {
        return (int) (.5 * (base * height));
    }
}
