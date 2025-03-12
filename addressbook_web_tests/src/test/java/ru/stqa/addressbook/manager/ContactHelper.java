package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.cssSelector("#maintable input[name='selected[]']"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        creationContactButton();
        contactHomePageReturn();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        creationContactButton();
        contactHomePageReturn();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        //selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        modificationContactButton();
        contactHomePageReturn();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        selectContact(contact);
        selectMainPageGroup(group);
        click(By.name("add"));
    }

    private void selectMainPageGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void initContactModification(ContactData contact) {
        //click(By.name("edit"));
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }

    public void removeContact(ContactData contact) {
        selectContact(contact);
        deleteContactButton();
    }

    public void removeAllContacts() {
        selectAllContacts();
        deleteContactButton();
    }

    private void deleteContactButton() {
        click(By.cssSelector("input[value='Delete']"));
    }

    private void selectContact(ContactData contact) {
        //click(By.cssSelector("#maintable input[name='selected[]']"));
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void contactHomePageReturn() {
        click(By.linkText("home page"));
    }

    private void creationContactButton() {
        click(By.xpath("//form[@name='theform']/input[@name='submit']"));
    }

    private void modificationContactButton() {
        click(By.xpath("//input[@name='update']"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        attach(By.name("photo"), contact.photo());
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

    private void initContactCreation() {
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

    public List<ContactData> getList() {
        ArrayList<ContactData> contacts = new ArrayList<>();
        var table = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : table) {
            //var name = tr.getText();
            var name = tr.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstName(name));
        }
        return contacts;
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
        selectContact(contact);
        click(By.name("remove"));
        click(By.cssSelector(String.format("a[href='./?group=%s']", group.id())));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public String getEmailsFromEditForm(ContactData contact) {
        initContactModification(contact);
        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");
        return email + "\n" + email2 + "\n" + email3;

    }

    public String getAddress(ContactData contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public String getAddressFromEditForm(ContactData contact) {
        initContactModification(contact);
        return manager.driver.findElement(By.name("address")).getText();
    }
}
