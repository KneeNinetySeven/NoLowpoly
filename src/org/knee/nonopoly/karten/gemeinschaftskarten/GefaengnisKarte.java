package org.knee.nonopoly.karten.gemeinschaftskarten;

import org.knee.nonopoly.karten.Karte;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class GefaengnisKarte implements Karte {

    private boolean istVergeben = false;

    @Override
    public void fuehreKartenAktionAus(Schiedsrichter schiedsrichter) {
        if(!this.istVergeben){
            schiedsrichter.getAktiverSpieler().setGefaengnisFreiKarte(this);
            this.istVergeben = true;
        } else {
            //schiedsrichter.naechsteGemeinschaftskarte();
            schiedsrichter.getProtokollant().printAs("Die Gefängniskarte wurde schon vergeben. Gebe nächste Karte aus...");
        }
    }

    public void abgeben(){
        this.istVergeben = false;
    }
}
