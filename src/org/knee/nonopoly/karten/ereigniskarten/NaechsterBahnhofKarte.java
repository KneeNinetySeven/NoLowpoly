package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 * Ereigniskarte 2
 * TODO: Implement
 */
public class NaechsterBahnhofKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(schiedsrichter.getAktiverSpieler().getPosition() < 5){

        } else if(schiedsrichter.getAktiverSpieler().getPosition() < 15) {

        } else if(schiedsrichter.getAktiverSpieler().getPosition() < 25) {

        } else if(schiedsrichter.getAktiverSpieler().getPosition() < 35) {

        }
    }
}
