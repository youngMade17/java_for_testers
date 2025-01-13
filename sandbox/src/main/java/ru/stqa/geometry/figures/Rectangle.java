package ru.stqa.geometry.figures;

public record Rectangle(double side1, double side2) {

    public Rectangle {
        if (side1 < 0 || side2 < 0)
            throw new IllegalArgumentException("Rectangle side should be non-negative");
    }

    public static void printRectangleArea(Rectangle rectangle) {
        System.out.println(String.format("Площадь прямоугольника со сторонами %f и %f = %f", rectangle.side1, rectangle.side2, rectangle.rectangleArea()));
    }

    private double rectangleArea() {
        return side1 * side2;
    }


}
