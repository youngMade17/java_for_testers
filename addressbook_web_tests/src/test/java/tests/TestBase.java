package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Paths;
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

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        Random rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }


}
