package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byteforce on 2017.03.29..
 */
public class DataManager {
    private List<User> userDatabase = new ArrayList<>();


    public void addUser(User user) {
        userDatabase.add(user);
    }

    public List<User> getUserDatabase() {
        return userDatabase;
    }

    public void printUsers(String role, ArrayList<User> database) {
        if(role.equals("mentor")) {
            for(User user: database) {
                System.out.println(String.format("User email: %s Role: %s", user.getEmail(), user.getRole()));
            }
        } else {
            for(User user: database) {
                if(!user.getRole().equals("mentor")) {
                    System.out.println(String.format("User email: %s Role: %s", user.getEmail(), user.getRole()));
                }
            }
        }
    }

    public void setUserDatabase(CSVRW csvrw) {
        this.userDatabase = csvrw.readUserDatabase();

    }
}
