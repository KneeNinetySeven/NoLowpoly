package org.knee.nonopoly.ui.felder;

import org.knee.nonopoly.felder.Feld;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Nils on 04.12.2016.
 * Package: org.knee.nonopoly.ui.felder
 */
public class VerticalFeld extends FeldPanel {
    public VerticalFeld(Feld feld, int height, int width) {
        super(feld, height, width);
        this.remove(title);
        this.add(new JPanel() {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(title.getWidth(), title.getHeight());
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.rotate(90 / 4, title.getWidth() / 2, title.getHeight() / 2);
                g2.drawString(title.getText(), 0, 0);
            }
        });
    }
}
