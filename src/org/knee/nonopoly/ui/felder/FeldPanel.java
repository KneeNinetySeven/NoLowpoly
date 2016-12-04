package org.knee.nonopoly.ui.felder;

import org.knee.nonopoly.felder.Feld;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Nils on 04.12.2016.
 * Package: org.knee.nonopoly.ui.felder
 */
public abstract class FeldPanel extends JPanel {

    private Feld feld;
    private int height, width;
    private JLabel title;

    FeldPanel(Feld feld, int height, int width){
        super();
        this.feld = feld;
        this.height = height;
        this.width = width;
        this.setup();
    }

    private void setup(){
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setMaximumSize(new Dimension(this.width, this.height));
        this.title = new JLabel(feld.getName());
        this.setBorder(new BevelBorder(1));
        this.add(title);
    }

}
