package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.Strasse;
import org.knee.nonopoly.logik.logging.Protokollant;

import java.util.Scanner;

/**
 * Created by Nils on 03.12.2016.
 * Package: org.knee.nonopoly.entities.spielerStrategien
 */
public class Interactive extends Strategie {

    /**
     * Konstruktor
     */
    public Interactive() {
        this.setName("Interaktiver Spieler");
    }

    private boolean readUserDecision(){
        boolean dec = false;
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        Protokollant.printAs(false, this, "Soll dat jekooft werden? [y/n] ");
        dec = reader.next().trim() == "y";
        return dec;
    }

    /**
     * @param spieler
     * @param feld
     * @return Gibt zurück, ob ein Haus, nach der Strategie, gebaut werden darf
     */
    @Override
    public boolean erlaubtHausbau(Spieler spieler, Strasse feld) {
        Protokollant.printAs(spieler, spieler.getName() + " - Soll ein neues Haus auf dem Feld " + feld.getName() + "gebaut werden?");
        Protokollant.printAs(spieler, "Preis: " + feld.getHauspreis() + " Mücken \t Du hast noch " + spieler.getGuthaben() );
        return readUserDecision();
    }

    /**
     * @param spieler
     * @param feld
     * @return Gibt zurück, ob ein Feld, nach der Strategie, gekauft werden darf
     */
    @Override
    public boolean erlaubtFeldKauf(Spieler spieler, ImmobilienFeld feld) {
        Protokollant.printAs(spieler, spieler.getName() + " - Soll ein das Feld " + feld.getName() + "gekauft werden?");
        Protokollant.printAs(spieler, "Preis: " + feld.getKaufpreis() + " Mücken	\t Du hast noch " + spieler.getGuthaben());
        return readUserDecision();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
