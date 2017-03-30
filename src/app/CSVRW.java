package app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVRW {
    private File userDatabaseCsv = new File("userdatabase.csv");
    private FileReader fr;

    public CSVRW() {
        try {
            fr = new FileReader(userDatabaseCsv);
        } catch (FileNotFoundException fnfe) {
            try {
                userDatabaseCsv.createNewFile();
                fr = new FileReader(userDatabaseCsv);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void saveToCSV(List<User> userDatabase) throws IOException {
        PrintWriter pw = new PrintWriter(userDatabaseCsv);
        // Username,EmailAddress,Role,Password
        for (Object register : userDatabase) {
            pw.append(register.toString());
        }
    }

    public List<User> readCSVDatabase() {
        String line = "";
        BufferedReader br = new BufferedReader(fr);
        List<User> users = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                users.add(new User(temp[0], temp[1], temp[2], temp[3]));
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
