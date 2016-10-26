package org.knee.nonopoly.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Nils on 26.10.2016.
 * Package: org.knee.nonopoly.ui
 */
public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    @FXML
    TextArea textArea;

    @FXML
    Label label;

    @FXML
    protected void buttonPressed() {
        System.out.println("Test");
    }

}
