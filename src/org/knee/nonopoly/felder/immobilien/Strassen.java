package org.knee.nonopoly.felder.immobilien;

import java.util.List;

/**
 * Created by Nils on 24.09.2016.
 */
public class Strassen extends ImmobilienFeld {

    private List<Integer> mietStaffel;
    int hauspreis;

    public Strassen(int index, String name, int kaufpreis, List<Integer> mietStaffel, int hauspreis) {
        super(index, name, kaufpreis);
        this.immobilienTyp = ImmobilienTypen.STRASSE;
        this.mietStaffel = mietStaffel;
        this.hauspreis = hauspreis;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Strassen{");
        sb.append("index=").append(this.getIndex());
        sb.append(", name=").append(this.getName());
        sb.append(", kaufpreis=").append(this.getKaufpreis());
        sb.append(", mietStaffel=").append(this.mietStaffel);
        sb.append(", hauspreis=").append(this.hauspreis);
        sb.append('}');
        return sb.toString();
    }
}
