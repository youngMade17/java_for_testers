package tests;

import local.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withName("Mikhail3"));
        }
        app.contacts().removeContact();
    }

    @Test
    public void canRemoveAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withName("Mikhail3"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
