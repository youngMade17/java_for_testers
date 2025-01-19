package tests;

import local.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithBasicInfo() {
        ContactData contactData = new ContactData();
        ContactData contactDataWithName = contactData.withBasicInfo("1", "2", "2", "2", "2", "2");
        createContact(contactDataWithName);
    }

}
