package org.knee.tests.feldTests;

import junit.framework.TestCase;
import junit.textui.TestRunner;
import org.knee.nonopoly.entities.spielerStrategien.AllesKaeufer;
import org.knee.nonopoly.felder.Feld;
import org.knee.nonopoly.logik.Schiedsrichter;

/**
 * Created by Nils on 04.11.2016.
 * Package: org.knee.tests.feldTests
 */
public class LosTest extends TestCase {

    public LosTest(String name) {
        super(name);
    }

    public void testPayment() throws Exception {
        Schiedsrichter testSchiri = new Schiedsrichter();
        testSchiri.registriereSpieler("Test", AllesKaeufer.class);
        Feld losFeld = testSchiri.getSpielbrett().get(0);
        losFeld.fuehrePflichtAktionAus(testSchiri);
        System.out.println(testSchiri.getBank().toString());
        assertTrue(testSchiri.getBank().getGuthaben() == 1994000);
    }

    public static void main(String[] args){
        TestRunner.run(LosTest.class);
    }
}
