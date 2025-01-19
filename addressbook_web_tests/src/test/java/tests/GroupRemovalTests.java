package tests;

import local.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        app.openGroupsPage();
        if ( !app.isGroupPresent() ) {
            app.createGroup(new GroupData("Group name 2", "Group header 2", "Group footer2"));
        }
        app.removeGroup();
    }

}
