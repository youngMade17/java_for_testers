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
        String photo
) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo);
    }
    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, this.lastName, middleName, this.address, this.email, this.home, this.mobile, this.work, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, address, this.email, this.home, this.mobile, this.work, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, email, this.home, this.mobile, this.work, this.photo);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, home, this.mobile, this.work, this.photo);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.home, mobile, this.work, this.photo);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.home, this.mobile, work, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, "", this.mobile, this.work, photo);
    }


}
