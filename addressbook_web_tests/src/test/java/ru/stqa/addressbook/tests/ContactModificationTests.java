package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    void canModifyContact() {
//        if (app.contacts().getCount() == 0) {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Mikhail3").withPhoto(randomFile("src/test/resources/images")));
        }
        //var oldContacts = app.contacts().getList();
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());
        ContactData testData = new ContactData().withFirstName("modified name").withPhoto(randomFile("src/test/resources/images"));
        app.contacts().modifyContact(oldContacts.get(index), testData);
        //var newContacts = app.contacts().getList();
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()).withPhoto(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            //newGroups.sort(Comparator.comparingInt(o -> Integer.parseInt(o.id())));
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
