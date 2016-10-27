package org.knee.nonopoly.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 26.10.2016.
 * Package: org.knee.nonopoly.ui
 */
public class Controller {

    Schiedsrichter schiedsrichter;

    @FXML
    private VBox spielerListe;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        schiedsrichter = new Schiedsrichter();
        schiedsrichter.getTeilnehmer().forEach(s -> spielerListe.getChildren().add(new SpielerNode(s).generate()));
    }

    @FXML
    protected void spielerHinzufuegen() {
        schiedsrichter.registriereSpieler("Nils", new AllesKaeufer());
        spielerListe.getChildren().add(new SpielerNode(schiedsrichter.getTeilnehmer().get(0)).generate());
    }

}
