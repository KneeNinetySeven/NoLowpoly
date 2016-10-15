package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.Steuertopf;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class SteuerFeld extends Feld {

    private int steuer;
    private Steuertopf steuertopf;

    public SteuerFeld(int index, String name, Steuertopf steuertopf, int steuer) {
        super(index, name);
        this.typ = FeldTypen.STEUERFELD;
        this.steuer = steuer;
        this.steuertopf = steuertopf;
    }

    public int getSteuer() {
        return steuer;
    }

    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Spieler aktiverSpieler = schiedsrichter.getAktiverSpieler();
        schiedsrichter.getProtokollant().printAs(aktiverSpieler.getName() + " zahlt " + steuer + " Mücken an Steuern ");
        aktiverSpieler.ueberweiseAn(steuer, schiedsrichter.getSteuertopf());
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
