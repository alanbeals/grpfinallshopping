package entities;

public class User {

    private int id;
    private String fullName;
    private String phone;
    private String address;

    public int getUserId() {

        return id;
    }
    public void setUserId(int id) {

        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {

        return phone;
    }
    public void setPhone(String phone) {

        this.phone = phone;
    }

    public String getAddress() {

        return address;
    }
    public void setAddress(String  address) {

        this.address = address;
    }

    public User (){};

    public User(int id, String fullName, String phone, String address)
    {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }
}
