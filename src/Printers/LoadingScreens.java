package Printers;

import static Printers.BoxPrinter.BoxPrinter;
import static Printers.ScreenCleaner.CLS;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoadingScreens {

    public static void LoadingDatabase() throws InterruptedException {

        System.out.print("\n GATHERING DATA FROM DATABASE.");
        for (int i = 0; i < 4; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println("\n");

    }

    public static void PleaseWait() throws InterruptedException {

        System.out.print("\n PLEASE WAIT.");
        for (int i = 0; i < 2; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println("\n");

    }

    public static void TransactionInProgress() throws InterruptedException {

        System.out.print("\n TRANSACTION IN PROGRESS.");
        for (int i = 0; i < 2; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println();
        BoxPrinter("TRANSACTION COMPLETE");
        System.out.println("\n");

    }

    public static void LogOut() throws InterruptedException, IOException {
        CLS();
        System.out.print("\n LOGGING OUT.");
        for (int i = 0; i < 2; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print(".");
        }
        System.out.println();
        BoxPrinter("BANKING SYSTEM TERMINATED");
        System.out.println("\n");

    }

    public static void LoadingBar() throws InterruptedException, IOException {

      System.out.println("LOADING PLEASE WAIT");
           
        for (int i = 0; i < 9; i++) {
            
           
        Thread.sleep(400);
        System.out.print("█");
        }
         System.out.print(" 100%");
          Thread.sleep(800);
          CLS();
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        LoadingBar();
    }
}
