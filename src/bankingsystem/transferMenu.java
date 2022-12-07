package bankingsystem;

import javafx.geometry.Pos;         // importing all necessary in-built functions
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Scanner;
import java.io.*;                     // Import all file classes

public class transferMenu {         // transferMenu class

    static TextField accNo_Text, amt_Text;
    static int transferNo;
    static double transferAmt;

    public static void transferMenu(int no, String name, String pw, double bal, double sal) {   // transferMenu method

        Stage window = new Stage();

        window.setTitle("Transfer Money");

        GridPane grid = new GridPane();         // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Transfer Money to an AccountNo");      // text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 18));     // font for text
        grid.add(welcome, 0, 0);

        Label accNo_Lbl = new Label("AccountNo to transfer");           // setting attributes to label
        accNo_Text = new TextField();               
        accNo_Lbl.setMinWidth(100);

        grid.add(accNo_Text, 1, 1);             // grid adding label and textfield
        grid.add(accNo_Lbl, 0, 1);

        Label amt_Lbl = new Label("Amount");
        amt_Text = new TextField("Rs");
        amt_Lbl.setMinWidth(100);

        grid.add(amt_Text, 1, 2);
        grid.add(amt_Lbl, 0, 2);

        Button apply = new Button("Apply");         // apply button
        grid.add(apply, 1, 3);                      // setting position for apply button
        apply.setMinWidth(100);                     // setting dimension

        Button OK = new Button("Done");             // done button
        grid.add(OK, 1, 4);                         // setting position for done button
        OK.setMinWidth(100);

        apply.setOnAction(e -> {                    // if apply button is pressed

            int return_value = eventHandler();      // store value(0/1) into return_value

            if (return_value == 1) {

                try {

                    transferNo = Integer.parseInt(accNo_Text.getText());        // initialising transferNo as value type in accNo textfield

                    java.io.File transferFile = new java.io.File("files/accounts/" + transferNo + ".txt");     // setting path

                    if (transferFile.exists()) {            // if file exists

                        Scanner Reader = new Scanner(transferFile);     // read file

                        String transferName = Reader.nextLine();        // assigning values from files
                        String transferPassword = Reader.nextLine();
                        int accountNo = Reader.nextInt();
                        double currentBalance = Reader.nextDouble();
                        double transferSalary = Reader.nextDouble();

                        transferAmt = Double.parseDouble(amt_Text.getText());       // initialising transferAmt as value type in amt textfield

                        if (transferAmt > bal) {        // if transferamt is less than balance from file
                            DialogBox.DialogBox("Transaction failed due to insufficient fund");
                            
                        } else if (transferNo == no) {
                            DialogBox.DialogBox("Invalid Entry");

                        } else {
                            double newbal = transfer(bal, transferAmt, transferNo, no);     // calling transfer method

                            File.modifyFile(no, pw, name, newbal, sal);         // update the file

                            double transferBalance = currentBalance + transferAmt;      // new bal for receiver

                            File.modifyFile(transferNo, transferPassword, transferName, transferBalance, transferSalary);   // update file of receiver
                        }
                    } else {
                        DialogBox.DialogBox("Account does not exist");          // dialog message
                    }

                } catch (IOException e2) {
                    DialogBox.DialogBox("An error occurred");                   // dialog message
                }

            }
        });

        OK.setOnAction(e -> {           // if OK button is pressed
            
            refresh.refresh();          // refresh the stage
            window.close();

        });

        Scene scene = new Scene(grid, 500, 500);        // adding the grid and dimension to the scene
        window.setScene(scene);
        window.show();

    }

    public static int eventHandler() {      // validation
        
        // if statements
        if (accNo_Text.getText().matches("")) {
            DialogBox.DialogBox("Field empty");

        } else if (amt_Text.getText().matches("")) {
            DialogBox.DialogBox("Field empty");

        } else if (!accNo_Text.getText().matches("^[0-9]+$")) {
            DialogBox.DialogBox("AccountNo should consist of numbers only");

        } else if (!amt_Text.getText().matches("^[0-9]+$")) {
            DialogBox.DialogBox("Amount should consist of numbers only");

        } else {
            return 1;       // return value of 1
        }

        return 0;           // return value of 0 

    }

    public static double transfer(double currentBal, double Amt, int transferNo, int id) {      // transfer method

        double newBal = currentBal - Amt;           // calculating updated bal

        DialogBox.DialogBox("You transfered Rs" + Amt + " to AccountNo [" + transferNo + "]");      // dialog message

        try {           // try - catch

            Writer FileWriter;      // adding line to keep record of transfer expenses for the receiver
            FileWriter = new BufferedWriter(new FileWriter("files/expenses/" + transferNo + "_Expenses.txt", true));    
            FileWriter.append(menuCustomer.date());         // add line of today date into file
            FileWriter.append(" - Money received from AccountNo [" + id + "] - " + Amt + "\n");     // add line into file
            FileWriter.close();         // close file

        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");       // dialog message
        }

        try {

            Writer FileWriter;  // adding line to keep record of transfer expenses 
            FileWriter = new BufferedWriter(new FileWriter("files/expenses/" + id + "_Expenses.txt", true));
            FileWriter.append(menuCustomer.date());         // add line into file
            FileWriter.append(" - Money transfered to AccountNo [" + transferNo + "] - " + Amt + "\n");         // add line into file
            FileWriter.close();

        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");           // dialog message
        }

        return newBal;              // return value of newBal

    }
    
}                       // closing of class transferMenu
