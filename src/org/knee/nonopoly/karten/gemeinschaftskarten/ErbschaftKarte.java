package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 * Gemeinschaftskarte 3
 */
public class ErbschaftKarte implements Karte{
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(2000, schiedsrichter.getAktiverSpieler());
    }
}
