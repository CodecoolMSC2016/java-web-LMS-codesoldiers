package app;

import app.pages.AssignmentPage;
import app.pages.Page;
import app.pages.TextPage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVRW {
    private File userDatabaseCsv;
    private FileReader fr;

    public CSVRW(String filename) {
        userDatabaseCsv = new File(filename);
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

    public void saveToCSV(List db) {
        PrintWriter pw;
        try {
            // Username,EmailAddress,Role,Password
            pw = new PrintWriter(userDatabaseCsv);
            for (Object register : db) {
                pw.append(register.toString() + "\n");
            }
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> readUserDatabase() {
        String line = "";
        BufferedReader br = new BufferedReader(fr);
        List<User> tempList = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                tempList.add(new User(temp[0], temp[1], temp[2], temp[3]));
            }
            return tempList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Page> readPageDatabase() {
        String line = "";
        BufferedReader br = new BufferedReader(fr);
        List<Page> tempList = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(";");
                if (temp.length == 2) {
                    tempList.add(new TextPage(temp[0], temp[1]));
                } else if (temp.length == 3) {
                    tempList.add(new AssignmentPage(temp[0], temp[1], Integer.valueOf(temp[2])));
                }
                return tempList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
