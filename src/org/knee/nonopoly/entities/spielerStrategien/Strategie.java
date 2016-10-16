package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class Strategie {

    private String name;

    public boolean erlaubtHausbau(Spieler spieler, ImmobilienFeld feld){
        return false;
    }

    public boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld){
        return feld.getKaufpreis() < spieler.getGuthaben();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UNDEFINED STRATEGIE";
    }
}
