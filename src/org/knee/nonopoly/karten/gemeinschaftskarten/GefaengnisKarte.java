package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * @author Nils
 */
public class GefaengnisKarte implements Karte {

    private boolean istVergeben = false;

    /**
     * Führt die Pflichtaktion beim ziehen einer Gefängniskarte aus.
     * @param schiedsrichter Der Schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(!this.istVergeben){
            schiedsrichter.getAktiverSpieler().setGefaengnisFreiKarte(this);
            this.istVergeben = true;
        } else {
            // Ist das überhaupt eine Gemeinschaftskarte?!
            //                          \/
            //schiedsrichter.naechsteGemeinschaftskarte();

            schiedsrichter.getProtokollant().printAs("Die Gefängniskarte wurde schon vergeben. Gebe nächste Karte aus...");
        }
    }

    public void abgeben(){
        this.istVergeben = false;
    }
}
