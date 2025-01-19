package tests;

import local.ContactData;
import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;

public class TestBase {
    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init();
    }

    protected boolean isContactPresent() {
        return app.isElementPresent(By.cssSelector("#maintable input[name='selected[]']"));
    }

    protected void createContact(ContactData contact) {
        ApplicationManager.driver.findElement(By.linkText("add new")).click();
        ApplicationManager.driver.findElement(By.name("firstname")).click();
        ApplicationManager.driver.findElement(By.name("firstname")).sendKeys(contact.firstName());
        ApplicationManager.driver.findElement(By.name("middlename")).click();
        ApplicationManager.driver.findElement(By.name("middlename")).sendKeys(contact.middleName());
        ApplicationManager.driver.findElement(By.name("lastname")).click();
        ApplicationManager.driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
//        driver.findElement(By.name("nickname")).click();
//        driver.findElement(By.name("nickname")).sendKeys("Nickname");
//        driver.findElement(By.name("title")).click();
//        driver.findElement(By.name("title")).sendKeys("Title");
//        driver.findElement(By.name("company")).click();
//        driver.findElement(By.name("company")).sendKeys("Company");
//        driver.findElement(By.name("address")).click();
        ApplicationManager.driver.findElement(By.name("address")).sendKeys(contact.address());
//        driver.findElement(By.name("home")).click();
//        driver.findElement(By.name("theform")).click();
//        driver.findElement(By.name("home")).click();
//        driver.findElement(By.name("home")).sendKeys("Home");
        ApplicationManager.driver.findElement(By.name("mobile")).click();
        ApplicationManager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
//        driver.findElement(By.name("work")).click();
//        driver.findElement(By.name("work")).sendKeys("Work");
//        driver.findElement(By.name("fax")).click();
//        driver.findElement(By.name("fax")).sendKeys("Fax");
        ApplicationManager.driver.findElement(By.name("email")).click();
        ApplicationManager.driver.findElement(By.name("email")).sendKeys(contact.email());
//        driver.findElement(By.name("email2")).click();
//        driver.findElement(By.name("email2")).sendKeys("Email2");
//        driver.findElement(By.name("email3")).click();
//        driver.findElement(By.name("email3")).sendKeys("Email3");
//        driver.findElement(By.name("homepage")).click();
//        driver.findElement(By.name("homepage")).sendKeys("Homepage");
//        driver.findElement(By.name("bday")).click();
//        {
//            WebElement dropdown = driver.findElement(By.name("bday"));
//            dropdown.findElement(By.xpath("//option[. = '23']")).click();
//        }
//        driver.findElement(By.name("bmonth")).click();
//        {
//            WebElement dropdown = driver.findElement(By.name("bmonth"));
//            dropdown.findElement(By.xpath("//option[. = 'January']")).click();
//        }
//        driver.findElement(By.name("byear")).click();
//        driver.findElement(By.name("byear")).sendKeys("1999");
//        driver.findElement(By.name("aday")).click();
//        driver.findElement(By.name("aday")).click();
//        {
//            WebElement dropdown = driver.findElement(By.name("aday"));
//            dropdown.findElement(By.xpath("//option[. = '30']")).click();
//        }
//        driver.findElement(By.name("amonth")).click();
//        {
//            WebElement dropdown = driver.findElement(By.name("amonth"));
//            dropdown.findElement(By.xpath("//option[. = 'December']")).click();
//        }
//        driver.findElement(By.name("ayear")).click();
//        driver.findElement(By.name("ayear")).sendKeys("2017");
//        driver.findElement(By.name("new_group")).click();
//        driver.findElement(By.name("new_group")).click();
//        driver.findElement(By.cssSelector("input:nth-child(75)")).click();
        ApplicationManager.driver.findElement(By.xpath("//form[@name='theform']/input[@name='submit']")).click();
        ApplicationManager.driver.findElement(By.linkText("home page")).click();
    }

    public void removeContact() {
        ApplicationManager.driver.findElement(By.cssSelector("#maintable input[name='selected[]']")).click();
        ApplicationManager.driver.findElement(By.cssSelector("input[value='Delete']")).click();
    }




}
