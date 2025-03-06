import ru.stqa.geometry.figures.Square;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
//        var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
//
//        Square.printSquareArea(new Square(7.0));
//        Square.printSquareArea(new Square(5.0));
//        Square.printSquareArea(new Square(3.0));
//
//        Rectangle.printRectangleArea(3.0, 5.0);
//        Rectangle.printRectangleArea(7.0, 9.0);
//
//        Consumer<Square> print = square -> Square.printSquareArea(square);
//        squares.forEach(print);

        Supplier<Square> randSquare = () -> new Square(10.0);
        var squares = Stream.generate(randSquare).limit(5);

        Consumer<Square> print = square -> Square.printSquareArea(square);
        //squares.peek().forEach(print);
    }
}
