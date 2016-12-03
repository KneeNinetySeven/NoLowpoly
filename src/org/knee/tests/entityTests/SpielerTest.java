package org.knee.tests.entityTests;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;

/**
 * Created by Nils on 04.11.2016.
 * Package: org.knee.tests.entityTests
 */
public class SpielerTest extends TestCase{

    Spieler testSpieler;

    public SpielerTest(String name){
        super(name);
        testSpieler = Spieler.spielerErzeugen("Test", new AllesKaeufer());
        testSpieler.setGuthaben(100);
    }

    // --------------------------------- INIT ---------------------------------

    public void testGuthaben() throws Exception {
        System.out.println("Testing: Guthaben");
        assertTrue(testSpieler.getGuthaben() == 100);
    }

    public void testPosition() throws Exception {
        System.out.println("Testing: Position");
        assertTrue(testSpieler.getPosition() == 0);
    }

    public void testStrategie() throws Exception {
        System.out.println("Testing: Strategie");
        assertTrue(testSpieler.getStrategie().getClass() == AllesKaeufer.class);
    }

    // --------------------------------- /INIT --------------------------------

    public void testPasche() throws Exception {
        System.out.println("Testing: Pasche");
        assertFalse(testSpieler.registrierePasch());
        assertFalse(testSpieler.registrierePasch());
        testSpieler.pascheZuruecksetzen();
        assertFalse(testSpieler.registrierePasch());
        assertFalse(testSpieler.registrierePasch());
        assertTrue(testSpieler.registrierePasch());
    }

    public void testGeheInsGefaengnis() throws Exception {
        System.out.println("Testing: Schicke Spieler ins Gefängnis");
        testSpieler.geheInsGefaengnis();
        assertTrue(testSpieler.getPosition() == 10);
        assertTrue(testSpieler.getImGefaengnis() == 3);
    }

    public void testKommeAusDemGefängnisFrei() throws Exception {
        System.out.println("Testing: Schicke Spieler aus dem Gefängnis");
        testSpieler.verlasseDasGefängnis();

        assertTrue(testSpieler.getImGefaengnis() == 0);
    }

    public static void main(String[] args){
        TestRunner.run(SpielerTest.class);
    }

}
