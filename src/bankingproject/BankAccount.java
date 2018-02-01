package bankingproject;

import static Printers.BoxPrinter.BoxPrinter;
import static Printers.LoadingScreens.LogOut;
import static Printers.LoadingScreens.TransactionInProgress;
import static bankingproject.AppMenus.FILE;
import static bankingproject.DatabaseAccess.DataBaser;
import static bankingproject.DatabaseAccess.UpdateDbAmount;
import static bankingproject.DatabaseAccess.UsernameToId;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class BankAccount {

    public static int id;
    public static double balance;

    double ShowBalance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        double balance = 0;
        String id1 = Integer.toString(id);
        String QUERY = "SELECT * FROM accounts WHERE id=";
        String QUERY1 = QUERY.concat(id1);
        ResultSet rs = DataBaser(QUERY1);
        while (rs.next()) {
            balance = rs.getInt("amount");
        }
        return balance;
    }

    public static double ShowBalanceBasedonID(int id5) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        double balance5 = 0;
        String idString = Integer.toString(id5);
        String QUERY4 = "SELECT * FROM accounts WHERE id=";
        String QUERY5 = QUERY4.concat(idString);
        ResultSet rs = DataBaser(QUERY5);
        while (rs.next()) {
            balance5 = rs.getInt("amount");
        }
        return balance5;
    }

    void Deposit() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException, IOException {
        Boolean Checked = false;
        System.out.println("AVAILABLE BALANCE: " + ShowBalance() + "\n");
        System.out.println("HOW MUCH WOULD YOU LIKE TO SEND?" + "\n");
        Scanner send = new Scanner(System.in);
        double sendthem = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.println("PLEASE ENTER AMOUNT: ");
            if (send.hasNextDouble()) {
                sendthem = send.nextDouble();
                isValid = true;
                if (sendthem <= 0) {
                    BoxPrinter("INVALID AMOUNT");
                    isValid = false;
                }
                if (sendthem > ShowBalance()) {
                    BoxPrinter("INSUFFIECIENT BALANCE");
                    isValid = false;
                }
            } else {
                BoxPrinter("INVALID AMOUNT");
            }
            send.nextLine();
        }
        System.out.println("TYPE THE RECIPIENT'S NAME:" + "\n");
        boolean exists = false;
        String recipient = null;
        boolean notfound = false;
        while (exists == false) {
            if (notfound == true) {
                BoxPrinter("USER DOES NOT EXIST");
            }
            System.out.println("Please Enter Username:");
            notfound = true;
            Scanner recscanner = new Scanner(System.in);
            String username = recscanner.nextLine();
            while ("".equals(username)) {
                BoxPrinter("RECIPIENT FIELD CANNOT BE EMPTY");
                System.out.println("Please Enter Username");
                username = recscanner.nextLine();
            }
            ResultSet rs = DataBaser("SELECT username FROM users;");
            while (rs.next() && exists == false) {
                String dbUsername = rs.getString("username");
                if (dbUsername.equals(username)) {
                    exists = true;
                    recipient = username;
                }
            }
        }
        int recip = UsernameToId(recipient);
        if (recip == id) {
            BoxPrinter("TRANSACTION FAILED");
            System.out.println("YOU CANNOT SEND/RECEIVE MONEY TO/FROM YOUR BANK ACCOUNT");
            BoxPrinter("TERMINATING SYSTEM DUE TO INVALID INPUT");
            LogOut();
        }
        double recipientsbalance = ShowBalanceBasedonID(recip);
        recipientsbalance = recipientsbalance + sendthem;
        UpdateDbAmount(recipientsbalance, recip);
        System.out.println("-----------------------");
        UpdateDbAmount((ShowBalance() - sendthem), id);
        TransactionInProgress();
        System.out.println(sendthem + "EUROS" + " SENT TO " + recipient);
        System.out.println("YOUR BALANCE NOW IS: " + ShowBalance());
        FILE.SetFileContent(sendthem + " EUROS -" + " DEPOSITED TO: " + recipient);
    }

    void DepositToCo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException, IOException {

        System.out.println("AVAILABLE BALANCE: " + ShowBalance() + "\n");
        System.out.println("HOW MUCH WOULD YOU LIKE TO SEND?" + "\n");
        Scanner send = new Scanner(System.in);
        double sendthem = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.println("PLEASE ENTER AMOUNT: ");
            if (send.hasNextDouble()) {
                sendthem = send.nextDouble();
                isValid = true;
                if (sendthem <= 0) {
                    BoxPrinter("INVALID AMOUNT");
                    isValid = false;
                }
                if (sendthem > ShowBalance()) {
                    BoxPrinter("INSUFFIECIENT BALANCE");
                    isValid = false;
                }
            } else {
                BoxPrinter("INVALID AMOUNT");
            }
            send.nextLine();
        }
        String recipient = "admin";
        int recip = UsernameToId(recipient);
        double recipientsbalance = ShowBalanceBasedonID(recip);
        recipientsbalance = recipientsbalance + sendthem;
        UpdateDbAmount(recipientsbalance, recip);
        System.out.println("-----------------------");
        UpdateDbAmount((ShowBalance() - sendthem), id);
        TransactionInProgress();
        System.out.println(sendthem + "EUROS" + " SENT TO " + recipient);
        System.out.println("YOUR BALANCE NOW IS: " + ShowBalance());
        FILE.SetFileContent(sendthem + " EUROS -" + " DEPOSITED TO: " + recipient);
    }

    void Withdraw() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException, IOException {

        System.out.println("AVAILABLE BALANCE: " + ShowBalance() + "\n");
        System.out.println("SELECT AN ACCOUNT TO WITHDRAW FROM:" + "\n");
        boolean exists = false;
        String recipient = null;
        boolean notfound = false;
        while (exists == false) {
            if (notfound == true) {
                BoxPrinter("USER DOES NOT EXIST");
            }
            System.out.println("Please Enter Username:");
            notfound = true;
            Scanner recscanner = new Scanner(System.in);
            String username = recscanner.nextLine();
            while ("".equals(username)) {
                BoxPrinter("RECIPIENT FIELD CANNOT BE EMPTY");
                System.out.println("Please Enter Username");
                username = recscanner.nextLine();
            }
            ResultSet rs = DataBaser("SELECT username FROM users;");
            while (rs.next() && exists == false) {
                String dbUsername = rs.getString("username");
                if (dbUsername.equals(username)) {
                    exists = true;
                    recipient = username;
                }
            }
        }
        int recip = UsernameToId(recipient);
        if (recip == id) {
            BoxPrinter("TRANSACTION FAILED");
            System.out.println("YOU CANNOT SEND/RECEIVE MONEY TO/FROM YOUR BANK ACCOUNT");
            BoxPrinter("TERMINATING SYSTEM");
            LogOut();
        }
        double recipientsbalance = ShowBalanceBasedonID(recip);
        System.out.println("AVAILABLE BALANCE ON USER: " + recipient + " " + recipientsbalance);
        System.out.println("HOW MUCH WOULD YOU LIKE TO WITHDRAW?" + "\n");
        Scanner send = new Scanner(System.in);
        double sendthem = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.println("PLEASE ENTER AMOUNT: ");
            if (send.hasNextDouble()) {
                sendthem = send.nextDouble();
                isValid = true;
                if (sendthem <= 0) {
                    BoxPrinter("INVALID AMOUNT");
                    isValid = false;
                }
                if (sendthem > recipientsbalance) {
                    BoxPrinter("INSUFFIECIENT BALANCE ON SELCTED USER");
                    isValid = false;
                }
            } else {
                BoxPrinter("INVALID AMOUNT");
            }
            send.nextLine();
        }
        recipientsbalance = recipientsbalance - sendthem;
        UpdateDbAmount(recipientsbalance, recip);
        System.out.println("--------------------");
        UpdateDbAmount((ShowBalance() + sendthem), id);
        TransactionInProgress();
        System.out.println(sendthem + "EUROS" + "WITHDRAWED FROM: " + recipient);
        System.out.println("YOUR BALANCE NOW IS: " + ShowBalance());
        FILE.SetFileContent(sendthem + " EUROS -" + " TAKEN FROM: " + recipient);
    }

    BankAccount(int i) {
        id = i;

    }

}
