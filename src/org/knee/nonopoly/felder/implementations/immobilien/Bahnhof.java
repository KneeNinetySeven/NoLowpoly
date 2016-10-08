package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeld;
import org.knee.nonopoly.felder.implementations.FeldTypen;

import java.util.List;

/**
 * Created by Nils on 24.09.2016.
 */
public class Bahnhof extends ImmobilienFeld {

    private List<Integer> mietStaffel;



    public Bahnhof(int index, String name, int kaufpreis, List<Integer> mietStaffel) {
        super(index, name, kaufpreis);
        this.typ = FeldTypen.IMMOBILIENFELD;
        this.mietStaffel = mietStaffel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bahnhof{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append("kaufpreis=").append(this.getKaufpreis());
        sb.append('}');
        sb.append("mietStaffel=").append(mietStaffel);
        sb.append('}');
        return sb.toString();
    }
}
