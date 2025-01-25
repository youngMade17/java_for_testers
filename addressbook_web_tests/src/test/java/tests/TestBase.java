package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome"));
    }

    public static String randomString(int count) {
        Random randomChar = new Random();
        String str = "";
        for (int i = 0; i < count; i++) {
            str = str + (char)('a' + randomChar.nextInt(27));
        }
        return str;
    }


}
