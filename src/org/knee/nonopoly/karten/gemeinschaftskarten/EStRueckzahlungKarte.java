package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Gemeinschaftskarte 12
 */
public class EStRueckzahlungKarte implements Karte {
    /**
     * Der Spieler erhält eine ESt-Rückzahlung.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(400, schiedsrichter.getAktiverSpieler());
    }
}
