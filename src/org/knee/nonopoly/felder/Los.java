package org.knee.nonopoly.felder;

import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;

import javax.swing.*;

/**
 * Created by Nils on 24.09.2016.
 */
public class Los extends Feld {

    private int treffer;
    private int ueberschreitung;

    /**
     * Konstruktor
     *
     * @param index
     * @param name
     * @param treffer
     * @param ueberschreitung
     */
    public Los(int index, String name, int treffer, int ueberschreitung) {
        super(index, name);
        this.typ = FeldTypen.LOS;
        this.setTreffer(treffer);
        this.setUeberschreitung(ueberschreitung);
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        Protokollant.printAs(this, schiedsrichter.getAktiverSpieler().getName() + " trifft das Los-Feld!");
        JOptionPane.showMessageDialog(null, schiedsrichter.getAktiverSpieler().getName() + " trifft das Los-Feld und bekommt " + treffer + " Mäuse bar auf die Kralle.");
        schiedsrichter.getBank().ueberweiseAn(getTreffer(), schiedsrichter.getAktiverSpieler());
    }

    public int getTreffer() {
        return treffer;
    }

    public void setTreffer(int treffer) {
        this.treffer = treffer;
    }

    public int getUeberschreitung() {
        return ueberschreitung;
    }

    public void setUeberschreitung(int ueberschreitung) {
        this.ueberschreitung = ueberschreitung;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Los{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name=").append(this.getName());
        sb.append("treffer=").append(treffer);
        sb.append(", ueberschreitung=").append(ueberschreitung);
        sb.append('}');
        return sb.toString();
    }
}
