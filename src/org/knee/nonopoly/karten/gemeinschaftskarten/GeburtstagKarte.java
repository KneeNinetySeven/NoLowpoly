package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Gemeinschaftskarte 2
 */
public class GeburtstagKarte implements Karte {
    /**
     * Der Spieler erhält von jedem noch aktiven Spieler Geld zum Geburtstag.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getTeilnehmer().stream().filter(Spieler::istImSpiel).forEach(spieler -> spieler.ueberweiseAn(1000, schiedsrichter.getAktiverSpieler()));
    }
}
