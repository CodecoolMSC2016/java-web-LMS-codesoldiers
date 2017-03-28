import java.util.ArrayList;
import java.util.List;

public class Users {
    public List users;

    public boolean validate(String username) {
        if (users == null || users.size() < 1) {
            readUserDatabase();
            System.out.println("anyád");
        }
        return users.contains(username);
    }

    public void readUserDatabase() {
        users = new ArrayList<String>();
        users.add("dávid");
        users.add("krisz");
        users.add("laci");
        users.add("tomi");
    }

    public void writeUserDatabase() {

    }

    public boolean addUser() {
        return false;
    }
}
