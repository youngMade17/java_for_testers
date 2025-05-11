package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        // Создать пользователя (адрес) на почтовом сервере (JamesHelper)
        // Заполнить форму создания и отправялем (браузер))
        // ЖДЕМ почту (MailHelper)
        // Nзвлекаем ссылку из письма
        // Проходим по ссылке и завершаем регистрацию пользователя (браузер)
        // Проверяем что пользователь может залогиниться (httpSessionHelper)

        var userName = CommonFunctions.randomString(10);
        var email = String.format("%s@localhost", userName);
        //app.JamesCli().addUser(email, "password");
        app.JamesApi().addUser(email, "password");
        app.session().signUp(userName, email, "password");
        var url = app.mail().extract(email, "password");
        app.driver().get(url);
        app.session().editAccount(userName, "password");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        app.http().login(userName, "password");
        app.http().isLoggedIn();
        //app.session().isLoggedIn();
    }

}
