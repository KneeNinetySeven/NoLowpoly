package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.logik.Schiedsrichter;

import javax.swing.*;

/**
 * Created by Nils on 24.09.2016.
 */
public class FreiParken extends Feld {

    public FreiParken(int index, String name) {
        super(index, name);
        this.typ = FeldTypen.STEUERFELD;
    }

    /**
     * <b> Pflichtaktion von FreiParken-Feldern </b>
     * Überweist den Inhalt des gesamten Steuertopfes an den Spieler
     *
     * @param schiedsrichter
     */

    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Steuertopf steuertopf = schiedsrichter.getSteuertopf();
        JOptionPane.showMessageDialog(null, "Bei " + schiedsrichter.getAktiverSpieler().getName().toUpperCase() + " klingelt die Kasse, " +
                "denn er hat das Frei-Parken-Feld erwischt! \n Er bekommt " + steuertopf.getGuthaben() );
        steuertopf.ueberweiseAn(steuertopf.getGuthaben(), schiedsrichter.getAktiverSpieler());

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FreiParken{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name=").append(this.getName());
        sb.append('}');
        return sb.toString();
    }
}
