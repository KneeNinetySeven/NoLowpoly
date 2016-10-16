package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.Strasse;

/**
 * Created by Nils on 24.09.2016.
 */
public class RestBudgetBetrachter extends Strategie {

    private int minGuthaben;

    public RestBudgetBetrachter() {
        this.setName("Restbudget-Betrachter");
        this.minGuthaben = 5000;
    }

    @Override
    public boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld) {
        return spieler.getGuthaben() > this.minGuthaben && feld.getKaufpreis() < spieler.getGuthaben() ;
    }

    @Override
    public boolean erlaubtHausbau(Spieler spieler, Strasse feld) {
        return spieler.getGuthaben() > this.minGuthaben && feld.getHauspreis() < spieler.getGuthaben();
    }

    @Override
    public String toString(){
        return "Achtet mit Argus-Augen auf sein Budget";
    }
}
