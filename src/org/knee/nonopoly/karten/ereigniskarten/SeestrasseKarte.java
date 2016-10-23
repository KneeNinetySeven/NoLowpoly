package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.felder.Los;
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 * Ereigniskarte 7
 */
public class SeestrasseKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(schiedsrichter.getAktiverSpieler().getPosition() > 11){ // Spieler kommt über Los
            schiedsrichter.getAktiverSpieler().setPosition(11);
            schiedsrichter.getBank().ueberweiseAn(4000, schiedsrichter.getAktiverSpieler());
        } else {
            schiedsrichter.getAktiverSpieler().setPosition(11);
        }
    }
}
