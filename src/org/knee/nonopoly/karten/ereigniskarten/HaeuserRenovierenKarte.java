package org.knee.nonopoly.karten.ereigniskarten;

import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.FeldTypen;
import org.knee.nonopoly.felder.immobilien.ImmobilienFeld;
import org.knee.nonopoly.felder.immobilien.ImmobilienTypen;
import org.knee.nonopoly.felder.immobilien.Strasse;
import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Adrian
 *         Ereigniskarte 8
 *         siehe Gemeinschaftskarte 1
 */
public class HaeuserRenovierenKarte implements Karte {
    private int hausRenovierung = 500;

    /**
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        int summe = 0;
        for (Feld feld : schiedsrichter.getSpielbrett()) {
            if (feld.istVomTyp(FeldTypen.IMMOBILIENFELD)) {
                ImmobilienFeld immobilienFeld = (ImmobilienFeld) feld;
                if (immobilienFeld.istImmobilienTyp(ImmobilienTypen.STRASSE)) {
                    Strasse strasse = (Strasse) immobilienFeld;
                    if (strasse.getBesitzer() == schiedsrichter.getAktiverSpieler()) {
                        summe += strasse.getHausanzahl() * hausRenovierung;
                    }
                }
            }
        }
        // Cast unschön, aber bis jetzt notwendig.
    }
}
