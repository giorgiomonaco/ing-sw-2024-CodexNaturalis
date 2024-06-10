package it.polimi.ingsw.server.saveHandlig;

import it.polimi.ingsw.server.model.Player;

import java.io.Serializable;
import java.util.List;

/**
 * The class contains the information needed to save and load an existing game.
 */
public class SaveData implements Serializable {
    private List<Player> players;


}
