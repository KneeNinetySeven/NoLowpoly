package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeld;

import java.util.Arrays;

/**
 * Created by Nils on 24.09.2016.
 */
public class Bahnhof extends ImmobilienFeld {

    private int[] mietStaffel;



    public Bahnhof(String name, int kaufpreis, int[] mietStaffel) {
        super(name, kaufpreis);
        this.mietStaffel = mietStaffel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bahnhof{");
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append("kaufpreis=").append(this.getKaufpreis());
        sb.append('}');
        sb.append("mietStaffel=").append(Arrays.toString(mietStaffel));
        sb.append('}');
        return sb.toString();
    }
}
