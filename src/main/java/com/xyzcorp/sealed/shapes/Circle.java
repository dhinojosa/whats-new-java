package com.xyzcorp.sealed.shapes;

public final class Circle extends Shape {
    private final int radius;
    public Circle(int radius) {
        this.radius = radius;
    }

    public int radius() {
        return radius;
    }

    @Override
    public int area() {
        return (int) (Math.PI * Math.pow(radius,2));
    }
}
