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

    @Test
    void negativeSide() {
        try {
            new Triangle(-5.0, 3.0, 9.0);
            Assertions.fail();
        } catch (IllegalArgumentException exceptionSide1) {
            //OK
            try {
                new Triangle(5.0, -3.0, 9.0);
                Assertions.fail();
            } catch (IllegalArgumentException exceptionSide2) {
                //OK
                try {
                    new Triangle(5.0, 3.0, -9.0);
                    Assertions.fail();
                } catch (IllegalArgumentException exceptionSide3) {
                    //OK
                }
            }
        }
    }

    @Test
    void lengthOfTwoSidesLessThirdSide() {
        try {
            new Triangle(2.0, 3.0, 6.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception1) {
            //OK
            try {
                new Triangle(2.0, 7.1, 5.0);
                Assertions.fail();
            } catch (IllegalArgumentException exception2) {
                //OK
                try {
                    new Triangle(12.01, 4.0, 8.0);
                    Assertions.fail();
                } catch (IllegalArgumentException exception3) {
                    //OK
                }
            }
        }
    }

    @Test
    void equality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(4.0, 5.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }

}
