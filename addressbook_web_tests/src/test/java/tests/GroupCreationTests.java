package tests;

import local.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static ArrayList<GroupData> groupProvider() {
        ArrayList<GroupData> result = new ArrayList<>();
        for (var name : List.of("", "group name")) {
            for (var header  : List.of("", "header name")) {
                for (var footer : List.of("", "footer name")) {
                    result.add(new GroupData(name, header, footer));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new GroupData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    public static ArrayList<GroupData> negativeGroupProvider() {
        ArrayList<GroupData> result = new ArrayList<>(List.of(
                new GroupData("group name ' ", "", "")
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
        //int n = 5;
        int groupCount = app.groups().getCount();
        //for (int i = 0; i < n; i++) {
        app.groups().createGroup(group);
        //}
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }




}
