package local;

public record ContactData(String firstName, String lastName, String middleName, String address, String email, String mobile) {
    public ContactData() {
        this("", "", "", "", "", "");
    }

    public ContactData withBasicInfo(String firstName, String lastName, String middleName, String address, String email, String mobile) {
        return new ContactData(firstName, lastName, middleName, address, email, mobile);
    }

    public ContactData withName(String firstName) {
        return new ContactData(firstName, this.lastName, this.middleName, this.address, this.email, this.mobile);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.firstName, lastName, this.middleName, this.address, this.email, this.mobile);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.firstName, this.lastName, middleName, this.address, this.email, this.mobile);
    }
}
