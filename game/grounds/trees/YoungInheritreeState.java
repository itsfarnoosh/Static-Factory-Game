package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Represents the young state of an Inheritree.
 * Implements the TreeState interface and manages the transition to the mature state.
 */
public class YoungInheritreeState implements TreeState {
    private static final int GROWTH_PERIOD = 5;
    private int tickCount = 0;

    /**
     * Handles the behavior of the young Inheritree in each tick (turn).
     * Increments the tick count and transitions to the mature state if the growth period is reached.
     *
     * @param context the Inheritree whose state is being changed.
     * @param location the location of the Inheritree.
     */
    @Override
    public void tick(Inheritree context, Location location) {
        tickCount++;
        context.isGrowing(tickCount,GROWTH_PERIOD,new MatureInheritreeState());
    }

    /**
     * Gets the display character representing the young Inheritree.
     *
     * @return the character 'y'.
     */
    @Override
    public char getDisplayChar() {
        return 'y';
    }
}
