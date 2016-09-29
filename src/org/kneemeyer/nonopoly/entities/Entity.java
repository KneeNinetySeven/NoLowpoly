package org.kneemeyer.nonopoly.entities;

/**
 * Created by Nils on 09.09.2016.
 */
public abstract class Entity {

    private int guthaben;

    public int getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(int guthaben) {
        this.guthaben = guthaben;
    }

    protected Entity() {
        this.guthaben = 0;
    }

    public void ueberweiseAn(int geldMenge, Entity ziel){
        this.belasteMit(geldMenge);
        ziel.gutschreibenAn(geldMenge);
        System.out.println(this.toString() + "\t===" + geldMenge + "==> \t" + ziel);
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
