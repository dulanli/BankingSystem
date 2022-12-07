package bankingsystem;

import javafx.geometry.Pos;         // importing in built functions
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class deleteMenu {           // deleteMenu class

    static TextField msg_Text;      // able to use everywhere
    static Text txt;

    public static void deleteMenu() {       // deleteMenu method

        Stage window = new Stage();

        window.setTitle("Delete Account");

        GridPane grid = new GridPane();     // setting attributes of grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("Delete Account Menu");             // Text
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 20)); // font for Text

        Label msg_Lbl = new Label("List of Accounts:");         // Label message
        msg_Text = new TextField("AccountNo");                  // TextField 
        msg_Lbl.setMinWidth(100);       // dimension

        grid.add(welcome, 0, 0);        // add the Text, Label, TextField

        grid.add(msg_Text, 1, 1);
        grid.add(msg_Lbl, 0, 1);

        Button OK = new Button("OK");    // button OK
        grid.add(OK, 1, 4);
        OK.setMinWidth(100);

        Button back = new Button("Back");    // button Back
        grid.add(back, 1, 5);
        back.setMinWidth(100);

        java.io.File f = new java.io.File("files/accounts/");       // Open directory

        String[] files = f.list();              // returns an array of Strings containing names of files

        // Display the names of the files
        for (int j = 0; j < files.length; j++) {            // loop until number of files
            txt = new Text();               // Text message
            txt.setText(files[j]);          // display Text message
            grid.add(txt, 0, j + 2);        // setting position of Text message
        }

        OK.setOnAction(e -> {           // OK button 

            int return_value = eventHandler();      // initialising return_value as eventHandler method

            if (return_value == 1) {

                int del = Integer.parseInt(msg_Text.getText());

                java.io.File deleteFile = new java.io.File("files/accounts/" + del + ".txt");       // setting path
                java.io.File deleteExpFile = new java.io.File("files/expenses/" + del + "_Expenses.txt");   // setting path

                if (deleteFile.delete()) {      // if delete File is succesful
                    deleteExpFile.delete();     // delete Exp Acc
                    DialogBox.DialogBox("Account [" + del + "] was successfully deleted");
                    window.close();     // close stage

                } else {
                    DialogBox.DialogBox("Account Not Found");
                }
            }
        });

        back.setOnAction(e -> {           // Back button 
            
            window.close();               // close the stage
        });

        Scene scene = new Scene(grid, 500, 500);        // adding the grid to the scene and dimension
        window.setScene(scene);
        window.show();

    }

    public static int eventHandler() {          // validation

        if (msg_Text.getText().matches("")) {
            DialogBox.DialogBox("Field empty");

        } else if (!msg_Text.getText().matches("^[0-9]+$")) {
            DialogBox.DialogBox("Entry should consist of numbers only");

        } else {
            return 1;       // return value of 1
        }

        return 0;           // return value of 0

    }

}           // closing of class deleteMenu()
