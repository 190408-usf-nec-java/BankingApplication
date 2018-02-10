package bankingproject;

import static Printers.BoxPrinter.BoxPrinter;
import static Printers.LoadingScreens.LoadingBar;
import static Printers.ScreenCleaner.CLS;
import static Printers.LoadingScreens.LogOut;
import static Printers.LoadingScreens.PleaseWait;
import static bankingproject.DatabaseAccess.DataBaser;
import static bankingproject.DatabaseAccess.IdToUsername;
import static bankingproject.Main.UserID;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AppMenus {

    public static StatementFile FILE = new StatementFile();

    public static void SuperAcc() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException, IOException {
        BankAccount User = new BankAccount(UserID);
        java.util.Date date = new java.util.Date();
        System.out.println("\nWELCOME , " + IdToUsername(UserID) + "\n" + date + "\n" + "BANK ACCOUNT - PERMISSIONS: ALL/SUPERUSER\n\n");
        System.out.println("               +----------------------------------------+\n"
                + "               |    BANKING SYSTEM CONTROL PANEL        | \n"
                + "+--------------------------------------------------------------------------+\n"
                + "| (A)View Cooperative's internal bank account                              |\n"
                + "| (B)View Members' bank accounts                                           |\n"
                + "| (C)Deposit to Member's bank account                                      |\n"
                + "| (D)Withdraw from Member's bank account                                   |\n"
                + "| (E)Send to the statement_user_x_dd_mm_yyyy.txt file today's transactions |\n"
                + "|                                                                          |\n"
                + "|                                                         (EXIT) - LOGOUT  |\n"
                + "+--------------------------------------------------------------------------+");

        System.out.println("\nSELECT YOUR OPTION:   (BY TYPING:A,B,C,D,E) -- TYPE \"EXIT\" TO LOGOUT\n");
        Scanner rec = new Scanner(System.in);
        String selection = rec.nextLine();

        while (!"A".equalsIgnoreCase(selection) && !"B".equalsIgnoreCase(selection) && !"C".equalsIgnoreCase(selection) && !"D".equalsIgnoreCase(selection) && !"E".equalsIgnoreCase(selection) && !"EXIT".equalsIgnoreCase(selection)) {
            BoxPrinter("INVALID CHOICE");
            System.out.println("PLEASE SELECT ONE OF THE ABOVE OPTIONS");
            selection = rec.nextLine();

        }
        if ("EXIT".equalsIgnoreCase(selection)) {
            LogOut();
            System.exit(0);
        }
        if ("A".equalsIgnoreCase(selection)) {
            CLS();
            
            BoxPrinter("INTERNAL BANK ACCOUNT INFO");
            System.out.println("\n\nADMINISTRATOR BANK ACCOUNT\n-----------------------------\n");
            System.out.println("CURRENT ACCOUNT BALANCE: " + User.ShowBalance() + "\n-----------------------------\n");
            System.out.println("WOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();

            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SuperAcc();
                
            }
        }
        if ("B".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("MEMBERS' ACCOUNT VIEWER PORTAL");
            System.out.println();
            ResultSet UsersDB = DataBaser("SELECT A.username ,B.amount FROM users AS A   INNER\n" + "JOIN accounts as B   ON A.id=B.id ;");
            Printers.LoadingScreens.LoadingDatabase();
            Printers.DBTablePrinter.printResultSet(UsersDB);
            System.out.println("WOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SuperAcc();
            }

        }
        if ("C".equalsIgnoreCase(selection)) {
            CLS();
            System.out.println("LOADING PLEASE WAIT");
            System.out.println("");
            BoxPrinter("DEPOSIT TO MEMBER'S ACCOUNT");
            System.out.println("-----------------------------");
            User.Deposit();
            System.out.println("WOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SuperAcc();
            }

        }
        if ("D".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("WITHDRAW DROM MEMBER'S ACCOUNT");
            System.out.println("-----------------------------\n");
            User.Withdraw();
            System.out.println("WOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SuperAcc();
            }
        }

        if ("E".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("WRITE TODAY'S TRANSACTIONS TO STATEMENT FILE");
            System.out.println("\n-----------------------------\n");
            System.out.println("TRANSACTIONS COMPLETED TODAY :");
            String content = FILE.GetFileContent();
            System.out.println(content);
            System.out.println("WOULD YOU LIKE TO SAVE THEM?        (TYPE: Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                FILE.SendToSuperFile(content);
                System.out.println("WRITTING TRANSACTIONS TO FILE");
                PleaseWait();
                BoxPrinter("SUCCESS");
                System.out.println("RETURNING TO MAIN MENU");
                PleaseWait();
                SuperAcc();
            } else {
                CLS();
                System.out.println("RETURNING TO MAIN MENU");
                PleaseWait();
                SuperAcc();
            }
        }
    }

    public static void SimpleAcc() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException, InterruptedException {
        BankAccount User = new BankAccount(UserID);
        System.out.println();
        java.util.Date date = new java.util.Date();
        BoxPrinter("WELCOME , " + IdToUsername(UserID));
        System.out.println("\n" + date);
        System.out.println("BANK ACCOUNT - PERMISSIONS: STANDARD/MEMBER\n");
        System.out.println("               +----------------------------------------+\n"
                + "               |    BANKING SYSTEM CONTROL PANEL        | \n"
                + "+---------------------------------------------------------------------------+\n"
                + "| (A)View Bank account                                                      | \n"
                + "| (B)Deposit to Cooperative's internal bank account                         |\n"
                + "| (C)Deposit to another Members' bank account                               |\n"
                + "| (D)Send to the statement_user_x_dd_mm_yyyy.txt file today's Transactions  |\n"
                + "|                                                                           | \n"
                + "|                                                       (EXIT) - LOGOUT     |\n"
                + "+---------------------------------------------------------------------------+");
        System.out.println("SELECT YOUR OPTION:   (BY TYPING:A,B,C OR D)" + "\n");
        Scanner rec = new Scanner(System.in);
        String selection = rec.nextLine();
        while (!"A".equalsIgnoreCase(selection) && !"B".equalsIgnoreCase(selection) && !"C".equalsIgnoreCase(selection) && !"D".equalsIgnoreCase(selection) && !"EXIT".equalsIgnoreCase(selection)) {
            BoxPrinter("INVALID CHOICE");
            System.out.println("PLEASE SELECT ONE OF THE ABOVE OPTIONS");
            selection = rec.nextLine();
        }

        if ("exit".equalsIgnoreCase(selection)) {
            LogOut();
            System.exit(0);
        }

        if ("A".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("INTERNAL BANK ACCOUNT INFO");
            System.out.println("BANK ACCOUNT - PERMISSIONS: STANDARD/MEMBER");
            BoxPrinter("CURRENT ACCOUNT BALANCE: " + User.ShowBalance());
            System.out.println("\nWOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SimpleAcc();
            }

        }

        if ("B".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("DEPOSIT TO COOPERATIVE'S ACCOUNT");
            User.DepositToCo();
            System.out.println("WOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SimpleAcc();
            }

        }

        if ("C".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("DEPOSIT TO MEMBER'S ACCOUNT");
            User.Deposit();
            System.out.println("\nWOULD YOU LIKE TO RETURN TO THE MAIN MENU?(Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                CLS();
                SimpleAcc();
            }
        }

        if ("D".equalsIgnoreCase(selection)) {
            CLS();
            BoxPrinter("WRITE TODAY'S TRANSACTIONS TO STATEMENT FILE");
            System.out.println("\nTRANSACTIONS COMPLETED TODAY :");
            String content = FILE.GetFileContent();
            System.out.println(content);
            System.out.println("WOULD YOU LIKE TO SAVE THEM?        (TYPE: Y/N)");
            Scanner scanner = new Scanner(System.in);
            String CHOICE = scanner.nextLine();
            if ("Y".equalsIgnoreCase(CHOICE) || "YES".equalsIgnoreCase(CHOICE)) {
                FILE.SendToUserFile(content, IdToUsername(FILE.idd));
                System.out.println("WRITTING TRANSACTIONS TO FILE");
                PleaseWait();
                BoxPrinter("SUCCESS");
                PleaseWait();
                System.out.println("RETURNING TO MAIN MENU");
                PleaseWait();
                CLS();
                SimpleAcc();
            } else {

                System.out.println("RETURNING TO MAIN MENU");
                PleaseWait();
                CLS();
                SimpleAcc();
            }
        }
    }
}
