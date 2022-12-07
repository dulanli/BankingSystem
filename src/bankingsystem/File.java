package bankingsystem;

import java.io.*;                     // importing in-built functions
import java.util.Scanner;

public class File {                   // File class

    public static void CreateFile(int id, String pw, String name, double bal, double sal) {         // CreateFile method
        try {
            java.io.File OpenFile = new java.io.File("files/accounts/" + id + ".txt");               // setting path

            if (OpenFile.exists()) {                            // check if it exists
                DialogBox.DialogBox("AccountNo [" + id + "] is taken");             // if file exists

            } else {            // if file does not exist

                try (FileWriter WriteFile = new FileWriter("files/accounts/" + id + ".txt")) {        // write into file 
                    WriteFile.write("" + name);       // Name
                    WriteFile.write("\n" + pw);       // Password

                    WriteFile.write("\n" + id);       // AccountNo
                    WriteFile.write("\n" + bal);      // Balance
                    WriteFile.write("\n" + sal);      // Salary

                    DialogBox.DialogBox("AccountNo [" + id + "] succesfully created");

                }

            }
        } catch (IOException e) {           // try-catch
            DialogBox.DialogBox("An error occurred");
        }

    }       // closing of CreateFile

    public static void CreateExpFile(int id) {              // CreateExpFile method

        try {
            java.io.File OpenFile = new java.io.File("files/expenses/" + id + "_Expenses.txt");       // setting path 

            if (OpenFile.exists()) {            // if file exists

            } else {

                Writer FileWriter;              
                FileWriter = new BufferedWriter(new FileWriter("files/expenses/" + id + "_Expenses.txt"));      // CreateFile if does not exist

                FileWriter.close();             // close the file

            }

        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");
        }

    }

    public static String checkFile(int id) {            // checkFile method

        String pw = null;
        String name = null;
        double bal = 0.0;
        double sal = 0.0;

        java.io.File checkFile = new java.io.File("files/accounts/" + id + ".txt");     // setting path

        try {
            if (checkFile.exists()) {

                try (Scanner Reader = new Scanner(checkFile)) {
                    name = Reader.nextLine();
                    pw = Reader.nextLine();
                    id = Reader.nextInt();
                    bal = Reader.nextDouble();
                    sal = Reader.nextDouble();
                }

            } else {
                DialogBox.DialogBox("Invalid AccountNo");

            }
        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");

        }

        return pw;

    }

    public static void modifyFile(int no, String pw, String name, double bal, double sal) {         // modifyFile method

        try {

            FileWriter WriteFile = new FileWriter("files/accounts/" + no + ".txt");         // rewrite the file

            WriteFile.write("" + name);     // Name
            WriteFile.write("\n" + pw);     // Password

            WriteFile.write("\n" + no);     // AccountNo
            WriteFile.write("\n" + bal);    // Balance
            WriteFile.write("\n" + sal);    // Salary
            WriteFile.close();

        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");
        }
    }

    public static String admin_checkFile(String username) {             // admin_checkFile method

        String pw = " ";            // initialising variables as null
        String name = " ";
        String email = " ";

        java.io.File checkFile = new java.io.File("files/admins/" + username + ".txt");     // setting path

        try {
            if (checkFile.exists()) {           // if file exists

                try (Scanner Reader = new Scanner(checkFile)) {         // read the file
                    name = Reader.nextLine();
                    pw = Reader.nextLine();
                    username = Reader.nextLine();
                    email = Reader.nextLine();
                }

            }
        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");

        }

        return pw;          // return value of pw         

    }
    
    
}           // closing of class File
