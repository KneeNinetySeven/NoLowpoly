package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Ereigniskarte 10
 */
public class ZinsenKarte implements Karte {
    /**
     * Der Spieler erhält eine Zinszahlung von der Bank
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(3000, schiedsrichter.getAktiverSpieler());
    }
}
