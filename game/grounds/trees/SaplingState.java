package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.items.consumables.LargeFruit;
import game.items.consumables.SmallFruit;

import java.util.Random;

/**
 * Represents the sapling state of an Inheritree.
 * Implements the TreeState interface and manages the transition to the young state.
 * Also handles the periodic dropping of small fruits.
 */
public class SaplingState implements TreeState {
    private static final int GROWTH_PERIOD = 6;
    private int tickCount = 0;
    private static final double PLANT_CHANCE = 0.3;

    /**
     * Handles the behavior of the sapling Inheritree in each tick (turn).
     * Increments the tick count and transitions to the young state if the growth period is reached.
     * Occasionally drops a small fruit in a random adjacent location.
     *
     * @param context the Inheritree whose state is being managed.
     * @param location the location of the Inheritree.
     */
    @Override
    public void tick(Inheritree context, Location location) {
        tickCount++;
        if (context.isGrowing(tickCount,GROWTH_PERIOD, new YoungInheritreeState())) {
            context.dropFruit(location, new SmallFruit(), PLANT_CHANCE);
        }

    }

    /**
     * Gets the display character representing the sapling Inheritree.
     *
     * @return the character 't'.
     */
    @Override
    public char getDisplayChar() {
        return 't';
    }

}
