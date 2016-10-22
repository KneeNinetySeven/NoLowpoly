package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Ereigniskarte 13
 */
public class OpernplatzKarte implements Karte{

    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(schiedsrichter.getAktiverSpieler().getPosition() > 24){ // Spieler kommt über Los
            schiedsrichter.getAktiverSpieler().setPosition(24);
            schiedsrichter.getBank().ueberweiseAn(4000, schiedsrichter.getAktiverSpieler());
        } else {
            schiedsrichter.getAktiverSpieler().setPosition(24);
        }
    }

}
