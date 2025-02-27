package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Mikhail3"));
        }
        //app.contacts().removeContact();

        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newGroups = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Mikhail3"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
