package tests;

import local.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static ArrayList<GroupData> groupProvider() {
        ArrayList<GroupData> result = new ArrayList<>();
        for (var name : List.of("", "group name")) {
            for (var header  : List.of("", "header name")) {
                for (var footer : List.of("", "footer name")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(randomString(i * 10))
                    .withHeader(randomString(i * 10))
                    .withFooter(randomString(i * 10))
            );
        }
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
        try {
            var oldGroups = app.groups().getList();
            app.groups().createGroup(group);
            var newGroups = app.groups().getList();
            Assertions.assertEquals(newGroups, oldGroups);
        } catch (NoSuchElementException e) {
            System.out.println("---------------------------Error!---------------------------");
        }
    }




}
