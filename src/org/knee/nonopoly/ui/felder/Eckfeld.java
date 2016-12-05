package org.knee.nonopoly.ui.felder;

import org.knee.nonopoly.felder.Feld;

import java.awt.*;

/**
 * Created by Nils on 04.12.2016.
 * Package: org.knee.nonopoly.ui.felder
 */
public class Eckfeld extends FeldPanel{

    public Eckfeld(Feld feld, int height, int width) {
        super(feld, height, width);
        this.setBackground(Color.lightGray);
    }
}
