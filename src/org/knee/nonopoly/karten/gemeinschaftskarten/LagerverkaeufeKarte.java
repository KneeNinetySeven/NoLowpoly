package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Gemeinschaftskarte 15
 */
public class LagerverkaeufeKarte implements Karte {
    /**
     * Der Spieler erhält Geld aus Lagerverkäufen.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(500, schiedsrichter.getAktiverSpieler());
    }
}
