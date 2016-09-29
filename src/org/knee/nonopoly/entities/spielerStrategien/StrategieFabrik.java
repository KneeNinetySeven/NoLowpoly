package org.knee.nonopoly.entities.spielerStrategien;

import org.knee.nonopoly.entities.SpielerFabrik;

/**
 * Created by Nils on 24.09.2016.
 */
public abstract class StrategieFabrik {

    private String name;

    public boolean erlaubtHausbau(SpielerFabrik spielerFabrik){
        return false;
    }

    public boolean erlaubtFeldKauf(SpielerFabrik spielerFabrik){ return false; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UNDEFINED STRATEGIE";
    }
}
