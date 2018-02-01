package bankingproject;

import static Printers.BoxPrinter.BoxPrinter;
import static Printers.ScreenCleaner.CLS;
import static bankingproject.DatabaseAccess.DataBaser;
import static bankingproject.DatabaseAccess.IdToUsername;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginScreen {

    public static int userid;
    public static int loginCheck() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, IOException {
        boolean login = false;
        int logcounter = 1;
        while (logcounter <= 3 && login == false) {
            
            System.out.println("Please Enter Username:");
            Scanner userscanner = new Scanner(System.in);
            String username = userscanner.nextLine();
            while ("".equals(username)) {
                CLS();
                BoxPrinter("USERNAME FIELD CANNOT BE EMPTY");
                System.out.println("Please Enter Username:");
                username = userscanner.nextLine();
            }
            System.out.println("-------------------");
            System.out.println("Password: ");
            Scanner passscanner = new Scanner(System.in);
            String password = passscanner.nextLine();
            while ("".equals(password)) {
                CLS();
                BoxPrinter("PASSWORD FIELD CANNOT BE EMPTY");
                System.out.println("Please Enter Password:");
                password = passscanner.nextLine();
            }
            ResultSet rs = DataBaser("SELECT username, password ,id FROM users;");
            while (rs.next() && login == false) {
               int temp_id = rs.getInt("id");
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password");

                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    BoxPrinter("LOGIN SUCCESSFUL");
                    Printers.LoadingScreens.PleaseWait();
                    login = true;
                    userid = temp_id; 
                }
            }
            if (login == true && !"admin".equals(username)) {
                CLS();
                BoxPrinter("USER ACCOUNT MANAGEMENT");
                System.out.println("LOGGED IN AS: " + IdToUsername(userid));
                
            }
            if (login == true && "admin".equals(username)) {
                BoxPrinter(" SYSTEM ADMINISTRATION ACCOUNT(*admin*)");
                CLS();
            }
            if (login == false) {
                userid = -1;
                if (logcounter == 3) {
                    BoxPrinter("MAXIMUM ATTEMPTS REACHED");
                    logcounter++;
                } else {
                    CLS();
                    BoxPrinter("LOGIN FAILED");
                    System.out.println("WRONG USERNAME OR PASS, TRY AGAIN\n");
                    BoxPrinter((3 - logcounter) + " LOGIN ATTEMPTS REMAINING");
                    logcounter++; 
                }
            }
        }
        return userid;
    }
}
