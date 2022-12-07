package bankingsystem;

import static bankingsystem.ExistingAccount.accountno_Text;         // importing in-built functions
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.*;                               // Import all file classes
import java.time.format.DateTimeFormatter;      // Import date classes


public class menuCustomer {         // menuCustomer class

    static Text accountno, nameText, balanceText;           // able to use these variables anywhere in the class
    static Customer account = new Customer();
    static int id;
    static String name;
    static String pw;
    static double bal;
    static double sal;

    public static void menuCustomer() throws IOException{       // menuCustomer method

        int no = Integer.parseInt(accountno_Text.getText());    // initialising no as accountno typed in existingaccount() method

        Stage window = new Stage();

        window.setTitle("City Bank - Menu");

        GridPane grid = new GridPane();         // settting attributes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(18));

        Text welcome = new Text("           Customer Menu");
        welcome.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));

        java.io.File CreateFile = new java.io.File("files/accounts/" + no + ".txt");            // setting path

        if (CreateFile.exists()) {              // if file exists

            try (Scanner Reader = new Scanner(CreateFile)) {        // Read the file

                String[] stringData = new String[2];        // String array
                int[] intData = new int[1];                 // int array
                Double[] doubleData = new Double[2];        // Double array

                stringData[0] = Reader.nextLine();          // assignment value read from file
                stringData[1] = Reader.nextLine();
                intData[0] = Reader.nextInt();
                doubleData[0] = Reader.nextDouble();
                doubleData[1] = Reader.nextDouble();

                account.setperson_name(stringData[0]);      // set the values
                account.setperson_password(stringData[1]);
                account.setCustomer_accountno(intData[0]);
                account.setCustomer_balance(doubleData[0]);
                account.setCustomer_salary(doubleData[1]);

            } catch (IOException e) {
                DialogBox.DialogBox("An error occurred");   // display message box
            }
        } else {
            DialogBox.DialogBox("Invalid Entry");
        }

        id = account.getCustomer_accountno();           // get and set the value for simpler variables
        name = account.getperson_name();
        pw = account.getperson_password();
        bal = account.getCustomer_balance();
        sal = account.getCustomer_salary();

        accountno = new Text();                         // setting text
        accountno.setText("AccountNo: " + id);

        nameText = new Text();
        nameText.setText("Name: " + name);

        balanceText = new Text();
        balanceText.setText("Balance: " + bal);

        grid.add(welcome, 0, 0);                        // grid adding text
        grid.add(accountno, 0, 2);
        grid.add(nameText, 0, 3);
        grid.add(balanceText, 0, 4);

        Button withdraw = new Button("Withdraw");       // withdraw button
        grid.add(withdraw, 0, 8);
        withdraw.setMinWidth(350);

        withdraw.setOnAction(e -> {         // if withdraw button is pressed

            withdrawMenu.withdrawMenu(id, name, pw, bal, sal);              // calling withdrawMenu.withdrawMenu
            window.close();
        });

        Button deposit = new Button("Deposit");              // deposit button
        grid.add(deposit, 0, 9);
        deposit.setMinWidth(350);

        deposit.setOnAction(e -> {          

            depositMenu.depositMenu(id, name, pw, bal, sal); // calling class depositMenu.depositMenu
            window.close();                                  // close stage
        });

        Button transfer = new Button("Transfer Money to AccountNo");          // transfer button
        grid.add(transfer, 0, 10);
        transfer.setMinWidth(350);

        transfer.setOnAction(e -> {
            
            transferMenu.transferMenu(id, name, pw, bal, sal);      // calling transferMenu.transferMenu
            window.close();

        });

        Button transac = new Button("Recent Transactions");         // recent transactions button
        grid.add(transac, 0, 11);
        transac.setMinWidth(350);

        transac.setOnAction(e -> {          // if button is pressed
            
            try {               // try-catch
                RecentTransactions.RecentTransactions(id);      // calling method 

            } catch (IOException ex) {
                DialogBox.DialogBox("An error occurred");
            }

        });

        Button out = new Button("Sign Out");
        grid.add(out, 0, 13);
        out.setMinWidth(350);

        out.setOnAction(e -> {          // if button sign out is pressed

            window.close();             // close stage
        });

        Scene scene = new Scene(grid);  // adding the grid to the scene
        window.setMaximized(true);      // maximise the window
        window.setScene(scene);
        window.showAndWait();

    }

    public static String date() {               // date method

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.now();       // get today's date
        String datenow = dtf.format(date);

        return datenow;         // return value of datenow

    }
    

}               // closing of class menuCustomer
