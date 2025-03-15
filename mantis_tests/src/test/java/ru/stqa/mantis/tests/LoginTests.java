package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() throws InterruptedException {
        app.session().login("administrator", "root");
        Thread.sleep(500);
        Assertions.assertTrue(app.session().isLoggedIn());
    }
}
