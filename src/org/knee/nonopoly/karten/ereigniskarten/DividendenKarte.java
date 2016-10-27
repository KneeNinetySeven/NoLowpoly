package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Ereigniskarte 9
 */
public class DividendenKarte implements Karte {
    /**
     * Der Spieler erhält eine Dividendenzahlung
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(1000, schiedsrichter.getAktiverSpieler());
    }
}
