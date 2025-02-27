package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroup(GroupData group) {
        //var oldGroups = app.groups().getList();
        //var oldGroups = app.jdbc().getGroupList();
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        //var newGroups = app.groups().getList();
        //var newGroups = app.jdbc().getGroupList();
        var newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size()-1).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    // Дополнительный тест для сверки списка из UI со списком из БД
    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateGroupUICheck(GroupData group) {
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        // Сохранение списков до и после создания новой группы
        var oldGroupsOfDB = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroupsOfUI = app.groups().getList();
        // Сортировка обновленного списка
        newGroupsOfUI.sort(compareById);
        // Добавление последней созданной группы в новый список на основе старого списка
        var expectedListUI = new ArrayList<>(oldGroupsOfDB);
        var maxId = newGroupsOfUI.get(newGroupsOfUI.size()-1).id();
        expectedListUI.add(group.withId(maxId).withHeader("").withFooter(""));
        // Сброс значений хедоров и футеров в списке из БД
        for (int i = 0; i < expectedListUI.size(); i++) {
            expectedListUI.set(i, expectedListUI.get(i).withHeader("").withFooter(""));
        }
        // Сортировка списка из БД перед сравнением
        expectedListUI.sort(compareById);
        Assertions.assertEquals(newGroupsOfUI, expectedListUI);
    }
    public static List<GroupData> singleRandomGroup() throws IOException {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(20))
                .withFooter(CommonFunctions.randomString(30))
        );
    }
    public static ArrayList<GroupData> groupProvider() throws IOException {
        ArrayList<GroupData> result = new ArrayList<>();
//        for (var name : List.of("", "group name")) {
//            for (var header  : List.of("", "header name")) {
//                for (var footer : List.of("", "footer name")) {
//                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }

        // 1-ый способ чтения через библиотеку Jackson и класс ObjectMapper
        //var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});

        // 2-ой способ чтения
        //var json = Files.readString(Paths.get("groups.json"));

        // 3-й способ чтения
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//             var breader = new BufferedReader(reader)) {
//            var line = breader.readLine();
//            while (line != null) {
//                json += line;
//                line = breader.readLine();
//            }
//        }
        // Объект для чтения json
        //ObjectMapper mapper = new ObjectMapper();

        //Объект для чтения yaml
        //ObjectMapper mapper = new YAMLMapper();

        //Объект для чтения xml
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>(){});
        result.addAll(value);

        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }
    public static ArrayList<GroupData> negativeGroupProvider() {
        ArrayList<GroupData> result = new ArrayList<>(List.of(
                new GroupData().withName("group name ' ").withHeader("").withFooter("")
        ));
        return result;
    }

    // Ниже описан способ создания параметризированного теста с помощью фиксированных значений
//    @ParameterizedTest
//    @ValueSource(strings = {"group name", "group name '"})
//    public void canCreateGroup(String name) {
//        int groupCount = app.groups().getCount();
//        app.groups().createGroup(new GroupData(name, "Group header", "Group footer"));
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount+1, newGroupCount);
//    }
//

}
