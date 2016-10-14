package org.knee.nonopoly.felder.immobilien;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.logik.Schiedsrichter;

import java.util.List;

/**
 * Created by Nils on 24.09.2016.
 */
public class Werk extends ImmobilienFeld {

    private List<Integer> faktoren;

    public Werk(int index, String name, int kaufpreis, List<Integer> faktoren) {
        super(index, name, kaufpreis);
        this.immobilienTyp = ImmobilienTypen.WERK;
        this.faktoren = faktoren;
    }

    @Override
    public void fuehrePflichtAktionAus(Schiedsrichter schiedsrichter) {
        if(besitzer == schiedsrichter.getBank()){
            if(schiedsrichter
                    .getAktiverSpieler()
                    .getStrategie()
                    .erlaubtFeldKauf(schiedsrichter.getAktiverSpieler())){
                anSpielerVerkaufen(schiedsrichter.getAktiverSpieler());
            }
        }
    }

    private void anSpielerVerkaufen(Spieler spieler){
        spieler.ueberweiseAn(getKaufpreis(), besitzer);
        besitzer = spieler;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Werk{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append("kaufpreis=").append(this.getKaufpreis());
        sb.append('}');
        sb.append("faktoren=").append(faktoren);
        sb.append('}');
        return sb.toString();
    }
}
