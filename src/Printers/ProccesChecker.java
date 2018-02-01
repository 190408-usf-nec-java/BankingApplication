/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProccesChecker {


  public static boolean isRunning(String text) {
      
      
    boolean found = false;
    try {
        
  

        File file = File.createTempFile("realhowto",".vbs");
        file.deleteOnExit();
        FileWriter fw = new java.io.FileWriter(file);

        String vbs = "Set WshShell = WScript.CreateObject(\"WScript.Shell\")\n"
                   + "Set locator = CreateObject(\"WbemScripting.SWbemLocator\")\n"
                   + "Set service = locator.ConnectServer()\n"
                   + "Set processes = service.ExecQuery _\n"
                   + " (\"select * from Win32_Process where name='" + text +"'\")\n"
                   + "For Each process in processes\n"
                   + "wscript.echo process.Name \n"
                   + "Next\n"
                   + "Set WSHShell = Nothing\n";

        fw.write(vbs);
        fw.close();
        Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
        BufferedReader input =
            new BufferedReader
              (new InputStreamReader(p.getInputStream()));
        String line;
        line = input.readLine();
        if (line != null) {
            if (line.equals(text)) {
              found = true;
            }
        }
        input.close();

    }
    catch(Exception e){
        e.printStackTrace();
    }
      return found;
  }
  


}
