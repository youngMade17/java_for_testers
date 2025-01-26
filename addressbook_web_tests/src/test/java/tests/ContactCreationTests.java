package tests;

import local.ContactData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

    public static ArrayList<ContactData> contactProvider() {
        ArrayList<ContactData> arr = new ArrayList<>();
        for (var firstName : List.of("", "First Name")) {
            for (var middleName : List.of("", "Middle Name")) {
                for (var lastName : List.of("", "Last Name")) {
                    for (var address : List.of("", "Address")) {
                        for (var email : List.of("", "Email")) {
                            for (var mobile : List.of("", "Mobile")) {
                                arr.add(new ContactData(firstName, middleName, lastName, address, email, mobile));
                            }
                        }
                    }
                }
            }
        }

        for (var firstName : List.of("", "First Name")) {

            for (var middleName : List.of("", "Middle Name")) {
                arr.add(new ContactData(firstName, middleName, "", "", "", ""));
            }

            for (var lastName : List.of("", "Last Name")) {
                for (var address : List.of("", "Address")) {
                    arr.add(new ContactData(firstName, middleName, lastName, address, email, mobile));
                }
            }
        }
        return arr;
    }

    @Test
    public void canCreateContactWithBasicInfo() {
        app.contacts().createContact(new ContactData().withBasicInfo("1", "2", "3", "4", "5", "6"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        for (int i = 0; i < 5; i++) {
            app.contacts().createContact(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }

        app.contacts().createContact(contact);
    }


}
