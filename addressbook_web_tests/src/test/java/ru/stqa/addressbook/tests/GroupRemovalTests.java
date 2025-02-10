package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.local.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData()
                    .withName("Group name 2")
                    .withHeader("Group header 2")
                    .withFooter("Group footer2")
            );
        }
//        int groupCount = app.groups().getCount();
//        app.groups().removeGroup();
//        int newGroupCount = app.groups().getCount();
//        Assertions.assertEquals(groupCount - 1, newGroupCount);

        var oldGroups = app.groups().getList();
        var rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData()
                    .withName("Group name")
                    .withHeader("Group header")
                    .withFooter("Group footer")
            );
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
