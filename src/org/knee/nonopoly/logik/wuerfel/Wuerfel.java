package org.knee.nonopoly.logik.wuerfel;

import java.util.Random;

/**
 * Created by Nils on 24.09.2016.
 */
public class Wuerfel {

    private int wurf1;
    private int wurf2;
    private Random generator;

    /**
     *
     */
    public Wuerfel(){
        this.wurf1 = 0;
        this.wurf2 = 0;
        this.generator = new Random();
        this.generator.setSeed((long) 1 );
    }


    /**
     * Ergebnisse der ersten 40 Würfe (bei seed = 1):
     *  3 4 1 3 2 4 2 4 4 4 1 1 1 3 0 4 2 0 0 5 2 2 4 5 2 5 4 5 3 2 1 4 0 4 5 3 3 0 3 2
     * @return Wurf
     */
    public Wurf wuerfeln(){
        this.wurf1 = this.generator.nextInt(6);
        this.wurf2 = this.generator.nextInt(6);
        return new Wurf(this.wurf1, this.wurf2);
    }

}
