package ru.stqa.addressbook.model;

public record ContactData(
        String id,
        String firstName,
        String lastName,
        String middleName,
        String address,
        String email,
        String home,
        String mobile,
        String work,
        String photo,
        String email2,
        String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, this.lastName, middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, address, this.email, this.home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, email, this.home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, home, this.mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.home, mobile, this.work, this.photo, this.email2, this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.home, this.mobile, work, this.photo, this.email2, this.email3);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, "", this.mobile, this.work, photo, this.email2, this.email3);
    }

    public ContactData withEmail2(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, "", this.mobile, this.work, photo, this.email2, this.email3);
    }

    public ContactData withEmail3(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, "", this.mobile, this.work, photo, this.email2, this.email3);
    }


}
