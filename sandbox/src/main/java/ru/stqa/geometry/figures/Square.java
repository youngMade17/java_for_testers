package ru.stqa.geometry.figures;

public record Square(double side) {
    //private double side;

    public Square {
        if (side < 0)
            throw new IllegalArgumentException("Square side should be non-negative");
        //this.side = side;
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
