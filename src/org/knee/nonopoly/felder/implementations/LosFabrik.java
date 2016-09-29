package org.knee.nonopoly.felder.implementations;

import org.knee.nonopoly.felder.abstracts.FeldFabrik;

/**
 * Created by Nils on 24.09.2016.
 */
public class LosFabrik extends FeldFabrik {

    private int treffer;
    private int überschreitung;

    public LosFabrik() {
        this.treffer = 6000;
        this.überschreitung = 4000;
    }

    public int getTreffer() {
        return treffer;
    }

    public void setTreffer(int treffer) {
        this.treffer = treffer;
    }

    public int getÜberschreitung() {
        return überschreitung;
    }

    public void setÜberschreitung(int überschreitung) {
        this.überschreitung = überschreitung;
    }
}
