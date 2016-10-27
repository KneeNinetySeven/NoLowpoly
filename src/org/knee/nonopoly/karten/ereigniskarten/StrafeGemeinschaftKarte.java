package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Ereigniskarte 11
 */
public class StrafeGemeinschaftKarte implements Karte {

    /**
     * Der Spieler kann zwischen einer Strafzahlung von 200,- oder dem
     * Ziehen der nächsten Gemeinschaftskarte wählen.
     * TODO: Wahlmöglichkeit für Strafe oder nächste Gemeinschaftskarte
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.naechsteGemeinschaftskarte();
    }
}
