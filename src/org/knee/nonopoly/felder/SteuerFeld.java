package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;

import javax.swing.*;

/**
 * Created by Nils on 24.09.2016.
 */
public class SteuerFeld extends Feld {

    private int steuer;
    private Steuertopf steuertopf;

    /**
     * Konstruktor
     *
     * @param index
     * @param name
     * @param steuertopf
     * @param steuer
     */
    public SteuerFeld(int index, String name, Steuertopf steuertopf, int steuer) {
        super(index, name);
        this.typ = FeldTypen.STEUERFELD;
        this.steuer = steuer;
        this.steuertopf = steuertopf;
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     * Wird in den einzelnen Feldern überschrieben
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Spieler aktiverSpieler = schiedsrichter.getAktiverSpieler();
        aktiverSpieler.ueberweiseAn(steuer, schiedsrichter.getSteuertopf());
        Protokollant.printAs(this,aktiverSpieler.getName() + " zahlt " + steuer + " Mücken an Steuern ");
        JOptionPane.showMessageDialog(null, schiedsrichter.getAktiverSpieler().getName().toUpperCase()
                + " geht richtig steil und zahlt " + steuer + " Mücken an Steuern\n \n" + "Ab jetzt sind " + schiedsrichter.getSteuertopf().getGuthaben() + " Kröten im Pot");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SteuerFeld{");
        sb.append("index=").append(this.getIndex());
        sb.append(", name=").append(this.getName());
        sb.append(", steuer=").append(steuer);
        sb.append(", steuertopf=").append(steuertopf);
        sb.append('}');
        return sb.toString();
    }
}
