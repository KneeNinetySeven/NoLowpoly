package org.knee.nonopoly.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Nils on 26.10.2016.
 * Package: org.knee.nonopoly.ui
 */
public class FXML_Intergation extends Application{

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("window.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
