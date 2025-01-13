package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double side1, double side2, double side3) {
//    private double side1;
//    private double side2;
//    private double side3;

    public Triangle {
        if (side1 < 0 || side2 < 0 || side3 < 0)
            throw new IllegalArgumentException("Triangle sides should be non-negative");
        if (side1+side2<side3 || side1+side3<side2 || side2+side3<side1)
            throw new IllegalArgumentException("Sum of two triangle sides should be no less then third side");
//        this.side1 = side1;
//        this.side2 = side2;
//        this.side3 = side3;
    }

    public double trianglePerimeter() {
        return this.side1 + this.side2 + this.side3;
    }

    public double triangleArea() {
        double p = trianglePerimeter() / 2;
        return Math.sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(side1, triangle.side1) == 0 && Double.compare(side2, triangle.side2) == 0 && Double.compare(side3, triangle.side3) == 0
        || Double.compare(side1, triangle.side1) == 0 && Double.compare(side2, triangle.side3) == 0 && Double.compare(side3, triangle.side2) == 0
        || Double.compare(side1, triangle.side2) == 0 && Double.compare(side2, triangle.side1) == 0 && Double.compare(side3, triangle.side2) == 0
        || Double.compare(side1, triangle.side2) == 0 && Double.compare(side2, triangle.side3) == 0 && Double.compare(side3, triangle.side1) == 0
        || Double.compare(side1, triangle.side3) == 0 && Double.compare(side2, triangle.side1) == 0 && Double.compare(side3, triangle.side2) == 0
        || Double.compare(side1, triangle.side3) == 0 && Double.compare(side2, triangle.side2) == 0 && Double.compare(side3, triangle.side1) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
