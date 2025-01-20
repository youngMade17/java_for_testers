package tests;

import local.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithBasicInfo() {
        app.contacts().createContact(new ContactData().withBasicInfo("1", "2", "2", "2", "2", "2"));
    }

}
