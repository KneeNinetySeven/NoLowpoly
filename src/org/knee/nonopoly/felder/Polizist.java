package org.knee.nonopoly.felder;

import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;

import javax.swing.*;

/**
 * Created by Nils on 24.09.2016.
 */
public class Polizist extends Feld {
    public Polizist(int index, String name) {
        super(index, name);
        this.typ = FeldTypen.POLIZIST;
    }

    /**
     * Führt die Aktion des Feldes für den aktiven Spieler aus
     *
     * @param schiedsrichter
     */
    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().geheInsGefaengnis();
        Protokollant.printAs(this,schiedsrichter.getAktiverSpieler().getName() + " geht ins Gefaengnis");
        JOptionPane.showMessageDialog(null, "HALT! " + schiedsrichter.getAktiverSpieler().getName().toUpperCase() + " wird ins Gefängnis abgeführt!");

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Polizist{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append("name=").append(this.getName());
        sb.append('}');
        return sb.toString();
    }
}
