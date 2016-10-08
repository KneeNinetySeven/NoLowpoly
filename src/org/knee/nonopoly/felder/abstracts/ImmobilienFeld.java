package org.knee.nonopoly.felder.abstracts;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class ImmobilienFeld extends Feld {

    private int kaufpreis;

    public ImmobilienFeld(int index, String name, int kaufpreis) {
        super(index, name);
        this.kaufpreis = kaufpreis;
    }

    @Override
    public boolean istImmobilie(){
        return true;
    }

    public boolean istStrasse(){
        return false;
    }

    public int getKaufpreis() {
        return kaufpreis;
    }

    public void setKaufpreis(int kaufpreis) {
        this.kaufpreis = kaufpreis;
    }
}
