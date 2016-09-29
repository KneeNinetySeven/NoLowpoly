package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeld;

/**
 * Created by Nils on 24.09.2016.
 */
public class Strassen extends ImmobilienFeld {

    int[] mietStaffel;

    public Strassen(String name, int kaufpreis, int[] mietStaffel) {
        super(name, kaufpreis);
        this.mietStaffel = mietStaffel;
    }

    @Override
    public boolean istStrasse(){
         return true;
     }

}
