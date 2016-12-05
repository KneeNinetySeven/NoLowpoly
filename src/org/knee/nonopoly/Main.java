package org.knee.nonopoly;

import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.ui.MainWindow;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nils on 09.09.2016.
 */
public class Main {

    Schiedsrichter schiedsrichter;

    public Main() {
        schiedsrichter = new Schiedsrichter();
    }

    public static void  main(String[] args){
        try {
            new Main().runGame();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lässt den Schiedsrichter (noch) nur die richtigen Dinge tun
     */
    public void runGame() throws InstantiationException, IllegalAccessException {
        MainWindow mainWindow = new MainWindow(schiedsrichter);
//        schiedsrichter.registriereSpieler("Spieler1", Interactive.class);
//        schiedsrichter.registriereSpieler("Spieler2", AllesKaeufer.class);
//        schiedsrichter.registriereSpieler("Spieler3", AllesKaeufer.class);
//        schiedsrichter.registriereSpieler("Spieler4", AllesKaeufer.class);
//        schiedsrichter.registriereSpieler("Spieler5", AllesKaeufer.class);
        mainWindow.refresh();
        Thread refresher = new Thread(new Refresher(mainWindow));
        refresher.start();
//        schiedsrichter.spielStarten();
//        schiedsrichter.spieleSpielZuEnde();

    }

    private class Refresher implements Runnable {
        private MainWindow mainWindow;

        Refresher(MainWindow m){
            this.mainWindow = m;
        }

        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
                } catch (InterruptedException e){
                    System.err.println("Refresh timer interrupted!");
                }
                if(schiedsrichter.updateAvailable) {
                    this.mainWindow.refresh();
                }
            }
        }
    }
}
