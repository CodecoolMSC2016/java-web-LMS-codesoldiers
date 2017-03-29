import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CSVRW {
    private String CSVFile = "userdatabase.csv";
    private File userDatabaseCsv = new File(CSVFile);
    private BufferedReader br = new BufferedReader(new FileReader(CSVFile));

    private ArrayList<Register> userDatabase = new ArrayList<>();

    public CSVRW() throws FileNotFoundException {
    }

    public ArrayList<Register> getUserDatabase() {
        return userDatabase;
    }

    public void addUser(Register register) {
        userDatabase.add(register);
    }

    public void saveToCSV(ArrayList userDatabase) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(userDatabaseCsv);
        String stringbuilder = null;
        pw.append("Username,Emailaddress,Role");
        for (Object register : userDatabase) {
            pw.append(register.toString());
        }

    }

    public ArrayList<Register> readCSVDatabase(){
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                userDatabase.add(new Register(line.split(";")[0],line.split(";")[1],line.split(";")[2]));

            }
        } catch (IOException e) {
            e.printStackTrace();
            }
        return userDatabase;
    }
}
