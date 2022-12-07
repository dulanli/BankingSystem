package bankingsystem;

import java.io.BufferedWriter;          // importing in built functions
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


public class depositMenu {              // depositMenu class

    static TextField msg_Text;          // use everywhere
    static double depoAmount;

    public static void depositMenu(int no, String name, String pw, double bal, double sal) {        // depositMenu method

        Stage window = new Stage();

        window.setTitle("Withdraw");

        GridPane grid = new GridPane();         // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Amount");      // text 
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));     // font for text

        Label msg_Lbl = new Label("Amount to deposit");     // Label
        msg_Text = new TextField("Rs");     // textfield
        msg_Lbl.setMinWidth(100);           // dimension for Label

        grid.add(welcome, 0, 0);            // add position 

        grid.add(msg_Text, 1, 1);
        grid.add(msg_Lbl, 0, 1);

        Button OK = new Button("OK");       // OK button
        grid.add(OK, 1, 4);
        OK.setMinWidth(100);

        OK.setOnAction(e -> {               // if OK is pressed

            int return_value = eventHandler();

            if (return_value == 1) {

                depoAmount = Double.parseDouble(msg_Text.getText());    // initialising depoAmount as value type in textfield

                double newbal = deposit(bal, depoAmount, no);           // calling function withdraw and storing a new value in bal
                File.modifyFile(no, pw, name, newbal, sal);             // updating the file
                
                refresh.refresh();          // refresh the menu
                window.close();
                
            }

        });

        Scene scene = new Scene(grid, 500, 500);        // adding the grid and dimension to the scene
        window.setScene(scene);
        window.show();

    }

    public static int eventHandler() {          // validation of input
        
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

    public static double deposit(double currentBal, double depo, int id) {          // deposit method
        double newBal = currentBal + depo;          // calculating an updated balance

        try {

            Writer FileWriter;

            FileWriter = new BufferedWriter(new FileWriter("files/expenses/" + id + "_Expenses.txt", true));       // add line in Expenses File to keep record 
            FileWriter.append(menuCustomer.date());                     // add today date line into file
            FileWriter.append(" - Deposit" + " - " + depo + "\n");      // add line into file
            FileWriter.close();

        } catch (IOException e) {
            DialogBox.DialogBox("An error occurred");
        }

        return newBal;              // return value of newBal
    }

}           // closing of class depositMenu
