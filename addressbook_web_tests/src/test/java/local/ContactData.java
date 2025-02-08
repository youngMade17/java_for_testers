package local;

public record ContactData(String id, String firstName, String lastName, String middleName, String address, String email, String mobile) {
    public ContactData() {
        this("", "", "", "", "", "", "");
    }

    public ContactData withBasicInfo(String id, String firstName, String lastName, String middleName, String address, String email, String mobile) {
        return new ContactData(id, firstName, lastName, middleName, address, email, mobile);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.middleName, this.address, this.email, this.mobile);
    }
    public ContactData withName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.middleName, this.address, this.email, this.mobile);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.middleName, this.address, this.email, this.mobile);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, this.lastName, middleName, this.address, this.email, this.mobile);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, address, this.email, this.mobile);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, email, this.mobile);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.middleName, this.address, this.email, mobile);
    }


}
