package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class Strategie {

    private String name;

    public boolean erlaubtHausbau(Spieler spieler){
        return false;
    }

    public boolean erlaubtFeldKauf(Spieler spieler){ return false; }

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
