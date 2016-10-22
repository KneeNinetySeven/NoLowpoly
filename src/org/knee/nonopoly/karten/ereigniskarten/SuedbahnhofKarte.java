package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 * Ereigniskarte 6
 */
public class SuedbahnhofKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(schiedsrichter.getAktiverSpieler().getPosition() > 5){ // Spieler kommt über Los
            schiedsrichter.getAktiverSpieler().setPosition(5);
            schiedsrichter.getBank().ueberweiseAn(4000, schiedsrichter.getAktiverSpieler());
        } else {
            schiedsrichter.getAktiverSpieler().setPosition(5);
        }
    }
}
