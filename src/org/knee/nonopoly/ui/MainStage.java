package org.knee.nonopoly.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.knee.nonopoly.ui.Event.KlickEvent;

/**
 * Created by Nils on 02.10.2016.
 */
public class MainStage extends Application {

    private final String windowTitle = "NoNopoly - Dat Game";

    public MainStage(){

    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Primary Stage vorbereiten
        primaryStage.setTitle(windowTitle);

        //Komponenten vorbereiten
        Button button = new Button("TEST");
        button.setOnAction(new KlickEvent());

        //Komonenten zusammensetzen
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        //Scene aufsetzen
        Scene scene = new Scene(layout, 600, 800);
        primaryStage.setScene(scene);

        // GO!
        primaryStage.show();
    }

}
