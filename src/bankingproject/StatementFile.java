package bankingproject;


import static bankingproject.Main.getIDuser;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StatementFile {
    int idd=getIDuser();
    public static String filename;
    String Content="TODAY'S TRANSACTION LIST";
    
    
    void SendToSuperFile(String content) throws IOException {
        String file = "statement_admin_";
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        String dater = formatter.format(date);
        String filepath = "C:\\Users\\Dell\\Desktop\\";
        filepath = filepath.concat(file);
        filepath = filepath.concat(dater);
        filepath = filepath.concat(".txt");
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            FileWriter writer = new FileWriter(filepath,true);
            String line = scanner.nextLine();
            writer.write(line);
            writer.write(System.getProperty("line.separator"));
            writer.close();
        }
        scanner.close();
    }

    void SendToUserFile(String content, String username) throws IOException {
        String file = "statement_";
        file = file.concat(username);
        file = file.concat("_");
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        String dater = formatter.format(date);
        String filepath = "C:\\Users\\Dell\\Desktop\\";
        filepath = filepath.concat(file);
        filepath = filepath.concat(dater);
        filepath = filepath.concat(".txt");
        Scanner scanner = new Scanner(content);
        while (scanner.hasNextLine()) {
            FileWriter writer = new FileWriter(filepath, true);
            String line = scanner.nextLine();
            writer.write(line+"\n");
            writer.write(System.getProperty("line.separator"));
            writer.close();
        }
        scanner.close();
    }
    
    String GetFileContent() {
        return Content;
    }

    
    void SetFileContent(String Transactions) {
        Content = Content.concat("\n"+ Transactions);
    }

    
    public StatementFile() {}

}
