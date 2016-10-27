package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Ereigniskarte 4
 */
public class LosKarte implements Karte{
    /**
     * Der Spieler rückt vor bis auf Los.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().setPosition(0);
        schiedsrichter.getSpielbrett().get(0).fuehrePflichtAktionAus(schiedsrichter);
    }
}
