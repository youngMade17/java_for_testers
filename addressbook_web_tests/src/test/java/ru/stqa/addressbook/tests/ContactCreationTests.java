package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void canCreateContactInGroup() {
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
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size()+1, newRelated.size());
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
                .withPhoto(newContacts.get(newContacts.size()-1).photo())
        );
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
    public static ArrayList<ContactData> contactProvider() throws IOException {
        ArrayList<ContactData> arr = new ArrayList<>();
        // 0. Первоначальный способ генерации данных
//        for (int i = 0; i < 5; i++) {
//            arr.add(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(i * 10))
//                    .withLastName(CommonFunctions.randomString(i * 10))
//                    .withMiddleName(CommonFunctions.randomString(i * 10))
//                    .withAddress(CommonFunctions.randomString(i * 10))
//                    .withEmail(CommonFunctions.randomString(i * 10))
//                    .withMobile(CommonFunctions.randomString(i * 10))
//                    .withPhoto(randomFile("src/test/resources/images"))
//            );
//        }

        // 1. Чтение данных из json файла
//        var json = Files.readString(Paths.get("contacts.json"));
//        ObjectMapper mapper = new ObjectMapper();
//        var value = mapper.readValue(json, new TypeReference<List<ContactData>>(){});
//        arr.addAll(value);

        // 2. Чтение данных из xml файла
//        ObjectMapper xmlMapper = new XmlMapper();
//        var value = xmlMapper.readValue(new File("contacts.xml" ), new TypeReference<List<ContactData>>(){});
//        arr.addAll(value);

        // 3. Чтение данных из yaml файла
//        var yml = "";
//        try (FileReader fileReader = new FileReader("contacts.yaml");
//             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
//            var line = bufferedReader.readLine();
//            while (line != null) {
//                yml += line;
//                line = bufferedReader.readLine();
//            }
//        }
        ObjectMapper mapper = new YAMLMapper();
        var value = mapper.readValue(new File("contacts.yaml"), new TypeReference<List<ContactData>>(){});
        arr.addAll(value);
        return arr;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.groups().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.groups().getCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }
    public static ArrayList<ContactData> negativeContactProvider() {
        ArrayList<ContactData> arr = new ArrayList<>(List.of(
                new ContactData().withFirstName("contact name ' ")
        ));
        return arr;
    }


}
