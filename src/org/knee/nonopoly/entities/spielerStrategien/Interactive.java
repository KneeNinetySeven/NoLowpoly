package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.Strasse;
import org.knee.nonopoly.logik.logging.Protokollant;

import javax.swing.*;
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

    /**
     * Liest eine Frage aus der Konsole ab
     * @deprecated
     * @return Returns if the answer was y or not.
     */
    @Deprecated
    private boolean readUserDecision() {
        boolean dec = false;
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        Protokollant.printAs(false, this, "Soll dat jekooft werden? [y/n] ");
        dec = reader.next().trim() == "y";
        return dec;
    }

    /**
     * @param spieler Der abgefrage Spieler
     * @param feld Das Feld auf dem der Spieler grade steht
     * @return Gibt zurück, ob ein Haus, nach der Strategie, gebaut werden darf
     */
    @Override
    public boolean erlaubtHausbau(Spieler spieler, Strasse feld) {
        Protokollant.printAs(spieler, spieler.getName() + " - Soll ein neues Haus auf dem Feld " + feld.getName() + "gebaut werden?");
        Protokollant.printAs(spieler, "Preis: " + feld.getHauspreis() + " Mücken \t Du hast noch " + spieler.getGuthaben());
        return JOptionPane.showConfirmDialog(null, spieler.getName().toUpperCase() + " ist dran! \n \n Soll das Feld " + feld.getName() + " mit einem weiteren Haus" +
                " bebaut werden? \n Kosten: " + feld.getHauspreis(), "Bauoptionen", 0, 3) == 0;
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
        return JOptionPane.showConfirmDialog(null, spieler.getName().toUpperCase() + " ist dran! \n \n Soll das Feld " + feld.getName() +
                " gekauft werden? \n Kosten: " + feld.getKaufpreis(), "Kaufoptionen", 0, 3) == 0;
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
