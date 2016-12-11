package org.knee.nonopoly.ui.fx;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.Interactive;
import org.knee.nonopoly.logik.Schiedsrichter;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * Created by Adrian on 08.12.2016.
 */
public class Controller {

//    TODO: Zuordnung Felder zu UI

    @FXML
    private ListView<Spieler> spielerListe;

    @FXML
    private Circle circle;

    @FXML
    private TextField testFeld;

    private Schiedsrichter schiedsrichter;

    public Controller() throws InstantiationException, IllegalAccessException {
        this.schiedsrichter = new Schiedsrichter();
    }

    @FXML
    public void spielerHinzufuegen() {
        boolean done = false;

        TextInputDialog neuerSpielerDialog = new TextInputDialog();
        neuerSpielerDialog.setTitle("Neuen Spieler hinzufügen");
        neuerSpielerDialog.setContentText("Bitte Namen eingeben:");


        if (schiedsrichter.getTeilnehmer().size() < 6) {
            while (!done) {
                Optional<String> result = neuerSpielerDialog.showAndWait();
                if (result.isPresent()) {
                    String spielername = result.get();

//                Protokollant.printAs(this, "Eingegebener Name: " + spielername);

                    if (schiedsrichter.getTeilnehmer().stream().noneMatch(spieler -> spieler.getName().equalsIgnoreCase(spielername))) {
                        if (!spielername.equalsIgnoreCase("") && spielername != null) {
                            try {
                                schiedsrichter.registriereSpieler(spielername, Interactive.class);
                                done = true;
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InstantiationException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Fehler!");
                            alert.setContentText("Ungültiger Spielername");
                            alert.showAndWait();
                        }
                    } else {
//                    TODO: new NameSchonVergebenException();
                    }
                } else {
//                TODO: EXCEPTION ResultNotPresent
                    done = true;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler!");
            alert.setContentText("Es sind schon 6 Spieler vorhanden!");
//                TODO: Protokollant
        }
    }


    @FXML
    public void setItems(){
        this.spielerListe.setItems(FXCollections.observableList(this.schiedsrichter.getTeilnehmer()));
    }

    @FXML
    public void changeName() {
        this.schiedsrichter.getTeilnehmer().get(this.schiedsrichter.getTeilnehmer().size() - 1).setName("Bfff");
    }

    public void setChangeListener(){
        this.schiedsrichter.getTeilnehmer().addListener(new ListChangeListener<Spieler>() {
            @Override
            public void onChanged(Change<? extends Spieler> c) {
                spielerListe.itemsProperty().bind(new SimpleListProperty<Spieler>(schiedsrichter.getTeilnehmer()));
            }
        });
    }


    @FXML
    public void zeigeFeldDetails() {
//        testFeld.textProperty().bind(new SimpleStringProperty(this.schiedsrichter.getTeilnehmer().get(0).getName()));
        System.out.println("Feld-Details anzeigen");
        circle.setVisible(false);
    }

    @FXML
    public void mouseEntered() {
        System.out.println("Mouse entered");
    }

    @FXML
    public void mouseExited() {
        System.out.println("Mouse exited");
    }


    @FXML
    public void closeApp() {

    }

}
