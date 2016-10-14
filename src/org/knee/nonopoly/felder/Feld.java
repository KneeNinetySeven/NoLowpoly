package org.knee.nonopoly.felder;

import org.knee.nonopoly.logik.pflichtAktionArgs.PflichtArguments;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class Feld {

    private int index;
    private String name;
    public FeldTypen typ;

    public Feld(int index, String name){
        this.index = index;
        this.name = name;
        this.typ = FeldTypen.ABSTRACT;
    }

    public Feld() {
        this.setName("Feld");
    }

    public void fuehrePflichtAktionAus(PflichtArguments args){

    }

    public boolean istVomTyp(FeldTypen testType) {
        return this.typ == testType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex(){
        return this.index;
    }
}
