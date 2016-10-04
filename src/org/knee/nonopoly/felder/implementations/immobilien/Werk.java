package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeld;

import java.util.Arrays;

/**
 * Created by Nils on 24.09.2016.
 */
public class Werk extends ImmobilienFeld {

    private int[] faktoren;

    public Werk(String name, int kaufpreis, int[] faktoren) {
        super(name, kaufpreis);
        this.faktoren = faktoren;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Werk{");
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append("kaufpreis=").append(this.getKaufpreis());
        sb.append('}');
        sb.append("faktoren=").append(Arrays.toString(faktoren));
        sb.append('}');
        return sb.toString();
    }
}
