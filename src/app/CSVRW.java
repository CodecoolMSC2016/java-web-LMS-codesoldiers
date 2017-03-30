package app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVRW {
    private File userDatabaseCsv = new File("userdatabase.csv");
    private BufferedReader br = new BufferedReader(new FileReader(userDatabaseCsv));

    public CSVRW() throws FileNotFoundException {
    }

    public void saveToCSV(ArrayList userDatabase) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(userDatabaseCsv);
        String stringbuilder = null;
        pw.append("Username,Emailaddress,Role");
        for (Object register : userDatabase) {
            pw.append(register.toString());
        }

    }

    public List<User> readCSVDatabase(){
        String line = "";
        List<User> users = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                users.add(new User(temp[0], temp[1], temp[2], temp[3]));

            }
        } catch (IOException e) {
            e.printStackTrace();
            }
        return users;
    }


}
