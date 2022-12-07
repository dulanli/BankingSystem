package bankingsystem;

import javafx.geometry.Pos;             // importing in built functions
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class employeeAccount {

    static TextField user_Text;
    static PasswordField pw_Text;

    public static void employeeAccount() {

        Stage window = new Stage();

        window.setTitle("City Bank - Administrator");

        GridPane grid = new GridPane();         // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Administrator Login");         // text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));     // font for text

        user_Text = new TextField();            // textfield
        Label user_Lbl = new Label("Username"); // label
        user_Lbl.setMinWidth(100);              // dimension

        Label pw_Lbl = new Label("Password");
        pw_Text = new PasswordField();
        pw_Lbl.setMinWidth(100);

        grid.add(welcome, 0, 0);            // adding and setting positon

        grid.add(user_Text, 1, 1);
        grid.add(user_Lbl, 0, 1);

        grid.add(pw_Text, 1, 2);
        grid.add(pw_Lbl, 0, 2);

        Button login = new Button("Login");         // adding buttons
        grid.add(login, 1, 4);                      // grid adding the button and apply position
        login.setMinWidth(200);

        Button back = new Button("Back");
        grid.add(back, 1, 5);
        back.setMinWidth(200);

        login.setOnAction(e -> {            // action if login is pressed

            int return_value = eventHandler();

            if (return_value == 1) {

                String type_pw = File.admin_checkFile(user_Text.getText());     // retrieving password from File   

                if (pw_Text.getText().matches(type_pw)) {       // check if passwords match

                    window.close();
                    menuEmployee.menuEmployee();            // calling Employee menu

                } else {

                    DialogBox.DialogBox("Invalid Username/Password");
                }
            }

        });

        back.setOnAction(e -> {         // if button back is pressed

            window.close();
        });

        Scene scene = new Scene(grid);   // adding the grid to the scene
        window.setScene(scene);
        window.setMaximized(true);       // maximise the stage
        window.show();

    }

    public static int eventHandler() {         // validation of user input 
        
        // if statements
        if (user_Text.getText().matches("")) { 
         
            DialogBox.DialogBox("Field empty");

        }else if (pw_Text.getText().matches("")) { 
         
            DialogBox.DialogBox("Field empty");

        }else if (!user_Text.getText().matches("^[a-zA-Z]+$")) {

            DialogBox.DialogBox("Username should consist of letters only");
        } else {

            return 1;           // return value of 1
        }

        return 0;               // return value of 0

    }

}           // closing of employeeAccount
