package org.knee.nonopoly.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nils on 06.12.2016.
 * Package: org.knee.nonopoly.ui
 */
public class ProtokollUi extends JFrame {

    private JTextArea protokollFeld;

    public ProtokollUi() {
        this.setTitle("Debugging Help");
        this.setSize(new Dimension(600, 800));

        this.protokollFeld = new JTextArea();
        this.protokollFeld.setEditable(false);

        JScrollPane jScrollPane = new JScrollPane(this.protokollFeld);
        this.getContentPane().add(jScrollPane);
        setVisible(true);
    }

    /**
     * Schreibt ins ProtokollUi
     * @param message
     */
    public void schreibeInsProtokoll(String message){
        this.protokollFeld.append(message);
    }

    /**
     * Schreibt ins ProtokollUi
     * @param format
     * @param message
     */
    public void schreibeInsProtokoll(String format, Object[] message){
        String formattedMsg = String.format(format, message);
        this.protokollFeld.append(formattedMsg);
    }
}
