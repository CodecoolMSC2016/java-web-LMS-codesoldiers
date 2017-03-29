import java.util.ArrayList;
import java.io.*;
public class Register{
    protected String userName;
    protected String emailAddress;
    protected String role;


    public Register(String userName, String emailAddress , String role) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.role = role;

    }
    @Override
    public String toString(){
        return this.userName + ";" + this.emailAddress + ";" + this.role ;
    }

}



