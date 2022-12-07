package bankingsystem;

import javafx.geometry.Pos;             // importing in-built functions
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class DialogBox {               // start of DialogBox class

    static TextField msg_Text;          // able to use TextField within the class

    public static void DialogBox(String message) {      // DialogBox method

        Stage window = new Stage();

        window.setTitle("Message");

        Label label = new Label();
        label.setText(message);                 // message to be input
        label.setFont(Font.font("Avenir", FontWeight.LIGHT, 14));       // font of the message

        Button OK = new Button("OK");           // OK button
        OK.setMinWidth(100);                    // setting dimension
        OK.setOnAction(e -> {
            
            window.close();
        });
        
        
        VBox layout = new VBox(10);             // setting attributes for layout
        layout.getChildren().addAll(label, OK);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 85);   // adding the layout and dimension to the scene

        window.setScene(scene);
        window.showAndWait();

    }
    
    public static int eventHandler() {              // validation of input
        
        // if sttaements
        if (msg_Text.getText().matches("")) { 
         
            DialogBox.DialogBox("Field empty");

        }else if (msg_Text.getText().matches("")) { 
         
            DialogBox.DialogBox("Field empty");

        }else if (!msg_Text.getText().matches("^[0-9]+$")) {

            DialogBox.DialogBox("AccountNo should consist of numbers only");
        } else {

            return 1;       // return value of 1
        }

        return 0;           // return value of 0
    }       

}           // closing of class DialogBox()
