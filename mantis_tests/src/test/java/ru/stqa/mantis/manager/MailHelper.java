package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase{
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String userName, String password, Duration duration) {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
            try {
                Folder inbox = getInbox(userName, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();
                var result =  Arrays.stream(messages)
                        .map(m -> {
                            try {
                                return new MailMessage().withFrom(m.getFrom()[0].toString()).withContent((String) m.getContent());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close();
                if (result.size() > 0) {
                    return result;
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail");
    }

    public void drain(String userName, String password) {
        try {
            Folder inbox = getInbox(userName, password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extract(String email, String password) {
        var messages = receive(email, password, Duration.ofSeconds(10));
        var text = messages.get(0).content();
        Pattern pattern = Pattern.compile("http://\\S*");
        Matcher matcher = pattern.matcher(text);
        var url = "";
        while (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }

    private static Folder getInbox(String userName, String password) {
        try {
            Session session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            store.connect("localhost", userName, password);
            var inbox = store.getFolder("INBOX");
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
