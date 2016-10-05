package org.knee.nonopoly.felder.implementations;

import org.knee.nonopoly.felder.abstracts.Feld;

/**
 * Created by Nils on 24.09.2016.
 */
public class Los extends Feld {

    private int treffer;
    private int ueberschreitung;

    public Los(int index, String name, int treffer, int ueberschreitung) {
        super(index, name);
        this.setTreffer(treffer);
        this.setUeberschreitung(ueberschreitung);
    }

    public int getTreffer() {
        return treffer;
    }

    public void setTreffer(int treffer) {
        this.treffer = treffer;
    }

    public int getUeberschreitung() {
        return ueberschreitung;
    }

    public void setUeberschreitung(int ueberschreitung) {
        this.ueberschreitung = ueberschreitung;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Los{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append("treffer=").append(treffer);
        sb.append(", ueberschreitung=").append(ueberschreitung);
        sb.append('}');
        return sb.toString();
    }
}
