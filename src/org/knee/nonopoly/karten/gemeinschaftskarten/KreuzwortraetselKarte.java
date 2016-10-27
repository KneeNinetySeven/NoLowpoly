package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 * Gemeinschaftskarte 13
 */
public class KreuzwortraetselKarte implements Karte{
    /**
     * Der Spieler erhält ein Preisgeld für einen Kreuzworträtselwettbewerb.
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getBank().ueberweiseAn(2000, schiedsrichter.getAktiverSpieler());
    }
}
