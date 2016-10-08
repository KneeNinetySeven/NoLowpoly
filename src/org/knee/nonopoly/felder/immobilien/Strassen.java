package org.knee.nonopoly.felder.immobilien;

/**
 * Created by Nils on 24.09.2016.
 */
public class Strassen extends ImmobilienFeld {

    int[] mietStaffel;
    int hauspreis;

    public Strassen(int index, String name, int kaufpreis, int[] mietStaffel, int hauspreis) {
        super(index, name, kaufpreis);
        this.mietStaffel = mietStaffel;
        this.hauspreis = hauspreis;
    }

}
