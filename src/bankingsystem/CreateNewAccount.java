package bankingsystem;

import javafx.geometry.Pos;             //  importing in-built functions
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateNewAccount {                       // CreateNewAccount method
    
    static TextField accountno_Text, name_Text, pw_Text, bal_Text, sal_Text;            // able to use these variables within the class
    
    public static void CreateNewAccount() {          // createNewAccount method
        
        Stage window = new Stage();
        
        window.setTitle("City Bank - Create new account");
        
        GridPane grid = new GridPane();             // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        
        Text welcome = new Text("Create an account");       // text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));     // font for the text
        
        accountno_Text = new TextField();               // rectangular box to input data
        Label accountno_Lbl = new Label("AccountNo");   // Label for the TextField
        
        name_Text = new TextField();
        Label name_Lbl = new Label("Full Name");
        
        pw_Text = new TextField();
        Label pw_Lbl = new Label("Password");
        
        bal_Text = new TextField();
        Label bal_Lbl = new Label("Bank Balance");
        
        sal_Text = new TextField();
        Label sal_Lbl = new Label("Current Salary");
        
        grid.add(welcome, 0, 0);                    // add the Text, TextField, Label
                                                    // set their position
        grid.add(accountno_Text, 1, 1);
        grid.add(accountno_Lbl, 0, 1);
        
        grid.add(name_Text, 1, 2);
        grid.add(name_Lbl, 0, 2);
        
        grid.add(pw_Text, 1, 3);
        grid.add(pw_Lbl, 0, 3);
        
        grid.add(bal_Text, 1, 4);
        grid.add(bal_Lbl, 0, 4);
        
        grid.add(sal_Text, 1, 5);
        grid.add(sal_Lbl, 0, 5);
        
        Button apply = new Button("Apply");           // creating buttons
        apply.setMinWidth(225);                       // setting the dimension
        grid.add(apply, 1, 8);
        
        Button done = new Button("Done");              
        done.setMinWidth(225);
        grid.add(done, 1, 9);
        
        apply.setOnAction(e -> {
            
            int return_value = eventHandler();
            
            if (return_value == 1) {
               
             // calling File class and CreateFile method
                File.CreateFile(Integer.parseInt(accountno_Text.getText()), pw_Text.getText(), name_Text.getText(), Double.parseDouble(bal_Text.getText()), Double.parseDouble(sal_Text.getText()));
             // calling File class and CreateExpFile method
                File.CreateExpFile(Integer.parseInt(accountno_Text.getText()));
            }
        });
        
        done.setOnAction(e -> {
            
            window.close();         // close the stage
        });
        
        Scene scene = new Scene(grid);      // adding the grid to the scene
        window.setScene(scene);
        window.setMaximized(true);          // maxmimize the screen
        window.show();
        
    }
    
    public static int eventHandler() {          // validation of input
        
        // if statements
        if (accountno_Text.getText().matches("") && name_Text.getText().matches("") && pw_Text.getText().contains("") && bal_Text.getText().matches("") && sal_Text.getText().matches("")) {
            
            DialogBox.DialogBox("Fields are empty");
        } else if (accountno_Text.getText().matches("") || (!accountno_Text.getText().matches("^[0-9]+$"))) {
            
            DialogBox.DialogBox("AccountNo should consist of numbers only");
        } else if (Double.parseDouble(accountno_Text.getText()) < 1) {
            
            DialogBox.DialogBox("Invalid AccountNo");
        } else if (name_Text.getText().matches("") || (!name_Text.getText().matches("^[a-zA-Z]+$"))) {
            
            DialogBox.DialogBox("Name should consist of letters only");
        } else if (pw_Text.getText().length() < 6 || pw_Text.getText().contains(" ")) {
            
            DialogBox.DialogBox("Password should be 6 letters/numbers long");
        } else if (bal_Text.getText().matches("") || (!bal_Text.getText().matches("^[0-9]+$"))) {
            
            DialogBox.DialogBox("Balance should consist of numbers only");
        } else if (sal_Text.getText().matches("") || (!sal_Text.getText().matches("^[0-9]+$"))) {
            
            DialogBox.DialogBox("Salary should consist of numbers only");
        } else {
            return 1;           // return value of 1
        }   
        
        return 0;               // return value of 0
            
    }       // closing of eventHandler()
    
}           // closing of class CreateNewAccount()
