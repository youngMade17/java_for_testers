package manager;

import local.ContactData;
import org.openqa.selenium.By;

public class ContactHelper {
    private final ApplicationManager manager;

    public ContactHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.cssSelector("#maintable input[name='selected[]']"));
    }

    public void createContact(ContactData contact) {
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstName());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middleName());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastName());
//        driver.findElement(By.name("nickname")).click();
//        driver.findElement(By.name("nickname")).sendKeys("Nickname");
//        driver.findElement(By.name("title")).click();
//        driver.findElement(By.name("title")).sendKeys("Title");
//        driver.findElement(By.name("company")).click();
//        driver.findElement(By.name("company")).sendKeys("Company");
//        driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
//        driver.findElement(By.name("home")).click();
//        driver.findElement(By.name("theform")).click();
//        driver.findElement(By.name("home")).click();
//        driver.findElement(By.name("home")).sendKeys("Home");
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.mobile());
//        driver.findElement(By.name("work")).click();
//        driver.findElement(By.name("work")).sendKeys("Work");
//        driver.findElement(By.name("fax")).click();
//        driver.findElement(By.name("fax")).sendKeys("Fax");
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
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
        manager.driver.findElement(By.xpath("//form[@name='theform']/input[@name='submit']")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }

    public void removeContact() {
        manager.driver.findElement(By.cssSelector("#maintable input[name='selected[]']")).click();
        manager.driver.findElement(By.cssSelector("input[value='Delete']")).click();
    }
}
