package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.Strasse;

/**
 * @author Nils
 */
public class RestBudgetBetrachter extends Strategie {

    private int minGuthaben;

    /**
     * Konstruktor
     */
    public RestBudgetBetrachter() {
        this.setName("Restbudget-Betrachter");
        this.minGuthaben = 5000;
    }

    /**
     * @param spieler
     * @param feld
     * @return Gibt zurück, ob ein Feld, nach der Strategie, gekauft werden darf
     */
    @Override
    public boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld) {
        return spieler.getGuthaben() > this.minGuthaben && feld.getKaufpreis() < spieler.getGuthaben();
    }

    /**
     * @param spieler
     * @param feld
     * @return Gibt zurück, ob ein Haus, nach der Strategie, gebaut werden darf
     */
    @Override
    public boolean erlaubtHausbau(Spieler spieler, Strasse feld) {
        return spieler.getGuthaben() > this.minGuthaben && feld.getHauspreis() < spieler.getGuthaben();
    }

    @Override
    public String toString() {
        return "Achtet mit Argus-Augen auf sein Budget";
    }
}
