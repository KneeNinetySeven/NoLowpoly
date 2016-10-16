package org.knee.nonopoly.entities;

/**
 * Created by Nils on 09.09.2016.
 * Implementiert alle grundlegenden Funktionen, die von den Entitäten ausgeführt werden können sollen
 */
public abstract class Entity {

    private int guthaben;
    private String name;
    private boolean imSpiel;

    /**
     * Das Guthaben wird mit 0 Kapital initialisiert.
     * Der Name wird auf einen leeren String gesetzt.
     * Die Entität ist im Spiel und wird so intialisiert.
     */
    protected Entity() {
        this.setGuthaben(0);
        this.setName("");
        this.setImSpiel(true);
    }

    /**
     * Überweist eine bestimmte Menge an Geld von einem Entity zum nächsten
     * @param geldMenge Menge des zu transferierenden Geldes
     * @param ziel Ziel-Entity der Überweisung
     */
    public void ueberweiseAn(int geldMenge, Entity ziel){
        if(pruefeBonitaet(geldMenge)) {
            this.belasteMit(geldMenge);
            ziel.gutschreibenAn(geldMenge);
        } else {
            System.err.println(this.getName() + " hat doch garnicht genug Geld dafür!");
        }
    }

    /**
     * Prüft, ob genug Geld zur Verfügung steht.
     * @param geldMenge Menge des Geldes, das vorhanden sein soll
     * @return Gibt zurück, ob genug Geld zur Verfügung bereitsteht
     */
    private boolean pruefeBonitaet(int geldMenge){
        return this.getGuthaben() > geldMenge;
    }

    /**
     * Schreibt auf dem Konto die angegebene Summe gut
     * @param geldMenge
     */
    private void gutschreibenAn(int geldMenge){
        this.guthaben = this.guthaben + geldMenge;
    }

    /**
     * Zieht dem angegebenenen Konto die Summe an Geld ab
     * @param geldMenge
     */
    private void belasteMit(int geldMenge) {
        this.guthaben = this.guthaben - geldMenge;
    }

    @Override
    public String toString(){
        return("UNDEFINED ENTITY");
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

}
