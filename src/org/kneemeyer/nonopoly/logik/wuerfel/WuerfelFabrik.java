package org.kneemeyer.nonopoly.logik.wuerfel;

import java.util.Random;

/**
 * Created by Nils on 24.09.2016.
 */
public class WuerfelFabrik {

    private int wurf1;
    private int wurf2;
    private Random generator;

    public WuerfelFabrik(){
        this.wurf1 = 0;
        this.wurf2 = 0;
        this.generator = new Random();
        this.generator.setSeed((long) 1 );
    }

    public WurfFabrik wuerfeln(){
        this.wurf1 = this.generator.nextInt(6);
        this.wurf2 = this.generator.nextInt(6);
        return new WurfFabrik(this.wurf1, this.wurf2);
    }

}
