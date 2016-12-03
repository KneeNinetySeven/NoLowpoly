package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;

/**
 * @author Nils
 */
public class GefaengnisKarte implements Karte {

    private boolean istVergeben = false;

    /**
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(!this.istVergeben){
            schiedsrichter.getAktiverSpieler().setGefaengnisFreiKarte(this);
            this.istVergeben = true;
        } else {
            //schiedsrichter.naechsteGemeinschaftskarte();
            Protokollant.printAs(this,"Die Gefängniskarte wurde schon vergeben. Gebe nächste Karte aus...");
        }
    }

    public void abgeben(){
        this.istVergeben = false;
    }
}
