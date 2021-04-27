package io.zipcoder.persistenceapp;

public class Home {
    int id;
    String address;
    String homeNumber;

    public Home(){}

    public Home(String a, String h){
        this.address=a;
        this.homeNumber=h;
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

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }
}
