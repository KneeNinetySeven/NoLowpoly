package org.knee.nonopoly.ui.Event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.knee.nonopoly.ui.SpielerHinzufügenStage;

/**
 * Created by Nils on 02.10.2016.
 */
public class KlickEvent implements EventHandler<ActionEvent> {

    //TODO: private Schiedsrichter schiedsrichter;

    public KlickEvent() {

    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Button pressed...");
        new SpielerHinzufügenStage();
    }
}
