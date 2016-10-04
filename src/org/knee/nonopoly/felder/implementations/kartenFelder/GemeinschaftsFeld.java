package org.knee.nonopoly.felder.implementations.kartenFelder;

import org.knee.nonopoly.felder.abstracts.KartenFeld;

/**
 * Created by Nils on 24.09.2016.
 */
public class GemeinschaftsFeld extends KartenFeld {
    public GemeinschaftsFeld(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gemeinschaftsfeld{");
        sb.append("name='").append(this.getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
