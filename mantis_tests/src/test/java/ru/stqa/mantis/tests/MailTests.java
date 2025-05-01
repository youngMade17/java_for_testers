package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailTests extends TestBase{

    @Test
    void canDrainInBox() {
        app.mail().drain("user1@localhost", "password");
    }

    @Test
    void canReceiveEmail() {
        var messages =  app.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    // http://localhost/mantisbt-2.27.1/verify.php?id=3&confirm_hash=wk9QSdKC7KGxWK9EYdgA9AvBppdVj7VqX2mjMN2KXIuvEqb8o_zo_9cHcKn70HJhVAjT3HEwwgHb2PrlJGx7

    @Test
    void canExtractUrl() {
        var messages =  app.mail().receive("user1@localhost", "password", Duration.ofSeconds(10));
        var text = messages.get(0).content();
        Pattern pattern = Pattern.compile("http://\\S*");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}
