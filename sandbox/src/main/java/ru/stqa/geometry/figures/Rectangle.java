package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double side1, double side2) {
        System.out.println(String.format("Площадь прямоугольника со сторонами %f и %f = %f", side1, side2, rectangleArea(side1, side2)));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }
}
