package bankingsystem;

import java.io.BufferedReader;              // importing in-built functions
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class RecentTransactions {

    static TableView<detailsExpenses> table;            // tableView takes property of detailsExpenses

    public static void RecentTransactions(int id) throws IOException {      // exception handling

        Stage window = new Stage();

        window.setTitle("City Bank - Recent Transactions");
        
        GridPane grid = new GridPane();             // setting attrivutes for grid
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Date column
        TableColumn<detailsExpenses, String> dateColumn = new TableColumn<>("Date");        // creating date column
        dateColumn.setMinWidth(100);                // setting dimension
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("expdate"));              // from detailsExpenses

        // Details column
        TableColumn<detailsExpenses, String> detailsColumn = new TableColumn<>("Details");
        detailsColumn.setMinWidth(250);
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("expdetails"));

        // Amount column
        TableColumn<detailsExpenses, Double> amtColumn = new TableColumn<>("Amount (Rs)");
        amtColumn.setMinWidth(150);
        amtColumn.setCellValueFactory(new PropertyValueFactory<>("expamount"));

        table = new TableView<>();          // creating tableview
        getDetailsFromFile(id);             // calling method getDetailsFromFile
        table.getColumns().addAll(dateColumn, detailsColumn, amtColumn);        // add the columns to the table

        grid.add(table, 0, 0);      // add the table to the gridpane
        
        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);
        window.show();

    }
    

    public static void getDetailsFromFile(int id) {             // getDetailsFromFile method

        try {
            FileReader ExpFile = new FileReader("files/expenses/" + id + "_Expenses.txt");      // Open File

            try (BufferedReader reader = new BufferedReader(ExpFile)) {         // Read the File

                String line;            // declaring line as String
                String[] array;         // String array of "array" 

                while ((line = reader.readLine()) != null) {        // read line if line is not blank
                    array = line.split(" - ");          // spliter
                    table.getItems().add(new detailsExpenses((array[0]), (array[1]), Double.parseDouble(array[2])));        // store details in the array
                }

                reader.close();         // close the reader
            }
        } catch (IOException ex) {
            DialogBox.DialogBox("An error occurred");
        }
    }

}           // closing of class RecentTransactions
