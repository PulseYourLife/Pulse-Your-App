package Logic;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String address;
    private String password;
    private String weight;
    private String height;


    public User(String name, String address, String password, String weight, String height){
        this.name = name;
        this.address = address;
        this.password = password;
        this.weight = weight;
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
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
