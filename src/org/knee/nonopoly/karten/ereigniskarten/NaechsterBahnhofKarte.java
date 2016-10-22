package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 *         Ereigniskarte 2
 *         TODO: Implement doppelte Miete und Kaufmöglichkeit
 */
public class NaechsterBahnhofKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if (schiedsrichter.getAktiverSpieler().getPosition() < 5) {
            schiedsrichter.getAktiverSpieler().setPosition(5);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() < 15) {
            schiedsrichter.getAktiverSpieler().setPosition(15);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() < 25) {
            schiedsrichter.getAktiverSpieler().setPosition(25);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() < 35) {
            schiedsrichter.getAktiverSpieler().setPosition(35);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() > 35) {
            schiedsrichter.getAktiverSpieler().setPosition(5);
        }
    }
}
