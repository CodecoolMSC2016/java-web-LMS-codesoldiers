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

    public void setUserDatabase(CSVRW csvrw) {
        this.userDatabase = csvrw.readCSVDatabase();

    }
}
