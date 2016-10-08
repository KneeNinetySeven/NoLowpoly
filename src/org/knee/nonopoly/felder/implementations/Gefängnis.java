package org.knee.nonopoly.felder.implementations;

import org.knee.nonopoly.felder.abstracts.Feld;

/**
 * Created by Nils on 24.09.2016.
 * TODO: ä und ae angleichen (Gefaengnis)
 */
public class Gefängnis extends Feld {
    public Gefängnis(int index, String name) {
        super(index, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gefängnis{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append('}');
        return sb.toString();
    }
}
