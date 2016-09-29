package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeld;

/**
 * Created by Nils on 24.09.2016.
 */
public class Werk extends ImmobilienFeld {

    int[] faktoren;

    public Werk(String name, int kaufpreis, int[] faktoren) {
        super(name, kaufpreis);
        this.faktoren = faktoren;
    }
}
