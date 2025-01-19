package tests;

import local.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("Group name", "Group header", "Group footer"));
    }


    @Test
    public void canCreateGroupWithEmptyName() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithOnlyName() {
        app.openGroupsPage();
        GroupData group = new GroupData();
        GroupData groupWithName = group.withName("justName");
        app.createGroup(groupWithName);
    }
}
