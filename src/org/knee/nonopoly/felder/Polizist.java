package org.knee.nonopoly.felder;

import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 24.09.2016.
 */
public class Polizist extends Feld {
    public Polizist(int index, String name) {
        super(index, name);
        this.typ = FeldTypen.POLIZIST;
    }

    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        schiedsrichter.getAktiverSpieler().geheInsGefaengnis();
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
