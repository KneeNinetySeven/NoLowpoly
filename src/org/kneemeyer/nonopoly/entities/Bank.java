package org.kneemeyer.nonopoly.entities;

/**
 * Created by Nils on 11.09.2016.
 */
public class Bank extends Entity {
    public Bank() {
        super();
        setGuthaben(200000);
    }

    @Override
    public String toString(){
        return "Bank[" + getGuthaben()+"]";
    }
}
