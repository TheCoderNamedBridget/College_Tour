package com.example.collegetour;

public class Staff {

    String staffName = "";
    String staffEmail = "";
    String staffPhone = "";

    public Staff(String name, String email, String phone){
        staffName = name;
        staffEmail = email;
        staffPhone = phone;
    }

    public Staff(String name, String email){
        staffName = name;
        staffEmail = email;
    }

    public String getName(){
        return staffName;
    }

    public String getEmail(){
        return staffEmail;
    }

    public String getPhone(){
        return staffPhone;
    }
}
