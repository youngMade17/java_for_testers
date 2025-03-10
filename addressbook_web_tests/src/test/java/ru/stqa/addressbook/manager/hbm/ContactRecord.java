package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstName;
    @Column(name = "lastname")
    public String lastName;
    @Column(name = "address")
    public String address;
    @Column(name = "home")
    public String home;
    @Column(name = "mobile")
    public String mobile;
    @Column(name = "work")
    public String work;

    public Date deprecated = new Date();
    public String middlename = "default middlename";
    public String nickname = "default nickname";
    public String company = "default company";
    public String title = "default title";
    public String fax = "default fax";
    public String email = "default email";
    public String email2 = "default email2";
    public String email3 = "default email3";
    public String homepage = "default homepage";

    public ContactRecord() {
    }
    public ContactRecord(int id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;

    }
}
