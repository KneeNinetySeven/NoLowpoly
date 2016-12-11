package org.knee.nonopoly.ui.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 */
public class FXApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("fxapp.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(new Scene(root, 850, 850));
        primaryStage.setOnCloseRequest(event -> {
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        primaryStage.show();
    }
}
