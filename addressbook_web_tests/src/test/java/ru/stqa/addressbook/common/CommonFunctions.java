package ru.stqa.addressbook.common;

import java.util.Random;

public class CommonFunctions {

    public static String randomString(int count) {
        Random randomChar = new Random();
        String str = "";
        for (int i = 0; i < count; i++) {
            str = str + (char)('a' + randomChar.nextInt(27));
        }
        return str;
    }
}
