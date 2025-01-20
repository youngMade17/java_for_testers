package tests;

import local.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if ( !app.groups().isGroupPresent() ) {
            app.groups().createGroup(new GroupData("Group name 2", "Group header 2", "Group footer2"));
        }
        app.groups().removeGroup();
    }

}
