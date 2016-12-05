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
        Thread refresher = new Thread(new Refresher(mainWindow));
        mainWindow.refresh();
        refresher.start();
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
