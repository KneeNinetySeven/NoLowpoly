package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Ereigniskarte 6
 */
public class SuedbahnhofKarte implements Karte {

    /**
     * Der Spieler rückt bis zum Südbahnhof vor.
     * Es wird berücksichtigt, ob er über Los kommt.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if (schiedsrichter.getAktiverSpieler().getPosition() > 5) { // Spieler kommt über Los
            schiedsrichter.getAktiverSpieler().setPosition(5);
            schiedsrichter.getBank().ueberweiseAn(4000, schiedsrichter.getAktiverSpieler());
        } else {
            schiedsrichter.getAktiverSpieler().setPosition(5);
        }
    }
}
