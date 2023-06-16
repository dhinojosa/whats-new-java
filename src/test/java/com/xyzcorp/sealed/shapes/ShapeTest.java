package com.xyzcorp.sealed.shapes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShapeTest {

    String printStatement(Shape s) {
        return switch (s) {
            case Circle c ->
                String.format("Circle with radius: %d", c.radius());
            case Triangle t
            when t.area() > 10 ->
                String.format("Triangle with area: %d", t.area());
            case Triangle t ->
            String.format("Triangle with area: %d", t.area());
            case Rectangle r ->
                String.format("Rectangle with area: %d", r.area());
        };
    }

    @Test
    void testTriangle() {
        String str = printStatement(new Triangle(10, 11));
        assertThat(str).isEqualTo("Triangle with area: 55");
    }

    @Test
    void testCircle() {
        String str = printStatement(new Circle(22));
        assertThat(str).isEqualTo("Circle with radius: 22");
    }
}
