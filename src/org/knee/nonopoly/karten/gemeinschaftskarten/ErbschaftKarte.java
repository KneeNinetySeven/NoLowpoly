package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Gemeinschaftskarte 3
 */
public class ErbschaftKarte implements Karte{
    /**
     * Der Spieler erhält eine Zahlung aus einer Erbschaft.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(2000, schiedsrichter.getAktiverSpieler());
    }
}
