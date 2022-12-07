package bankingsystem;

import javafx.geometry.Pos;                     // importing in-built functions
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;                     // Import all file classes


public class loanMenu {         // loanMenu class

    static TextField accNo_Text, amt_Text;
    static int loanNo;
    static double loanAmt;

    public static void loanMenu() {         // loanMenu method

        Stage window = new Stage();

        window.setTitle("Loan");

        GridPane grid = new GridPane();      // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Grant Loan to an Account");            // text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 18));     // font to the text
        grid.add(welcome, 0, 0);

        Label accNo_Lbl = new Label("AccountNo to give loan");          // row to insert accountno        
        accNo_Text = new TextField();
        accNo_Lbl.setMinWidth(100);

        grid.add(accNo_Text, 1, 1);
        grid.add(accNo_Lbl, 0, 1);

        Label amt_Lbl = new Label("Amount");           // Row to insert amount
        amt_Text = new TextField("Rs");
        amt_Lbl.setMinWidth(100);

        grid.add(amt_Text, 1, 2);
        grid.add(amt_Lbl, 0, 2);

        Button apply = new Button("Apply");             // apply button
        grid.add(apply, 1, 3);                          // grid adding apply button and position
        apply.setMinWidth(100);

        Button OK = new Button("Done");
        grid.add(OK, 1, 4);
        OK.setMinWidth(100);

        apply.setOnAction(e -> {                        // if apply is pressed

            int return_value = eventHandler();          // store value(0/1) into return_value

            if (return_value == 1) {

                try {
                    loanNo = Integer.parseInt(accNo_Text.getText());            // initialising loanNo as value typed in accNo_text
                    loanAmt = Double.parseDouble(amt_Text.getText());           // initialising loanAmt as value typed in amt_text
                    
                    java.io.File LoanFile = new java.io.File("files/accounts/" + loanNo + ".txt");     // setting the path

                    if (LoanFile.exists()) {        // if file exists

                        Scanner Reader = new Scanner(LoanFile);     // read the file

                        String name = Reader.nextLine();
                        String pw = Reader.nextLine();
                        int no = Reader.nextInt();
                        double bal = Reader.nextDouble();
                        double sal = Reader.nextDouble();

                        if (sal < 20000) {          // check if salary is less than 20000
                            DialogBox.DialogBox("User is not eligible for loan");
                        } else {
                            
                            double newbal = loan(bal, loanAmt, loanNo);     // calling method newbal

                            File.modifyFile(loanNo, pw, name, newbal, sal); // updating the values
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Account does not exist");
                    }

                } catch (IOException e2) {
                    DialogBox.DialogBox("An error occurred");
                }

            }
        });

        OK.setOnAction(e -> {           // if OK is pressed
            
            window.close();
        });

        Scene scene = new Scene(grid, 500, 500);        // adding the grid to the scene and dimension
        window.setScene(scene);
        window.show();

    }

    public static int eventHandler() {                  // validation of user inputs
        
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
            return 1;           // return value of 1
        }

        return 0;               // return value of 0

    }

    public static double loan(double currentBal, double loanamt, int id) {      // loan method
        double newBal = currentBal + loanamt;           // calculating updated balance
        
        DialogBox.DialogBox("Loan Amount of Rs" + loanamt + " approved");           // calling DialogBox.DialogBox to display message

        try {               // try-catch

            Writer FileWriter;
            FileWriter = new BufferedWriter(new FileWriter("files/expenses/" + id + "_Expenses.txt", true));        // adding new line to Expenses File to keep record
            FileWriter.append(menuCustomer.date());         // add today day line into file
            FileWriter.append(" - Loan From Bank - " + loanamt + "\n");     // add line into file
            FileWriter.close();         // close file

        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");           // dialog box
        }

        return newBal;          // returning value of newBal
    }

}                   // closing of class loanMenu()
