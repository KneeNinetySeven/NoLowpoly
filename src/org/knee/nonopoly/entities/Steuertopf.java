package org.knee.nonopoly.entities;

/**
 * Created by Nils on 02.10.2016.
 */
public class Steuertopf extends Entity{

    public Steuertopf() {
        super();
        this.setName("Steuertopf");
    }

    @Override
    public String toString(){
        return this.getName() + " mit: " + this.getGuthaben() + " Geld im Topf";
    }

}
