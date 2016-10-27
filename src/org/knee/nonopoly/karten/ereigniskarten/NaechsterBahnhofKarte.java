package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 *         Ereigniskarte 2
 */
public class NaechsterBahnhofKarte implements Karte {

    /**
     * Der Spieler rückt auf den nächsten Bahnhof vor.
     * Diesen kann er kaufen, falls er noch nicht vergeben ist.
     * Ist der Bahnhof in Besitz eines anderen Spielers, erhält dieser
     * die doppelte Miete
     * TODO: doppelte Miete
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if (schiedsrichter.getAktiverSpieler().getPosition() < 5 || schiedsrichter.getAktiverSpieler().getPosition() > 35) {
            schiedsrichter.getAktiverSpieler().setPosition(5);
            schiedsrichter.getSpielbrett().get(5).fuehrePflichtAktionAus(schiedsrichter);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() < 15) {
            schiedsrichter.getAktiverSpieler().setPosition(15);
            schiedsrichter.getSpielbrett().get(15).fuehrePflichtAktionAus(schiedsrichter);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() < 25) {
            schiedsrichter.getAktiverSpieler().setPosition(25);
            schiedsrichter.getSpielbrett().get(25).fuehrePflichtAktionAus(schiedsrichter);
        } else if (schiedsrichter.getAktiverSpieler().getPosition() < 35) {
            schiedsrichter.getAktiverSpieler().setPosition(35);
            schiedsrichter.getSpielbrett().get(35).fuehrePflichtAktionAus(schiedsrichter);
        }
    }
}
