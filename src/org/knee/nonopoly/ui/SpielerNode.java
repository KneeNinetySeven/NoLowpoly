package org.knee.nonopoly.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.knee.nonopoly.entities.Spieler;

/**
 * Created by Nils on 27.10.2016.
 * Package: org.knee.nonopoly.ui
 */
public class SpielerNode implements EntityNode{

    private Spieler spieler;

    public SpielerNode(Spieler spieler){
        this.spieler = spieler;
    }

    public Pane generate(){
        Pane p = new Pane();

        VBox label = new VBox();
        label.getChildren().add(new Label("Name"));
        label.getChildren().add(new Label("Guthaben"));
        label.getChildren().add(new Label("Position"));
        label.getChildren().add(new Label("Strategie"));
        label.setPadding(new Insets(10));


        VBox playerData = new VBox();
        playerData.getChildren().add(new Label(spieler.getName()));
        playerData.getChildren().add(new Label(String.valueOf(spieler.getGuthaben())));
        playerData.getChildren().add(new Label(String.valueOf(spieler.getPosition())));
        playerData.getChildren().add(new Label(spieler.getStrategie().toString()));
        playerData.setPadding(new Insets(10));

        HBox sort = new HBox();
        sort.getChildren().add(label);
        sort.getChildren().add(playerData);
        sort.setPadding(new Insets(10));

        p.getChildren().add(sort);

        return p;
    }

}
