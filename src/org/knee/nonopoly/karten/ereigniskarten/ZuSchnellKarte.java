package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Ereigniskarte 12
 */
public class ZuSchnellKarte implements Karte {

    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().ueberweiseAn(300, schiedsrichter.getBank());
    }
}
