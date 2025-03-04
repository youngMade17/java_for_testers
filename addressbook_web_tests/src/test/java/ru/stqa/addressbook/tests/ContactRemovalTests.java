package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        //if (app.contacts().getCount() == 0) {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Mikhail3").withPhoto(randomFile("src/test/resources/images")));
        }
        //app.contacts().removeContact();

        //var oldContacts = app.contacts().getList();
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        int index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newGroups = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        //if (app.contacts().getCount() == 0) {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Mikhail3").withPhoto(randomFile("src/test/resources/images")));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

    @Test
    public void removeContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData()
                    .withName("Group name 2")
                    .withHeader("Group header 2")
                    .withFooter("Group footer2")
            );
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);

        app.contacts().createContact(contact, group);
        app.contacts().removeContactFromGroup(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size(), newRelated.size()-1);
    }

}
