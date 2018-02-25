package bankingproject;

import static Printers.BoxPrinter.BoxPrinter;
import static Printers.ScreenCleaner.CLS;
import static bankingproject.DatabaseAccess.DataBaser;
import static bankingproject.DatabaseAccess.UsernameToId;
import static bankingproject.LoginScreen.userid;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginScreen {

    public static int userid;

    public static int loginCheck() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException, InterruptedException {
        boolean login = false;
        int logcounter = 0;
        String dbPassword = null;
        String username = null;
        while (logcounter < 3 && login == false) {

            System.out.println("Please Enter Username:");
            Scanner userscanner = new Scanner(System.in);
            username = userscanner.nextLine();
            String LoginQuery = "SELECT * FROM users WHERE username='" + username + "';";
            ResultSet rs = DataBaser(LoginQuery);
            
            
            if ("".equals(username)) {
                CLS();
                BoxPrinter("USERNAME FIELD CANNOT BE EMPTY");
            } else if (rs.next() == false) {
                BoxPrinter("USER NOT FOUND");
                logcounter++;
                BoxPrinter((3 - logcounter) + " LOGIN ATTEMPTS REMAINING");
            } else {

                System.out.println("-------------------");
                while (logcounter < 3 && login == false) {
                    System.out.println("Please Enter Password:");
                    Scanner passscanner = new Scanner(System.in);
                    String password = passscanner.nextLine();
                    String PassQuery = "select password from users where username=\"" + username + "\"";
                    ResultSet rsPass = DataBaser(PassQuery);
                    if (rsPass.next()) {

                        dbPassword = rsPass.getString("password");
                    }

                    if ("".equals(password)) {
                        CLS();
                        BoxPrinter("USERNAME FIELD CANNOT BE EMPTY");

                    } else if (!password.equals(dbPassword)) {
                        System.out.println("WRONG PASSWORD PLEASE TRY AGAIN");
                        logcounter++;
                BoxPrinter((3 - logcounter) + " LOGIN ATTEMPTS REMAINING");
                    } else if (password.equals(dbPassword)) {
                        BoxPrinter("LOGIN SUCCESSFUL");
                        Printers.LoadingScreens.PleaseWait();
                        login = true;
                        break;

                    }

                }
            }

        }
        if (login == true && !"admin".equals(username)) {
            CLS();
      

        }
        if (login == true && "admin".equals(username)) {
            BoxPrinter(" SYSTEM ADMINISTRATION ACCOUNT(*admin*)");
            CLS();
        }
        return userid = UsernameToId(username);
    }

}
