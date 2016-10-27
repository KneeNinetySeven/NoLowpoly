package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Gemeinschaftskarte 16
 */
public class SchulgeldKarte implements Karte {
    /**
     * Der Spieler zahlt Schulgeld an die Bank.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().ueberweiseAn(3000, schiedsrichter.getBank());
    }
}
