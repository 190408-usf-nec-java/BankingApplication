/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printers;

import static Printers.ProccesChecker.isRunning;
import java.io.IOException;

/**
 *
 * @author Dell
 */
public class ScreenCleaner {
    public static void CLS() throws IOException, InterruptedException{
        
        if (isRunning("cmd.exe")==true || isRunning("powershell.exe")==true){
            
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
      
    
    }
}
