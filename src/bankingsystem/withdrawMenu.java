package bankingsystem;

import java.io.BufferedWriter;          // importing necessary in built functions
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class withdrawMenu {

    static TextField msg_Text;
    static double withAmount;

    public static void withdrawMenu(int no, String name, String pw, double bal, double sal) {       // withdrawMenu method

        Stage window = new Stage();

        window.setTitle("Withdraw");

        GridPane grid = new GridPane();         // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Withdraw");     // withdraw text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));         // font for withdraw text

        Label msg_Lbl = new Label("Amount to withdraw");        // message label
        msg_Text = new TextField("Rs");         // message textfield
        msg_Lbl.setMinWidth(100);               // message dimension

        grid.add(welcome, 0, 0);                // grid adding text, label, textfield

        grid.add(msg_Text, 1, 1);
        grid.add(msg_Lbl, 0, 1);

        Button OK = new Button("OK");           // ok button
        grid.add(OK, 1, 4);
        OK.setMinWidth(100);

        OK.setOnAction(e -> {                   // if ok is pressed

            int return_value = eventHandler();  // store value(0/1) of eventHandler 

            if (return_value == 1) {

                withAmount = Double.parseDouble(msg_Text.getText());        // initialising withAmount as value type 

                if (withAmount > bal) {         // if amount greater than bal
                    DialogBox.DialogBox("Transaction failed due to insufficient fund");     // message box

                } else {
                    double newbal = withdraw(bal, withAmount, no);      // calling function withdraw and storing a new value in bal
                    File.modifyFile(no, pw, name, newbal, sal);         // update the file

                    refresh.refresh();                  // refresh the menu
                    window.close();                     // close the stage

                }

            }

        });         // end of action if ok is pressed

        Scene scene = new Scene(grid, 500, 500);        // adding the grid and dimension to the scene
        window.setScene(scene);
        window.show();

    }

    public static int eventHandler() {          // validation of user input
        
        // if statements
        if (msg_Text.getText().matches("")) {

            DialogBox.DialogBox("Fields is empty");
        } else if (!msg_Text.getText().matches("^[0-9]+$")) {

            DialogBox.DialogBox("Insert numbers only");
        } else {

            return 1;           // return value of 1
        }

        return 0;               // return value of 0 

    }

    public static double withdraw(double currentBal, double with, int id) {         // withdraw method
        double newBal = currentBal - with;          // calculating newbal for withdraw

        try {

            Writer FileWriter;          // adding line to keep withdraw expenses
            FileWriter = new BufferedWriter(new FileWriter("files/expenses/" + id + "_Expenses.txt", true));
            FileWriter.append(menuCustomer.date());                     // add today date into file
            FileWriter.append(" - Withdraw" + " - " + with + "\n");     // add line into file
            FileWriter.close();

        } catch (IOException e) {       // try-catch
            DialogBox.DialogBox("An error occurred");
        }

        return newBal;          // return newBal
    }

}               // closing of class withdrawMenu
