package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(
                    new ContactData()
                            .withFirstName(CommonFunctions.randomString(10))
                            .withLastName(CommonFunctions.randomString(10))
                            .withPhoto(randomFile("src/test/resources/images"))
            );
        }
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

    @Test
    void testEmails() {
        var contacts = app.hbm().getContactList();
        if (contacts.size() == 0) {
            app.contacts().createContact(
                    new ContactData()
                            .withFirstName(CommonFunctions.randomString(10))
                            .withLastName(CommonFunctions.randomString(10))
                            .withPhoto(randomFile("src/test/resources/images"))
                            .withEmail(CommonFunctions.randomString(10))
                            .withEmail2(CommonFunctions.randomString(10))
                            .withEmail3(CommonFunctions.randomString(10))
            );
        }
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = app.contacts().getEmailsFromEditForm(contact);
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testMailAddress() {
        var contacts = app.hbm().getContactList();
        if (contacts.size() == 0) {
            app.contacts().createContact(
                    new ContactData()
                            .withFirstName(CommonFunctions.randomString(10))
                            .withLastName(CommonFunctions.randomString(10))
                            .withPhoto(randomFile("src/test/resources/images"))
                            .withEmail(CommonFunctions.randomString(10))
                            .withEmail2(CommonFunctions.randomString(10))
                            .withEmail3(CommonFunctions.randomString(10))
                            .withAddress(CommonFunctions.randomString(10))
            );
        }
        var contact = contacts.get(0);
        var emails = app.contacts().getAddress(contact);
        var expected = app.contacts().getAddressFromEditForm(contact);
        Assertions.assertEquals(expected, emails);
    }


}
