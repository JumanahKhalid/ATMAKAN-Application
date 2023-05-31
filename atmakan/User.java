package com.example.atmakan;

public class User {
    private String FirstName, LastName, Email, Age, ID, Password,identity,userStatus;

    public User(String firstName, String lastName, String email, String age, String ID, String password, String identity,String userStatus) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Age = age;
        this.ID = ID;
        Password = password;
        this.identity = identity;
        this.userStatus = userStatus;
    }

    public User() {
    }
    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}