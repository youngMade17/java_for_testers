package ru.stqa.geometry.figures;

public class Square {
    private double side;
    public Square(double side) {
        this.side = side;
    }

    public static void printSquareArea(Square s) {
        System.out.println( String.format("Площадь квадрата со стороной %f = %f", s.side, s.area()) );
    }

    public double area() {
        return side * side;
    }

    public double perimeter() {
        return 4 * side;
    }
}
