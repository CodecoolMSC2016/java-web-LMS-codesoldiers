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
        System.out.println(dbm.loginUser("d@dc.hu", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220"));
        System.out.println(dbm.loginUser("d@dc.hu", "7110eda4d09e062aa5e4a390b0a572ac0d2c0222"));
        System.out.println(dbm.loginUser("d@df.hu", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220"));
    }
}
