package app;

import java.util.ArrayList;

/**
 * Created by david_szilagyi on 2017.04.24..
 */
public class Test {
    public static void main(String[] args) {
        DatabaseManager dbm = DatabaseManager.getInstance();
        //dbm.printUsers();
        //User user1 = new User("David1", "d@dc.hu", "mentor", dbm.sha1("1234"));
        //User user2 = new User("David2", "d@hh.hu", "mentor", dbm.sha1("aBcd"));
        //User user3 = new User("David3", "d@hhc.hu", "mentor", dbm.sha1("abcd"));
        //dbm.addNewUser(user1);
        //dbm.addNewUser(user2);
        //dbm.addNewUser(user3);
        //dbm.printUsers();
        dbm.createUserList();
        ArrayList<User> userList = dbm.getUserList();
        for(User user: userList) {
            System.out.println(user.toString());
        }
        System.out.println(dbm.checkPass("d@dc.hu", dbm.sha1("1234"))); //true
        System.out.println(dbm.checkPass("d@dc.hu", dbm.sha1("12345"))); //false
        System.out.println(dbm.checkPass("d@df.hu", dbm.sha1("1234"))); //false
        System.out.println(dbm.checkPass("d@hh.hu", dbm.sha1("aBCd"))); //false
        System.out.println(dbm.checkPass("d@hh.hu", dbm.sha1("aBcd"))); //true
        System.out.println(dbm.checkPass("d@hhc.hu", dbm.sha1("abcd"))); //true
        System.out.println(dbm.checkPass("d@hhc.hu", dbm.sha1("abcD"))); //false
    }
}
