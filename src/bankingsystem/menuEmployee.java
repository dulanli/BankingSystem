package bankingsystem;

import static bankingsystem.employeeAccount.user_Text;      
import javafx.geometry.Insets;              // importing in built functions
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Scanner;
import java.io.*;                     // Import all file classes


public class menuEmployee {           // menuEmployee class

    static Employee account = new Employee();       // initialisation within the class
    static Text userText, nameText, emailText;
    static int del;

    public static void menuEmployee() {     // menuEmployee method

        String user = user_Text.getText();  // initialising user as username from employeeAccount

        Stage window = new Stage();

        window.setTitle("City Bank - Administrator");

        GridPane grid = new GridPane();     // setting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(18));

        Text welcome = new Text("Admin Panel");
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));

        java.io.File CreateFile = new java.io.File("files/admins/" + user + ".txt");     // setting path

        if (CreateFile.exists()) {          // if file exists

            try (Scanner Reader = new Scanner(CreateFile)) {            // read the file

                String[] stringData = new String[4];        // String array

                stringData[0] = Reader.nextLine();          // storing values of file into array and account class
                stringData[1] = Reader.nextLine();
                stringData[2] = Reader.nextLine();
                stringData[3] = Reader.nextLine();

                account.setperson_name(stringData[0]);
                account.setperson_password(stringData[1]);
                account.setEmployee_username(stringData[2]);
                account.setEmployee_email(stringData[3]);

            } catch (IOException e) {
                DialogBox.DialogBox("An error occurred");
            }
        }

        userText = new Text();              // text 
        userText.setText("Username: " + account.getEmployee_username());

        nameText = new Text();
        nameText.setText("Name: " + account.getperson_name());

        emailText = new Text();
        emailText.setText("E-mail: " + account.getEmployee_email());

        grid.add(welcome, 0, 0);           // grid adding the text
        grid.add(userText, 0, 2);
        grid.add(nameText, 0, 3);
        grid.add(emailText, 0, 4);

        Button delete = new Button("Delete Accounts");      // delete accounts button
        grid.add(delete, 0, 6);
        delete.setMinWidth(200);

        delete.setOnAction(e -> {               // if delete is pressed
            
            deleteMenu.deleteMenu();            // calling deleteMenu.deleteMenu class
        });

        Button loan = new Button("Account to give loan");
        grid.add(loan, 0, 7);
        loan.setMinWidth(200);

        loan.setOnAction(e -> {                // if loan button is pressed

            loanMenu.loanMenu();                // calling loanMenu.loanMenu class
        });

        Button out = new Button("Sign Out");
        grid.add(out, 0, 9);
        out.setMinWidth(200);

        out.setOnAction(e -> {                  // if sign out button is pressed

            window.close();                     // closing stage
        });

        Scene scene = new Scene(grid);          // adding the grid to the scene
        window.setScene(scene);
        window.setMaximized(true);              // maximise window
        window.show();

    }


}           // closing of class menuEmployee
