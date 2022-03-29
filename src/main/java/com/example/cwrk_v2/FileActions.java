package com.example.cwrk_v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileActions {
    public static void save() throws IOException {

        File f = new File("driverInfo.txt");
        PrintWriter out = new PrintWriter(f);

        for (int i = 0; i < 100; i++) {
            out.println(i);
        }

        out.close();
    }


    public static String load(String driverInfo) throws FileNotFoundException {
String result="";
        File f = new File(driverInfo+".txt");
        Scanner in = new Scanner(f);

        while (in.hasNext()) {
            result+=in.nextLine();
        }

        in.close();
        return result;
    }
}
