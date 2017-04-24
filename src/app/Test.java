package app;

/**
 * Created by david_szilagyi on 2017.04.24..
 */
public class Test {
    public static void main(String[] args) {
        DatabaseManager dbm = DatabaseManager.getInstance();
        //dbm.printUsers();
        dbm.addNewUser("David1", "d@dc.hu", "mentor", "1234");
        dbm.printUsers();
        System.out.println(dbm.loginUser("d@dc.hu", dbm.sha1("1234")));
        System.out.println(dbm.loginUser("d@dc.hu", dbm.sha1("12345")));
        System.out.println(dbm.loginUser("d@df.hu", dbm.sha1("1234")));
    }
}
