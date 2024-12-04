package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.items.consumables.LargeFruit;

import java.util.Random;

/**
 * Represents the mature state of an Inheritree.
 * Implements the TreeState interface and manages the periodic dropping of large fruits.
 */
public class MatureInheritreeState implements TreeState {
    private static final double PLANT_CHANCE = 0.2;

    /**
     * Handles the behavior of the mature Inheritree in each tick (turn).
     * Occasionally drops a large fruit in a random adjacent location.
     *
     * @param context the Inheritree whose state is being managed.
     * @param location the location of the Inheritree.
     */
    @Override
    public void tick(Inheritree context, Location location) {
        context.dropFruit(location, new LargeFruit(), PLANT_CHANCE);

    }

    /**
     * Gets the display character representing the mature Inheritree.
     *
     * @return the character 'T'.
     */
    @Override
    public char getDisplayChar() {
        return 'T';
    }

}
