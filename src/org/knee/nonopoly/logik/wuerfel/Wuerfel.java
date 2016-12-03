package org.knee.nonopoly.logik.wuerfel;

import java.util.Random;

/**
 * Created by Nils on 24.09.2016.
 * Ist in der Lage Zufallszahlen zwischen 1 und 6 zurückzugeben
 */
public class Wuerfel {

    private int wurf1;
    private int wurf2;
    private Random generator;

    public Wuerfel(){
        this.wurf1 = 0;
        this.wurf2 = 0;
        this.generator = new Random();
//        this.generator.setSeed((long) 1 );
    }


    /**
     * Ergebnisse der ersten 40 Würfe (bei seed = 1):
     *  <i>Folgt noch...</i>
     * @return Wurf
     */
    public Wurf wuerfeln(){
        this.wurf1 = this.generator.nextInt(6) + 1;
        this.wurf2 = this.generator.nextInt(6) + 1;
        return new Wurf(this.wurf1, this.wurf2);
    }

}
