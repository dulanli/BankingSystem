package bankingsystem;

import javafx.application.Application;              // importing in-built functions
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Dylan
 */
public class BankingSystem extends Application {

    Stage window;

    public static void main(String[] args) {            // main method
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {             // start method

        window = primaryStage;                          // initialising window as primaryStage
        window.setTitle("City Bank - Main Menu");       // title of the stage

        GridPane grid = new GridPane();                 // setting attributes of gridpane
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Text welcome = new Text("                City bank");               // text message
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));         // font for the text message

        Button button1 = new Button("Create an account");                   // creating buttons
        Button button2 = new Button("Use an existing account");
        Button button3 = new Button("Administrator Login");
        Button exit = new Button("Exit");

        grid.add(welcome, 1, 1);                    // add the buttons
        grid.add(button1, 1, 2);
        grid.add(button2, 1, 3);
        grid.add(button3, 1, 4);
        grid.add(exit, 1, 6);

        button1.setFocusTraversable(false);         // remove the surrounding highlight on the button
        button2.setFocusTraversable(false);
        button3.setFocusTraversable(false);
        exit.setFocusTraversable(false);
        
        button1.setMinWidth(350);                   // set the dimension of the buttons
        button2.setMinWidth(350);
        button3.setMinWidth(350);
        exit.setMinWidth(350);

        button1.setOnAction(e -> {                  // if button1 is pressed
            CreateNewAccount.CreateNewAccount();    // calling class
        });

        button2.setOnAction(e -> {                  // if button2 is pressed
            ExistingAccount.ExistingAccount();      // calling class
        });

        button3.setOnAction(e -> {                  // if button2 is pressed
            employeeAccount.employeeAccount();      // calling class
        });

        exit.setOnAction(e -> {                     // if exit button is pressed
            System.exit(0);                         // exit applicaiton
        });

        Scene scene = new Scene(grid);              // adding the grid to the scene

        window.setScene(scene);
        window.setMaximized(true);
        window.show();

    }

}
