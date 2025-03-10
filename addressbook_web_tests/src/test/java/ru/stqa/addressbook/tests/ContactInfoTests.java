package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(
                ContactData::id,
                contact -> Stream.of(contact.home(), contact.mobile(), contact.work())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);

//        for (var contact : contacts) {
//            //var phones = app.contacts().getPhones(contact);
//            var expected = Stream.of(contact.home(), contact.mobile(), contact.work())
//                    .filter(s -> s != null && !"".equals(s))
//                    .collect(Collectors.joining("\n"));
//            Assertions.assertEquals(expected, phones.get(contact.id()));
//        }
    }
}
