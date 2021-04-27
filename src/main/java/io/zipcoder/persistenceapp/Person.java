package io.zipcoder.persistenceapp;

public class Person {
    int id;
    String firstName;
    String lastName;
    String mobile;
    java.sql.Date birthday;
    int homeId;

    public Person(){}

    public Person(String first, String last, String phone, java.sql.Date date, int home){
        firstName=first;
        lastName=last;
        mobile=phone;
        birthday=date;
        homeId=home;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", birthday='" + birthday + '\'' +
                ", homeId=" + homeId +
                '}';
    }
}
