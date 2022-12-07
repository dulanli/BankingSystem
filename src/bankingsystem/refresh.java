package bankingsystem;

import java.io.IOException;             // importing in built functions
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;


public class refresh {          // class to refresh menuCustomer stage
    
        public static void refresh() {          // refresh method

        Stage window = new Stage();
        
        window.setTitle("Message");             // title

        Label label = new Label();              // label 
        label.setText("Refresh screen?");       // text
        label.setFont(Font.font("Avenir", FontWeight.LIGHT, 14));       // font for text

        Button Yes = new Button("Yes");     // Yes button
        Yes.setMinWidth(100);               // setting dimension
        
        Yes.setOnAction(e -> {              // if yes is pressed
            try {                           // try-catch
                menuCustomer.menuCustomer();            // call menuCustomer class
                
            } catch (IOException ex) {
                window.close();
            }
            window.close();             // close window
        });
        
        VBox layout = new VBox(10);     // setting attributes for layout
        layout.getChildren().addAll(label, Yes);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 350, 85);       // adding the layout and dimension to the scene

        window.setScene(scene);
        window.show();
            
            
        }

}           // closing of class refresh
