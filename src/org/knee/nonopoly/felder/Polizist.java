package org.knee.nonopoly.felder;

/**
 * Created by Nils on 24.09.2016.
 */
public class Polizist extends Feld {
    public Polizist(int index, String name) {
        super(index, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Polizist{");
        sb.append("index='").append(this.getIndex()).append('\'');
        sb.append('}');
        sb.append("name=").append(this.getName());
        sb.append('}');
        sb.append('}');
        return sb.toString();
    }
}
