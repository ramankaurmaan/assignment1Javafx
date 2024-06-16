package org.example.immigration_stats_java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The Main class serves as the entry point for the JavaFX application.
 * It loads the main view from an FXML file, sets the stage with a scene,
 * and applies an icon and stylesheet to the application window.
 */
public class Main extends Application {

    /**
     * The start method is the main entry point for all JavaFX applications.
     * It initializes and sets up the primary stage (window) of the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view from the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/immigration_stats_java/fxml/MainView.fxml")));

        // Load and set the application icon
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/example/immigration_stats_java/images/immigration_image.jpg")));
        primaryStage.getIcons().add(icon);

        // Create a scene with the loaded root node and set its size
        Scene scene = new Scene(root, 800, 800);

        // Load and apply the stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/org/example/immigration_stats_java/styles/styles.css")).toExternalForm());

        // Set the scene on the primary stage and configure the stage properties
        primaryStage.setScene(scene);
        primaryStage.setTitle("Immigration Statistics in Canada");
        primaryStage.show();
    }

    /**
     * The main method is the entry point for the Java application.
     * It launches the JavaFX application by calling the launch method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
