package org.knee.nonopoly.ui;

import org.knee.nonopoly.entities.Spieler;
import org.knee.nonopoly.entities.spielerStrategien.Interactive;
import org.knee.nonopoly.exceptions.NameSchonVergebenException;
import org.knee.nonopoly.logik.Schiedsrichter;
import org.knee.nonopoly.logik.logging.Protokollant;
import org.knee.nonopoly.ui.felder.Eckfeld;
import org.knee.nonopoly.ui.felder.FeldPanel;
import org.knee.nonopoly.ui.felder.HorizontalFeld;
import org.knee.nonopoly.ui.felder.VerticalFeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Nils on 04.12.2016.
 * Package: org.knee.nonopoly.ui
 */
public class MainWindow extends JFrame {

    private Schiedsrichter schiedsrichter;
    private Toolkit toolkit;
    private int winPosX, winPosY, winHeight, winWidth;
    private int indicator;

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
        plotPlayers();
        setResizable(false);
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
                                refresh();
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
        this.info_about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Dear tester, \n ... Ah fuck that. This is NoNopoly - The negative Monopoly. Cuz' this is awesome. \n \n Have fun!" +
                        "\n Logic and what not by. \n > Adrian Stölken - adri94@github.com <" +
                        "\n > Nils Kneemeyer - KneeNinetySeven@github.com < \n" +
                        "C u in da game. ");
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
        this.spielerAnzeige.setLayout(new BoxLayout(this.spielerAnzeige, BoxLayout.Y_AXIS));
//        this.spielerAnzeige.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        this.spielfeld = new JPanel();
        this.spielfeld.setLayout(new BorderLayout());
//        this.spielfeld.setLayout(new FlowLayout());
        this.spielfeld.setBackground(Color.darkGray);
        this.spielfeld.setPreferredSize(new Dimension((this.winWidth - this.spielerAnzeigeWidth), this.winHeight));
//        this.spielfeld.setLayout(new BoxLayout(this.spielfeld, BoxLayout.X_AXIS));

        this.getContentPane().add(this.spielfeld, BorderLayout.CENTER);
        this.getContentPane().add(this.spielerAnzeige, BorderLayout.EAST);
    }
    // </editor-fold>

    /**
     *
     */
    private void plotPlayers() {
        Color[] colors = {Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED, Color.DARK_GRAY, Color.ORANGE};
        int index = 0;
        this.spielerAnzeige.removeAll();
        for (Spieler s : schiedsrichter.getTeilnehmer()) {
            JTextArea textArea = new JTextArea("Name: " + s.getName()
                    + "\nGuthaben: " + s.getGuthaben()
                    + "\nPosition: " + s.getPosition());
            if (s.getGefaengnisFreiKarte() != null) {
                textArea.append("\n Gefängniskarte: " + s.getGefaengnisFreiKarte().getClass().getName());
            }
            textArea.setEditable(false);
            textArea.setSize(new Dimension(spielerAnzeigeWidth - 10, 100));
            textArea.setBackground(colors[index]);
            this.spielerAnzeige.add(textArea, -1);
            index++;
        }
    }

    private void plotSpielfeld() {
        this.spielfeld.removeAll();

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JPanel platzhalter = new JPanel();
        platzhalter.add(new JLabel(String.valueOf( "Spielzeit in Sekunden: " + indicator)));
        platzhalter.setMinimumSize(new Dimension(this.spielfeld.getWidth() / 2, this.spielfeld.getHeight() / 2));

        int totalSize = schiedsrichter.getSpielbrett().size();
        ArrayList<FeldPanel> topFelder = new ArrayList<>();
        ArrayList<FeldPanel> rightFelder = new ArrayList<>();
        ArrayList<FeldPanel> bottomFelder = new ArrayList<>();
        ArrayList<FeldPanel> leftFelder = new ArrayList<>();
        int spielfeldIndex = 0;
        int tmpIndex;

        // FELDER GENERIEREN UND EINSORTIEREN
        topFelder.add(new Eckfeld(schiedsrichter.getSpielbrett().get(spielfeldIndex), (this.spielfeld.getWidth() / 4), (this.spielfeld.getHeight() / 4)));
        spielfeldIndex++;
        for (tmpIndex = 0; tmpIndex < ((totalSize / 4) - 1); tmpIndex++) {
            spielfeldIndex++;
            topFelder.add(new VerticalFeld(schiedsrichter.getSpielbrett().get(spielfeldIndex),
                    this.spielfeld.getHeight() / 4,
                    this.spielfeld.getWidth() / 2 / ((totalSize - 4) / 4)));
        }
        topFelder.add(new Eckfeld(schiedsrichter.getSpielbrett().get(spielfeldIndex), (this.spielfeld.getWidth() / 4), (this.spielfeld.getHeight() / 4)));
        spielfeldIndex++;

        for (tmpIndex = 0; tmpIndex < ((totalSize / 4) - 1); tmpIndex++) {
            rightFelder.add(new HorizontalFeld(schiedsrichter.getSpielbrett().get(spielfeldIndex), this.spielfeld.getHeight() / (totalSize / 4 - 1), this.spielfeld.getWidth() / 4));
            spielfeldIndex++;
        }

        bottomFelder.add(new Eckfeld(schiedsrichter.getSpielbrett().get(spielfeldIndex), (this.spielfeld.getWidth() / 4), (this.spielfeld.getHeight() / 4)));
        spielfeldIndex++;
        for (tmpIndex = 0; tmpIndex < ((totalSize / 4) - 1); tmpIndex++) {
            bottomFelder.add(new VerticalFeld(
                    schiedsrichter.getSpielbrett().get(spielfeldIndex),
                    this.spielfeld.getHeight() / 4,
                    this.spielfeld.getWidth() / 2 / ((totalSize - 4) / 4)));
            spielfeldIndex++;
        }
        bottomFelder.add(new Eckfeld(schiedsrichter.getSpielbrett().get(spielfeldIndex), (this.spielfeld.getWidth() / 4), (this.spielfeld.getHeight() / 4)));
        spielfeldIndex++;

        for (tmpIndex = 0; tmpIndex < ((totalSize / 4) - 1); tmpIndex++) {
            leftFelder.add(new HorizontalFeld(schiedsrichter.getSpielbrett().get(spielfeldIndex), this.spielfeld.getHeight() / (totalSize / 4 - 1), this.spielfeld.getWidth() / 4));
            spielfeldIndex++;
        }

        // FELDER DEM PANEL ZUORDNEN
        topFelder.stream().forEach(feldPanel -> top.add(feldPanel));
        rightFelder.stream().forEach(feldPanel -> right.add(feldPanel));
        Collections.reverse(bottomFelder);
        bottomFelder.stream().forEach(feldPanel -> bottom.add(feldPanel));
        Collections.reverse(rightFelder);
        leftFelder.stream().forEach(feldPanel -> left.add(feldPanel));

        // PANELE EINSETZEN
        this.spielfeld.add(platzhalter, BorderLayout.CENTER);
        this.spielfeld.add(top, BorderLayout.NORTH);
        this.spielfeld.add(right, BorderLayout.EAST);
        this.spielfeld.add(bottom, BorderLayout.SOUTH);
        this.spielfeld.add(left, BorderLayout.WEST);

    }

    public void refresh() {
        plotPlayers();
        plotSpielfeld();
        repaint();
        revalidate();
        indicator++;
    }

}

