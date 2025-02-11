package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.local.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contacts().createContact(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"))
        );
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        //int newContactCount = app.contacts().getCount();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(newContacts.get(newContacts.size()-1).id())
                .withLastName("")
                .withMiddleName("")
                .withAddress("")
                .withEmail("")
                .withMobile("")
        );
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
    public static ArrayList<ContactData> contactProvider() {
        ArrayList<ContactData> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr.add(new ContactData()
                    .withFirstName(CommonFunctions.randomString(i * 10))
                    .withLastName(CommonFunctions.randomString(i * 10))
                    .withMiddleName(CommonFunctions.randomString(i * 10))
                    .withAddress(CommonFunctions.randomString(i * 10))
                    .withEmail(CommonFunctions.randomString(i * 10))
                    .withMobile(CommonFunctions.randomString(i * 10))
            );
        }
        return arr;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        try {
            int contactCount = app.groups().getCount();
            app.contacts().createContact(contact);
            int newContactCount = app.groups().getCount();
            Assertions.assertEquals(contactCount, newContactCount);
        } catch (NoSuchElementException e) {
            System.out.println("---------------------------Error!---------------------------");
        }
    }
    public static ArrayList<ContactData> negativeContactProvider() {
        ArrayList<ContactData> arr = new ArrayList<>(List.of(
                new ContactData().withFirstName("contact name ' ")
        ));
        return arr;
    }


}
