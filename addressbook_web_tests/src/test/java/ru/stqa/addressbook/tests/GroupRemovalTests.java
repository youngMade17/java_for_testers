package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData()
                    .withName("Group name 2")
                    .withHeader("Group header 2")
                    .withFooter("Group footer2")
            );
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData()
                    .withName("Group name")
                    .withHeader("Group header")
                    .withFooter("Group footer")
            );
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }

}
