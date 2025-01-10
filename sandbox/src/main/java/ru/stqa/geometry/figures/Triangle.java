package ru.stqa.geometry.figures;

public class Triangle {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        if (side1 < 0 || side2 < 0 || side3 < 0)
            throw new IllegalArgumentException("Triangle sides should be non-negative");
        if (side1+side2<side3 || side1+side3<side2 || side2+side3<side1)
            throw new IllegalArgumentException("Sum of two triangle sides should be no less then third side");
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double trianglePerimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public double triangleArea() {
        double p = trianglePerimeter() / 2;
        return Math.sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));
    }


}
