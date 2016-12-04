package org.knee.nonopoly.ui;

import org.knee.nonopoly.entities.spielerStrategien.Interactive;
import org.knee.nonopoly.exceptions.NameSchonVergebenException;
import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nils on 04.12.2016.
 * Package: org.knee.nonopoly.ui
 */
public class MainWindow extends JFrame {

    private Schiedsrichter schiedsrichter;
    private Toolkit toolkit;
    private int winPosX, winPosY, winHeight, winWidth;

    // <editor-fold desc="Menu-Komponenten">
    // Komponenten
    private JMenuBar menuBar;

    private JMenu spielMenu;
    private JMenu spielerMenu;
    private JMenu infoMenu;

    private JMenuItem spielMenu_spielStarten;
    private JMenuItem spielMenu_spielNeuStarten;
    private JMenuItem spielerMenu_spielerHinzu;
    private JMenuItem spielerMenu_spielerLoeschen;
    private JMenuItem info_about;
    //</editor-fold>

    //<editor-fold desc="Layout-Komponenten">
    private JPanel spielfeld;
    private JPanel spielerAnzeige;
    private int spielerAnzeigeWidth;
    //</editor-fold>

    public MainWindow(Schiedsrichter s) {
        super("Nonopoly");
        this.schiedsrichter = s;
        this.toolkit = Toolkit.getDefaultToolkit();
        this.winWidth = 1200;
        this.winHeight = 900;

        initWindow();
        initMenu();
        initLayouts();
        initListeners();
        pack();
        setVisible(true);
    }

    // <editor-fold desc="Initialisierungsmethoden">

    /**
     * Initialisiert das Hauptfenster
     */
    private void initWindow() {

        Dimension d = toolkit.getScreenSize();
        this.winPosX = (int) (d.getWidth() - winWidth) / 2;
        this.winPosY = (int) (d.getHeight() - winHeight) / 2;

        setBounds(this.winPosX, this.winPosY, winWidth, winHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Initialisiert die Menuleiste
     */
    private void initMenu() {
//        Protokollant.printAs(this, "Menu init!");
        this.menuBar = new JMenuBar();
        this.spielMenu = new JMenu("Spiel");
        this.spielerMenu = new JMenu("Spieler");
        this.infoMenu = new JMenu("Info");

        // SpielerMenu
        this.spielerMenu_spielerHinzu = new JMenuItem("Spieler hinzufügen");
        this.spielerMenu_spielerLoeschen = new JMenuItem("Spieler löschen");

        // SpielMenu
        this.spielMenu_spielStarten = new JMenuItem("Spiel starten");
        this.spielMenu_spielNeuStarten = new JMenuItem("Spiel neustarten");

        // Info
        this.info_about = new JMenuItem("About");

        // SpielerMenu bestücken
        this.spielerMenu.add(this.spielerMenu_spielerHinzu);
        this.spielerMenu.add(this.spielerMenu_spielerLoeschen);

        // SpielMenu bestücken
        this.spielMenu.add(this.spielMenu_spielStarten);
        this.spielMenu.add(this.spielMenu_spielNeuStarten);

        // Info bestücken
        this.infoMenu.add(this.info_about);

        // Menubar bestücken
        this.menuBar.add(this.spielerMenu);
        this.menuBar.add(this.spielMenu);
        this.menuBar.add(this.infoMenu);

        this.setJMenuBar(this.menuBar);

    }

    /**
     * Initialisiert alle Listener
     */
    private void initListeners() {
        this.spielerMenu_spielerHinzu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String spielername;

                // Prüfung auf Spieleranzahl
                if (schiedsrichter.getTeilnehmer().size() < 6) {
                    spielername = JOptionPane.showInputDialog(null, "Bitte den Spielernamen eingeben");
                    Protokollant.printAs(this, "Eingegebener Name: " + spielername);
                    // Prüfung auf Dopplungen
                    if (schiedsrichter.getTeilnehmer().stream().noneMatch(spieler -> spieler.getName().equalsIgnoreCase(spielername))) {
                        if (spielername != null) {
                            try {
                                schiedsrichter.registriereSpieler(spielername, Interactive.class);
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InstantiationException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            System.err.println("Bitte einen gültigen Spielernamen eingeben!");
                            JOptionPane.showMessageDialog(null, "Bitte einen gültigen Spielernamen eingeben!");
                        }
                    } else {
                        new NameSchonVergebenException();
                        JOptionPane.showMessageDialog(null, "Der Name ist schon vergeben!");
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "Es sind schon sechs Spieler eingetragen!");
                }
            }
        });
        this.spielMenu_spielStarten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schiedsrichter.spielStarten();
                if (schiedsrichter.spielGestartet()) {
                    spielfeld.add(new Label("Spielfeld"));
                    spielerMenu_spielerHinzu.setEnabled(false);
                    spielerMenu_spielerLoeschen.setEnabled(false);
                    schiedsrichter.spieleSpielZuEnde();
                } else {
                    JOptionPane.showMessageDialog(null, "Es sind noch nicht genügend Spieler vorhanden!", "Spiel wurde nicht gestartet", 2);
                }
            }
        });
        this.spielMenu_spielNeuStarten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Soll das Spiel wirklich neu gestartet werden?", "Neu starten?", 0, 1) == 0) {
                    schiedsrichter = new Schiedsrichter();
                    spielerMenu_spielerHinzu.setEnabled(true);
                    spielerMenu_spielerLoeschen.setEnabled(true);
                    initLayouts();
                }
            }
        });
    }

    /**
     * Initialisiert die Layouts
     */
    private void initLayouts() {
//        Protokollant.printAs(this, "Layouts");
        this.getContentPane().setLayout(new BorderLayout());

        this.spielerAnzeigeWidth = 300;
        this.spielerAnzeige = new JPanel();
        this.spielerAnzeige.setPreferredSize(new Dimension(this.spielerAnzeigeWidth, this.winHeight));
        this.spielerAnzeige.setBackground(Color.lightGray);


        this.spielfeld = new JPanel();
        this.spielfeld.setLayout(new BorderLayout());
        this.spielfeld.setBackground(Color.darkGray);
        this.spielfeld.setPreferredSize(new Dimension((this.winWidth - this.spielerAnzeigeWidth), this.winHeight));

        this.getContentPane().add(this.spielfeld, BorderLayout.CENTER);
        this.getContentPane().add(this.spielerAnzeige, BorderLayout.EAST);
    }
    // </editor-fold>

}

