package com.bydeone.agrimobi.Models;

public class User {

    public String userName;
    public String userPassword;
    public String userPhone;
    public String userAdress;

    public User(){

    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(String userName, String userPhone, String userAdress) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAdress = userAdress;
    }

    public User(String userName, String userPassword, String userPhone, String userAdress) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAdress = userAdress;
    }
}
