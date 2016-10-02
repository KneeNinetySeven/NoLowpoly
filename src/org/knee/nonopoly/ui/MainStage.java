package org.knee.nonopoly.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Nils on 02.10.2016.
 */
public class MainStage extends Application {

    private final String windowTitle = "NoNopoly - Dat Game";
    private Stage window;
    private Scene scene1, scene2;

    public MainStage() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle(windowTitle);

        Button button = new Button("CLICK ME");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();

        /*      SWITCHING BETWEEN SCENES
        window = primaryStage;
        Label label1 = new Label("Welcome to Scene 1");
        Button button1 = new Button("Switch Scene to 2");
        button1.setOnAction(e -> window.setScene(scene2));

        // Layout 1 - Vertical Column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        Label label2 = new Label("Welcome to Scene 2");
        Button button2 = new Button("Switch to Scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle(windowTitle);
        window.show();
        */
        //--------------------------------------------------

        //Primary Stage vorbereiten
        //primaryStage.setTitle(windowTitle);

        /*//Komponenten vorbereiten
        Button button = new Button("TEST");
        button.setOnAction(e -> {
            System.out.println("BUTTON");
        });

        //Komonenten zusammensetzen
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        //Scene aufsetzen
        Scene scene = new Scene(layout, 600, 800);
        primaryStage.setScene(scene);

        // GO!
        primaryStage.show();
   */
    }

}
