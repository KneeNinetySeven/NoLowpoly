package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class FreiParken extends Feld {

    public FreiParken(int index, String name) {
        super(index, name);
        this.typ = FeldTypen.STEUERFELD;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FreiParken{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name=").append(this.getName());
        sb.append('}');
        return sb.toString();
    }


    /**
     * <b> Pflichtaktion von FreiParken-Feldern </b>
     * Überweist den Inhalt des gesamten Steuertopfes an den Spieler
     * @param schiedsrichter
     */

    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Steuertopf steuertopf = schiedsrichter.getSteuertopf();
        steuertopf.ueberweiseAn(steuertopf.getGuthaben(), schiedsrichter.getAktiverSpieler());
    }
}
