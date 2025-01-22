package tests;

import local.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupModificationTests extends TestBase {
    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData().withName("Modify Group"));
        }
        app.groups().modifyGroup(new GroupData().withName("Modified name"));
    }
}
