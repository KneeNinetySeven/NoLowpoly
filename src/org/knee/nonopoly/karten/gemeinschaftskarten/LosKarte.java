package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Gemeinschaftskarte 8
 *         identisch zu Ereigniskarte 4
 */
public class LosKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().setPosition(0);
        schiedsrichter.getSpielbrett().get(0).fuehrePflichtAktionAus(schiedsrichter);
    }
}