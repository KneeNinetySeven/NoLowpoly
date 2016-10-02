package org.knee.nonopoly.felder.implementations;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.felder.abstracts.Feld;

/**
 * Created by Nils on 24.09.2016.
 */
public class SteuerFeld extends Feld {

    private int steuer;
    private Steuertopf steuertopf;

    public SteuerFeld(String name, Steuertopf steuertopf, int steuer) {
        super(name);
        this.steuer = steuer;
        this.steuertopf = steuertopf;
    }

    public int getSteuer() {
        return steuer;
    }

    @Override
    public void fuehrePflichtAktionAus(Spieler spieler){
        spieler.ueberweiseAn(this.getSteuer(), this.steuertopf);
    }
}
