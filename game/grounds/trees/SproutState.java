package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents the sprout state of an Inheritree.
 * Implements the TreeState interface and manages the transition to the sapling state.
 */
public class SproutState implements TreeState {
    private static final int GROWTH_PERIOD = 3;
    private int tickCount = 0;

    /**
     * Handles the behavior of the sprout Inheritree in each tick (turn).
     * Increments the tick count and transitions to the sapling state if the growth period is reached.
     *
     * @param context the Inheritree whose state is being managed.
     * @param location the location of the Inheritree.
     */
    @Override
    public void tick(Inheritree context, Location location) {
        tickCount++;
        context.isGrowing(tickCount, GROWTH_PERIOD, new SaplingState());

    }

    /**
     * Gets the display character representing the sprout Inheritree.
     *
     * @return the character ','.
     */
    @Override
    public char getDisplayChar() {
        return ',';
    }
}
