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
                String.format("Large Triangle with area: %d", t.area());
            case Triangle t ->
                String.format("Small Triangle with area: %d", t.area());
            case Parallelogram p ->
                String.format("Parallelogram with area: %d", p.area());
            case Rectangle r ->
                String.format("Rectangle with area: %d", r.area());
        };
    }

    @Test
    void testSmallTriangle() {
        String str = printStatement(new Triangle(3, 4));
        assertThat(str).isEqualTo("Small Triangle with area: 6");
    }

    @Test
    void testLargeTriangle() {
        String str = printStatement(new Triangle(10, 11));
        assertThat(str).isEqualTo("Large Triangle with area: 55");
    }


    @Test
    void testCircle() {
        String str = printStatement(new Circle(22));
        assertThat(str).isEqualTo("Circle with radius: 22");
    }

    @Test
    void testRectangle() {
        String str = printStatement(new Rectangle(10, 30));
        assertThat(str).isEqualTo("Rectangle with area: 300");
    }

    @Test
    void testSquare() {
        String str = printStatement(new Square(10));
        assertThat(str).isEqualTo("Rectangle with area: 100");
    }

    @Test
    void testParallelogram() {
        String str = printStatement(new Parallelogram(10, 40));
        assertThat(str).isEqualTo("Parallelogram with area: 400");
    }
}
