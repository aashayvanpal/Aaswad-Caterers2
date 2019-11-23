package com.example.aaswadcaterers;

public class saveuser {

    public String fullname;
    public String username;
    public String password;
    public String caterer;
    public String email;
    public String conf_password ;
    public String isTeacher;

    public saveuser(){

    }


    public saveuser(String fullname, String username, String password,String caterer , String email,String conf_password) {
        this.fullname = fullname;
        this.username = username;
        this.password= password;
        this.caterer = caterer;
        this.email = email;
        this.conf_password=conf_password;
    }
}