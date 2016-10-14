package org.knee.nonopoly.logik.pflichtAktionArgs;

import org.knee.nonopoly.entities.Bank;
import org.knee.nonopoly.entities.Spieler;

/**
 * Created by Nils on 14.10.2016.
 * Package: org.knee.nonopoly.logik.pflichtAktionArgs
 */
public class ImmobilienPflichtArguments extends PflichtArguments{

    public Bank bank;

    public ImmobilienPflichtArguments(Spieler spieler, Bank bank) {
        this.spieler = spieler;
        this.bank = bank;
    }
}
