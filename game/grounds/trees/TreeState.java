package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents the different states of a tree in the game.
 * Provides methods to handle the tree's behavior during each tick and to get the display character for the state.
 */
public interface TreeState {

    /**
     * Handles the behavior of the tree in each tick (turn).
     * This method is called periodically to update the state of the tree.
     *
     * @param context the Inheritree whose state is being managed.
     * @param location the location of the Inheritree.
     */
    void tick(Inheritree context, Location location);

    /**
     * Gets the display character representing the current state of the tree.
     *
     * @return the character representing the current state.
     */
    char getDisplayChar();
}
