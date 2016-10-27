package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Ereigniskarte 12
 */
public class ZuSchnellKarte implements Karte {

    /**
     * Der Spieler zahlt eine Strafe für zu schnelles Fahren an die Bank.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().ueberweiseAn(300, schiedsrichter.getBank());
    }
}
