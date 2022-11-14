package com.xyzcorp.sealed.shapes;

public sealed abstract class Shape permits Circle, Triangle {
    static String printStatement(Shape s) {
        return switch (s) {
            case Circle c ->
                String.format("Circle with radius: %d", c.radius());
            case Triangle t -> String.format("Triangle with area: %d", t.area());
        };
    }

    public abstract int area();
}
