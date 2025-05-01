package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        // Создать пользователя (адрес) на почтовом сервере (JamesHelper)
        // Заполнить форму создания и отправялем (браузер))
        // ЖДЕМ почту (MailHelper)
        // Nзвлекаем ссылку из письма
        // Проходим по ссылке и завершаем регистрацию пользователя (браузер)
        // Проверяем что пользователь может залогиниться (httpSessionHelper)
    }

}
