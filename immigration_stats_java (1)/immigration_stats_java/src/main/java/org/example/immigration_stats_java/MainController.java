package org.example.immigration_stats_java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The MainController class handles the interaction between the UI components
 * and the underlying data for the immigration statistics application.
 */
public class MainController {

    // UI components defined in the FXML file
    @FXML
    private VBox tableViewBox;  // Container for the TableView
    @FXML
    private VBox pieChartBox;  // Container for the PieChart
    @FXML
    private TableView<ImmigrationData> tableView;  // TableView to display immigration data
    @FXML
    private TableColumn<ImmigrationData, String> yearColumn;  // Column for the year
    @FXML
    private TableColumn<ImmigrationData, Integer> numberColumn;  // Column for the number of immigrants
    @FXML
    private Button switchToPieChartButton;  // Button to switch to PieChart view
    @FXML
    private Button switchToTableViewButton;  // Button to switch to TableView view
    @FXML
    private PieChart pieChart;  // PieChart to display immigration data

    /**
     * The initialize method is called after the FXML file has been loaded.
     * It sets up the TableView and PieChart with data from the database,
     * and sets up button actions to switch between views.
     */
    @FXML
    public void initialize() {
        try {
            // Database connection parameters
            String url = "jdbc:mysql://localhost:3306/immigration_db";
            String user = "root";
            String password = "";

            // Establishing the connection to the database
            Connection conn = DriverManager.getConnection(url, user, password);

            // Setting up the TableView and PieChart with data from the database
            setupTableView(conn);
            setupPieChart(conn);

            // Closing the database connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set action to switch to PieChart view
        switchToPieChartButton.setOnAction(e -> {
            tableViewBox.setVisible(false);  // Hide the TableView container
            pieChartBox.setVisible(true);  // Show the PieChart container
        });

        // Set action to switch to TableView view
        switchToTableViewButton.setOnAction(e -> {
            pieChartBox.setVisible(false);  // Hide the PieChart container
            tableViewBox.setVisible(true);  // Show the TableView container
        });
    }

    /**
     * Sets up the TableView with data from the database.
     */
    private void setupTableView(Connection conn) throws Exception {
        // Set up cell value factories for the TableView columns
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));  // Bind year column to the year property
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));  // Bind number column to the number property

        // Query to get immigration data from the database
        String query = "SELECT year, number FROM immigration_stats";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        // Populate the TableView with data from the database
        List<ImmigrationData> immigrationList = new ArrayList<>();
        while (resultSet.next()) {
            String year = resultSet.getString("year");
            int number = resultSet.getInt("number");
            ImmigrationData immigrationData = new ImmigrationData(year, number);
            immigrationList.add(immigrationData);
        }

        // Convert the list to an observable list and set it to the TableView
        ObservableList<ImmigrationData> data = FXCollections.observableArrayList(immigrationList);
        tableView.setItems(data);

        // Adjusting table size to display all rows and columns
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        resultSet.close();  // Close the ResultSet
        stmt.close();  // Close the Statement

        // Adjusting preferred width of columns
        yearColumn.setPrefWidth(800);  // Set preferred width for year column
        numberColumn.setPrefWidth(600);  // Set preferred width for number column
    }

    /**
     * Sets up the PieChart with data from the database.
     */
    private void setupPieChart(Connection conn) throws Exception {
        // Query to get immigration data from the database
        String query = "SELECT year, number FROM immigration_stats";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);

        // Populate the PieChart with data from the database
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String year = resultSet.getString("year");
            int number = resultSet.getInt("number");
            PieChart.Data data = new PieChart.Data(year, number);
            pieChartData.add(data);
        }

        pieChart.setData(pieChartData);  // Set the data for the PieChart
        pieChart.setTitle("Immigration Statistics in Canada Pie Chart");  // Set the title for the PieChart

        // Customizing pie chart colors
        List<Color> colors = new ArrayList<>();
        colors.add(Color.rgb(0, 128, 0));  // Green
        colors.add(Color.rgb(255, 0, 0));  // Red
        colors.add(Color.rgb(0, 0, 255));  // Blue
        colors.add(Color.rgb(255, 165, 0));  // Orange
        colors.add(Color.rgb(128, 0, 128));  // Purple
        colors.add(Color.rgb(255, 255, 0));  // Yellow
        colors.add(Color.rgb(0, 255, 255));  // Cyan
        colors.add(Color.rgb(255, 192, 203));  // Pink
        colors.add(Color.rgb(255, 140, 0));  // DarkOrange
        colors.add(Color.rgb(128, 0, 0));  // Maroon

        // Applying colors to pie chart slices
        int colorIndex = 0;
        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().setStyle("-fx-pie-color: #" + toRGBCode(colors.get(colorIndex)));  // Set the color for each slice
            colorIndex = (colorIndex + 1) % colors.size();  // Rotate through the list of colors
        }

        // Increase the size of the pie chart
        pieChart.setPrefWidth(800);  // Set preferred width for the PieChart
        pieChart.setPrefHeight(600);  // Set preferred height for the PieChart

        resultSet.close();  // Close the ResultSet
        stmt.close();  // Close the Statement
    }

    /**
     * Helper method to convert a Color object to its RGB hexadecimal code.
     */
    private String toRGBCode(Color color) {
        // Convert the Color object to an RGB hexadecimal code string
        return String.format("%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
