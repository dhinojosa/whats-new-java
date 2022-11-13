package com.xyzcorp.sealed2;

public final class Circle extends Shape {
    int radius;
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
