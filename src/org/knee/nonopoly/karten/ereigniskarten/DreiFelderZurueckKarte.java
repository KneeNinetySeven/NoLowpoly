package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Ereigniskarte 3
 */
public class DreiFelderZurueckKarte implements Karte {
    /**
     * Der Spieler wird drei felder zurück gesetzt.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().setPosition(schiedsrichter.getAktiverSpieler().getPosition() - 3);
    }
}
