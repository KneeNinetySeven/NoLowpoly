package org.knee.nonopoly.felder.kartenFelder;

import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.felder.FeldTypen;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class KartenFeld extends Feld {
    private int index;
    private String name;
    public KartenTypen kartenTyp;

    public KartenFeld(int index, String name) {
        this.index = index;
        this.name = name;
        this.typ = FeldTypen.KARTENFELD;
        this.kartenTyp = KartenTypen.ABSTRACT;
    }

    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter
                .getProtokollant()
                .printAs(schiedsrichter
                        .getAktiverSpieler()
                        .getName() + " zieht eine Karte auf dem " + this.getName());
    }

    public boolean istKartenTyp(KartenTypen testKartenTyp) {
        return this.kartenTyp == testKartenTyp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
