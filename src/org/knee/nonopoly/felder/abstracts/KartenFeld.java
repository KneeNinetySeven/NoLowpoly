package org.knee.nonopoly.felder.abstracts;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class KartenFeld {
    private int index;
    private String name;

    public KartenFeld(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
