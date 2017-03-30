package app;

import java.util.ArrayList;
import java.io.*;
public class User {
    private String username;
    private String emailAddress;
    private String role;
    private String password;


    public User(String username, String emailAddress, String role, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.role = role;
        this.password = password;

    }
    @Override
    public String toString(){
        return this.username + ";" + this.emailAddress + ";" + this.role ;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){return username;}

}



