package bankingsystem;

import java.io.IOException;            // importing in-built functions
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExistingAccount {          // ExistingAccount class

    static TextField accountno_Text;
    static PasswordField pw_Text;

    public static void ExistingAccount() {      // ExistingAccount method

        Stage window = new Stage();

        window.setTitle("City Bank - Login");

        GridPane grid = new GridPane();         // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Login");       // Text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));     // font for Text

        accountno_Text = new TextField();           // rectangular box to input data
        Label accountno_Lbl = new Label("AccountNo  ");     
        accountno_Lbl.setMinWidth(200);             // dimension of Label

        Label pw_Lbl = new Label("Password");       // Label for pw_Lbl
        pw_Text = new PasswordField();              // hide character-input in the field
        pw_Lbl.setMinWidth(200);                    // dimension of the Lbl

        grid.add(welcome, 0, 0);                    // adding of the Text, TextField, Label, PasswordField
                                                    // setting their position
        grid.add(accountno_Text, 1, 1);
        grid.add(accountno_Lbl, 0, 1);

        grid.add(pw_Text, 1, 2);
        grid.add(pw_Lbl, 0, 2);

        Button login = new Button("Login");
        grid.add(login, 1, 4);
        login.setMinWidth(200);

        Button back = new Button("Back");
        grid.add(back, 1, 5);
        back.setMinWidth(200);

        login.setOnAction(e -> {                    // action if login button is pressed

            int return_value = eventHandler();      // initialising return_value as eventHandler method

            if (return_value == 1) {                // if statement

                String type_pw = File.checkFile(Integer.parseInt(accountno_Text.getText()));   // retrieving pw from file     

                if (pw_Text.getText().matches(type_pw)) {       // check if password match from file

                    try {               // try-catch handling
                        window.close();                     // close stage
                        menuCustomer.menuCustomer();        // calling menuCustomer.menuCustomer
                        
                    } catch (IOException ex) {
                        DialogBox.DialogBox("An error occurred");       // use of method DialogBox.DialogBox
                    }
                    
                } else {

                    DialogBox.DialogBox("Invalid AccountNo/Password");     // calling DialogBox.DialogBox
                }
            }

        });

        back.setOnAction(e -> {             // action if back button is pressed

            window.close();                 // close stage
        });

        Scene scene = new Scene(grid);      // adding the grid to the scene
        window.setScene(scene);
        window.setMaximized(true);
        window.show();

    }

    public static int eventHandler() {          // validation

        if (accountno_Text.getText().matches("")) { 
         
            DialogBox.DialogBox("Field empty");

        }else if (pw_Text.getText().matches("")) { 
         
            DialogBox.DialogBox("Field empty");

        }else if (!accountno_Text.getText().matches("^[0-9]+$")) {

            DialogBox.DialogBox("AccountNo should consist of numbers only");
        } else {

            return 1;           // return value of 1
        }

        return 0;               // return value of 0 
    }

}       // closing of ExistingAccount()
