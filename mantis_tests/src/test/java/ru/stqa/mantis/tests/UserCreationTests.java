package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

public class UserCreationTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canCreateUserByDeveloperMail() {
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        var password = "password";

//        app.session().signUp(userName, email, "password");
//        var url = app.mail().extract(email, "password");
//        app.driver().get(url);
//        app.session().editAccount(userName, "password");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        app.http().login(userName, "password");
//        app.http().isLoggedIn();
    }

    @AfterEach
    void canDeleteUserByDeveloperMail() {
        app.developerMail().deleteUser(user);
    }

}
