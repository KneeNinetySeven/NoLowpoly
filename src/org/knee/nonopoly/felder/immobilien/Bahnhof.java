package org.knee.nonopoly.felder.immobilien;

import java.util.List;

/**
 * @author Nils
 */
public class Bahnhof extends ImmobilienFeld {

    private List<Integer> mietStaffel;

    /**
     * Konstruktor
     *
     * @param index
     * @param name
     * @param kaufpreis
     * @param mietStaffel
     */
    public Bahnhof(int index, String name, int kaufpreis, List<Integer> mietStaffel) {
        super(index, name, kaufpreis);
        this.immobilienTyp = ImmobilienTypen.BAHNHOF;
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
