package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.manager.ApplicationManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            app = new ApplicationManager();
        }
        var properties = new Properties();
        properties.load(new FileReader(System.getProperty("target", "local.properties")));
        app.init(System.getProperty("browser", "chrome"), properties);
    }

    @AfterAll
    static void checkDataBaseСonsistency() {
        app.jdbc().checkСonsistency();
    }

    public static String randomFile(String dir) {
        var fileNames = new File(dir).list();
        Random rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }


}
