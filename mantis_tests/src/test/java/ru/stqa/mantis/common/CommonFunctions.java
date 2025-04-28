package ru.stqa.mantis.common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {

    public static String randomString(int count) {
        Random rdn = new Random();
        Supplier<Integer> integerSupplier = () -> rdn.nextInt(26);
        var str = Stream.generate(integerSupplier)
            .limit(count)
            .map(integer -> 'a' + integer)
            .map(Character::toString)
            .collect(Collectors.joining());
//        String str = "";
//        for (int i = 0; i < count; i++) {
//            str = str + (char)('a' + rdn.nextInt(27));
//        }
        return str;
    }
}
