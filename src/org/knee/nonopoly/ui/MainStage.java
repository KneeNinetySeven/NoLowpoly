package org.knee.nonopoly.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 02.10.2016.
 */
public class MainStage extends Application {

    //Spieldaten
    private Schiedsrichter spiel;

    //UIdaten
    private final String windowTitle = "NoNopoly - Dat Game";
    private Stage window;
    private Scene scene1, scene2;
    private BorderPane layout;
    private MenuBar menuBar;

    public MainStage() {
        //TODO: this.spiel = schiedsrichter;
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        //Menu
        menuBar = new MenuBar();
        Menu menu = new Menu("Menü");
        menu.getItems().add(new MenuItem("Test"));
        menuBar.getMenus().add(menu);

        // Main Layout
        layout = new BorderPane();
        layout.setTop(menuBar);

        VBox context1 = new VBox();
        HBox context2 = new HBox();

        Button button1 = new Button("Switch to 2");
        button1.setOnAction(event -> layout.setCenter(context2));
        Label label1 = new Label("1");
        context1.getChildren().addAll(button1, label1);

        Button button2 = new Button("Switch to 1");
        button2.setOnAction(event -> layout.setCenter(context1));
        Label label2 = new Label("2");
        context2.getChildren().addAll(button2,label2);

        layout.setCenter(context1);
        scene1 = new Scene(layout, 500, 500);

        window.setScene(scene1);
        window.setTitle(windowTitle);
        window.show();
    }

    public void refresh() {
        window.close();
        Platform.runLater(() -> {
            try {
                new MainStage().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
