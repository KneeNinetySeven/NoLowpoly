package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 * Ereigniskarte 10
 */
public class ZinsenKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(3000, schiedsrichter.getAktiverSpieler());
    }
}
