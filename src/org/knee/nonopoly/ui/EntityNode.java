package org.knee.nonopoly.ui;

import javafx.scene.layout.Pane;
import org.knee.nonopoly.entities.Entity;

/**
 * Created by Nils on 27.10.2016.
 * Package: org.knee.nonopoly.ui
 */
public interface EntityNode {

    Pane generate();

    Entity entity = null;

}
