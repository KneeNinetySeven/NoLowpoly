package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.Strasse;

/**
 * @author Nils
 */
public abstract class Strategie {

    private String name;

    /**
     * Konstruktor
     */
    protected Strategie(){

    }

    /**
     * @param spieler
     * @param feld
     * @return Gibt zurück, ob ein Haus, nach der Strategie, gebaut werden darf
     */
    public abstract boolean erlaubtHausbau(Spieler spieler, Strasse feld);

    /**
     *
     * @param spieler
     * @param feld
     * @return Gibt zurück, ob ein Feld, nach der Strategie, gekauft werden darf
     */
    public abstract boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld);

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
