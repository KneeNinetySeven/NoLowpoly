package org.knee.nonopoly.felder.abstracts;

import org.knee.nonopoly.entities.Spieler;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class Feld {

    private String name;

    public Feld(String name){
        this.name = name;
    }

    public Feld() {
        this.setName("Feld");
        System.out.println(this.getName() + ": creating...");
    }

    public void fuehrePflichtAktionAus(Spieler spieler){

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
