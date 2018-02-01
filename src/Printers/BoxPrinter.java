package Printers;

public class BoxPrinter {
    
     public static void BoxPrinter(String Text)
    {
        
        int length = 10;
        int spacesNeeded = 0;
        int size = 1;
        String inputs = Text;
        System.out.print("+");
        int maxsize = 0;
        if (inputs.length() > maxsize) {
            maxsize = inputs.length();
        }
        for (int i = 0; i <= maxsize + 1; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
        System.out.print("| " + inputs);
        for (int i = inputs.length(); i <= maxsize - 1; i++) {
            System.out.print(" ");
        }
        System.out.print(" |");
        System.out.println();
        System.out.print("+");
        for (int i = 0; i <= maxsize + 1; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
    
    }
    
    
}
