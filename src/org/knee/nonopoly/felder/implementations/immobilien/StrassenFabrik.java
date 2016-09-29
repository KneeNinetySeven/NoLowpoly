package org.knee.nonopoly.felder.implementations.immobilien;

import org.knee.nonopoly.felder.abstracts.ImmobilienFeldFabrik;

/**
 * Created by Nils on 24.09.2016.
 */
public class StrassenFabrik extends ImmobilienFeldFabrik {

     @Override
    public boolean istStrasse(){
         return true;
     }

}
