package org.knee.nonopoly.felder;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.Steuertopf;

/**
 * Created by Nils on 24.09.2016.
 */
public class FreiParken extends Feld {

    private Steuertopf steuertopf;

    //TODO: Steuertopf steuertopf im Konstruktor einlesen

    public FreiParken(int index, String name) {
        super(index, name);
        //this.steuertopf = steuertopf;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FreiParken{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append('}');
        return sb.toString();
    }

    public void fuehrePflichtAktionAus(Spieler spieler) {
        //this.steuertopf.ueberweiseAn(this.steuertopf.getGuthaben(), spieler);
    }
}
