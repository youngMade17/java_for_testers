package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void getPerimeter() {
        Triangle testTriangle = new Triangle(7.0, 10.0, 15.0);
        Assertions.assertEquals(32, testTriangle.trianglePerimeter());

    }

    @Test
    void getArea() {
        Triangle testTriangle = new Triangle(7.0, 10.0, 15.0);
        // Сравнение значений, округленных до 3-х знаков после запятой
        Assertions.assertEquals(29.394, Math.round(testTriangle.triangleArea()*1000.0)/1000.0);
    }

}
