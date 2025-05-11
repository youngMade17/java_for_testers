package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.id("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.id("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public void signUp(String userName, String email, String password) {
        click(By.cssSelector("a[href='signup_page.php']"));
        type(By.name("username"), userName);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
        //type(By.name("password"), password);
        //click(By.cssSelector("a[href='login_page.php']"));
    }

    public void editAccount(String realName, String password) {
        type(By.id("realname"), realName);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
}
