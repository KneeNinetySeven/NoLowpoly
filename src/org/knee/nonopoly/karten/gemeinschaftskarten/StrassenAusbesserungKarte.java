package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.immobilien.Strasse;
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.karten.ereigniskarten.HaeuserRenovierenKarte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Gemeinschaftskarte 1
 *         siehe Ereigniskarte 8
 */
public class StrassenAusbesserungKarte implements Karte {
    private int hausRenovierung = 800;

    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        int summe = 0;
        for (Feld feld : schiedsrichter.getSpielbrett()) {
            if (feld instanceof Strasse) {
                Strasse strasse = (Strasse) feld;
                if (strasse.getBesitzer() == schiedsrichter.getAktiverSpieler()) {
                    summe += strasse.getHausanzahl() * hausRenovierung;
                }
            }
        }
        // Cast unschön, aber bis jetzt notwendig.
    }
}
