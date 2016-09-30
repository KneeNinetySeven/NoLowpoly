package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeld;

/**
 * Created by Nils on 24.09.2016.
 */
public class Strassen extends ImmobilienFeld {

    int[] mietStaffel;
    int hauspreis;

    public Strassen(String name, int kaufpreis, int[] mietStaffel, int hauspreis) {
        super(name, kaufpreis);
        this.mietStaffel = mietStaffel;
        this.hauspreis = hauspreis;
    }

    @Override
    public boolean istStrasse(){
         return true;
     }

}
