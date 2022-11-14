package com.xyzcorp.sealed.shapes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShapeTest {
    @Test
    void testTriangle() {
        String str = Shape.printStatement(new Triangle(10, 11));
        assertThat(str).isEqualTo("Triangle with area: 55");
    }

    @Test
    void testCircle() {
        String str = Shape.printStatement(new Circle(22));
        assertThat(str).isEqualTo("Circle with radius: 22");
    }
}
