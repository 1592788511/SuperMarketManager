package moudel;

public class supplier {

    private int id;
    private String address;
    private String contact;
    private String name;
    private String number;
    private String remarks;

    public supplier(){}

    public supplier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public supplier(String address, String contact, String name, String number, String remarks) {
        this.address = address;
        this.contact = contact;
        this.name = name;
        this.number = number;
        this.remarks = remarks;
    }

    public supplier(int id, String address, String contact, String name, String number, String remarks) {
        this.id = id;
        this.address = address;
        this.contact = contact;
        this.name = name;
        this.number = number;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return this.name;

    }

}
