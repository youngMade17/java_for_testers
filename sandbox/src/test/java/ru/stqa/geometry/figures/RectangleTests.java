package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {
    @Test
    void firstNegativeSide() {
        try {
            new Rectangle(-5, 5);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            //OK
        }
    }
    @Test
    void secondNegativeSide() {
        try {
            new Rectangle(5, -5);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            //OK
        }
    }

}
