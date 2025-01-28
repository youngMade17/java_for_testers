package tests;

import local.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithBasicInfo() {
        app.contacts().createContact(new ContactData().withBasicInfo("1", "2", "3", "4", "5", "6"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
    public static ArrayList<ContactData> contactProvider() {
        ArrayList<ContactData> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return arr;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        try {
            int contactCount = app.groups().getCount();
            app.contacts().createContact(contact);
            int newcontactCount = app.groups().getCount();
            Assertions.assertEquals(contactCount, newcontactCount);
        } catch (NoSuchElementException e) {
            System.out.println("---------------------------Error!---------------------------");
        }
    }
    public static ArrayList<ContactData> negativeContactProvider() {
        ArrayList<ContactData> arr = new ArrayList<>(List.of(
                new ContactData("contact name ' ", "", "", "", "", "")
        ));
        return arr;
    }


}
