package org.knee.nonopoly.entities;

/**
 * Created by Nils on 09.09.2016.
 */
public abstract class Entity {

    private int guthaben;
    private String name;
    private boolean imSpiel;

    protected Entity() {
        this.setGuthaben(0);
        this.setName("");
    }

    public int getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(int guthaben) {
        this.guthaben = guthaben;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getImSpiel() {
        return imSpiel;
    }

    public void setImSpiel(boolean imSpiel) {
        this.imSpiel = imSpiel;
    }

    public void ueberweiseAn(int geldMenge, Entity ziel){
        this.belasteMit(geldMenge);
        ziel.gutschreibenAn(geldMenge);
    }

    private void gutschreibenAn(int geldMenge){
        this.guthaben = this.guthaben + geldMenge;
    }

    private void belasteMit(int geldMenge) {
        this.guthaben = this.guthaben - geldMenge;
    }

    @Override
    public String toString(){
        return("UNDEFINED ENTITY");
    }

}
