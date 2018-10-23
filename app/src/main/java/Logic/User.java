package Logic;

public class User {
    private String name;
    private String address;
    private String password;
    public User(String name, String address, String password){
        this.name = name;
        this.address = address;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
