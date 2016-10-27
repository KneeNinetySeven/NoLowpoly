package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Gemeinschaftskarte 11
 */
public class KrankenhausKarte implements Karte {

    /**
     * Der Spieler zahlt einen festen Betrag für einen Krankenhausaufenthalt.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().ueberweiseAn(2000, schiedsrichter.getBank());
    }
}
