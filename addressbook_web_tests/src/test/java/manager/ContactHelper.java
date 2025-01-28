package manager;

import local.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.cssSelector("#maintable input[name='selected[]']"));
    }

    public void createContact(ContactData contact) {
        openCreationContactForm();
        fillContactFields(contact);
        creationContactButton();
        contactHomePageReturn();
    }

    public void removeContact() {
        selectContact();
        deleteContactButton();
    }

    public void removeAllContacts() {
        selectAllContacts();
        deleteContactButton();
    }

    private void deleteContactButton() {
        click(By.cssSelector("input[value='Delete']"));
    }

    private void selectContact() {
        click(By.cssSelector("#maintable input[name='selected[]']"));
    }

    private void contactHomePageReturn() {
        click(By.linkText("home page"));
    }

    private void creationContactButton() {
        click(By.xpath("//form[@name='theform']/input[@name='submit']"));
    }

    private void fillContactFields(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        // Заполнение остальных полей контакта
//        driver.findElement(By.name("nickname")).click();
//        driver.findElement(By.name("nickname")).sendKeys("Nickname");
//        driver.findElement(By.name("title")).click();
//        driver.findElement(By.name("title")).sendKeys("Title");
//        driver.findElement(By.name("company")).click();
//        driver.findElement(By.name("company")).sendKeys("Company");
//        driver.findElement(By.name("home")).click();
//        driver.findElement(By.name("theform")).click();
//        driver.findElement(By.name("home")).click();
//        driver.findElement(By.name("home")).sendKeys("Home");
//        driver.findElement(By.name("work")).click();
//        driver.findElement(By.name("work")).sendKeys("Work");
//        driver.findElement(By.name("fax")).click();
//        driver.findElement(By.name("fax")).sendKeys("Fax");
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
    }

    private void openCreationContactForm() {
        click(By.linkText("add new"));
    }


    public int getCount() {
        return manager.driver.findElements(By.cssSelector("#maintable input[name='selected[]']")).size();
    }

    private void selectAllContacts() {
        List<WebElement> contactsList = manager.driver.findElements(By.cssSelector("#maintable input[name='selected[]']"));
        for (WebElement contactElement :contactsList) {
            contactElement.click();
        }
    }
}
