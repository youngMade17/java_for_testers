package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.local.GroupData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static ArrayList<GroupData> groupProvider() throws IOException {
        ArrayList<GroupData> result = new ArrayList<>();
//        for (var name : List.of("", "group name")) {
//            for (var header  : List.of("", "header name")) {
//                for (var footer : List.of("", "footer name")) {
//                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
//                }
//            }
//        }
        // 2-ой способ чтения
        //var json = Files.readString(Paths.get("groups.json"));

        // 1-ый способ чтения через библиотеку Jackson и класс ObjectMapper
        //var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});

        // 3-й способ чтения
        var json = "";
        try (var reader = new FileReader("groups.json");
             var breader = new BufferedReader(reader)) {
            var line = breader.readLine();
            while (line != null) {
                json += line;
                line = breader.readLine();
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static ArrayList<GroupData> negativeGroupProvider() {
        ArrayList<GroupData> result = new ArrayList<>(List.of(
                new GroupData().withName("group name ' ").withHeader("").withFooter("")
        ));
        return result;
    }

    // В тесте ниже описан способ создания параметризированного теста с помощью фиксированных значений
//    @ParameterizedTest
//    @ValueSource(strings = {"group name", "group name '"})
//    public void canCreateGroup(String name) {
//        int groupCount = app.groups().getCount();
//        app.groups().createGroup(new GroupData(name, "Group header", "Group footer"));
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount+1, newGroupCount);
//    }
//

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(newGroups, oldGroups);
    }




}
