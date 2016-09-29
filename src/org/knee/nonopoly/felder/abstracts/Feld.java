package org.knee.nonopoly.felder.abstracts;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class Feld {

    private String name;

    public Feld(){

    }

    public void fuehrePflichtAktionAus(){

    }

    public boolean istImmobilie(){
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
