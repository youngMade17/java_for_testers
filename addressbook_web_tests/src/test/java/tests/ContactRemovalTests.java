package tests;

import local.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!isContactPresent()) {
            createContact(new ContactData().withName("Mikhail3"));
        }
        removeContact();
    }

}
