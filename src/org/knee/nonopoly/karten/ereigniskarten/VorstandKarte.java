package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian Stölken
 *         Ereigniskarte 16
 */

public class VorstandKarte implements Karte {
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getTeilnehmer().stream().filter(Spieler::getImSpiel).forEach(spieler -> {
            schiedsrichter.getAktiverSpieler().ueberweiseAn(1000, spieler);
        });
    }
}
