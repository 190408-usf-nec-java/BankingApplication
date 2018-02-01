package bankingproject;

import static Printers.BoxPrinter.BoxPrinter;
import static Printers.ScreenCleaner.CLS;
import static bankingproject.AppMenus.SimpleAcc;
import static bankingproject.AppMenus.SuperAcc;
import static bankingproject.DatabaseAccess.IdToUsername;
import static bankingproject.LoginScreen.loginCheck;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static int UserID;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, IOException {
        CLS();
        System.out.println("  ____              _    _               ____            _                   _ _ __________ \n"
                + " | __ )  __ _ _ __ | | _(_)_ __   __ _  / ___| _   _ ___| |_ ___ _ __ ___   / / |___ /___  |\n"
                + " |  _ \\ / _` | '_ \\| |/ / | '_ \\ / _` | \\___ \\| | | / __| __/ _ \\ '_ ` _ \\  | | | |_ \\  / / \n"
                + " | |_) | (_| | | | |   <| | | | | (_| |  ___) | |_| \\__ \\ ||  __/ | | | | | | | |___) |/ /  \n"
                + " |____/ \\__,_|_| |_|_|\\_\\_|_| |_|\\__, | |____/ \\__, |___/\\__\\___|_| |_| |_| |_|_|____//_/   \n"
                + "                                 |___/         |___/                                        "+"\n");
        BoxPrinter("WELCOME TO BANKING SYSTEM 1137");
        System.out.println("\nPRESS ENTER TO PROCEED...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        BoxPrinter("SYSTEM LOGIN");

        UserID = loginCheck();
        if (UserID != -1) {

            if ("admin".equals(IdToUsername(UserID))) {
                SuperAcc();
            }
            {
                SimpleAcc();
            }
        }
    }

    public static int getIDuser() {
        return UserID;
    }
}
