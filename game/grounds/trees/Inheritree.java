package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.consumables.Fruit;
import game.items.consumables.SmallFruit;

import java.util.Random;

/**
 * Represents a tree in the game that can transition through different states.
 * Extends the Ground class and manages its state using the TreeState interface.
 */
public class Inheritree extends Ground {
    private TreeState state;

    /**
     * Constructor.
     * Initializes the Inheritree with an initial state.
     *
     * @param initialState the initial state of the Inheritree.
     */
    public Inheritree(TreeState initialState) {
        super(initialState.getDisplayChar());
        this.state = initialState;
    }

    /**
     * Sets the state of the Inheritree.
     *
     * @param state the new state of the Inheritree.
     */
    public void setState(TreeState state) {
        this.state = state;
    }


    /**
     * Drops a fruit in a random adjacent location.
     *
     * @param location the current location of the Inheritree.
     * @param fruit the fruit to be dropped.
     * @param chance the chance of dropping a fruit.
     */
    public void dropFruit(Location location, Fruit fruit, double chance) {
        if (Math.random() <= chance) {
            Random rand = new Random();
            Location destination = location.getExits().get(rand.nextInt(location.getExits().size())).getDestination();
            destination.addItem(fruit);
        }
    }

    /**
     * Checks if the Inheritree is growing in the same state.
     *
     * @param tickCount the current tick count of the Inheritree in the current state.
     * @param nextState the next state of the Inheritree.
     * @param period the amount of ticks required to grow into the next state
     *
     * @return true if the Inheritree is still growing in the same state, else false
     */
    public boolean isGrowing(int tickCount, int period, TreeState nextState){
        if (tickCount >= period) {
            this.setState(nextState);
            return false;
        }
        return true;
    }


    /**
     * Handles the ticking behavior of the Inheritree.
     * Delegates the tick handling to the current state.
     *
     * @param location the location of the Inheritree.
     */
    @Override
    public void tick(Location location) {
        state.tick(this, location);
    }

    /**
     * Gets the display character representing the current state of the Inheritree.
     *
     * @return the character representing the current state.
     */
    @Override
    public char getDisplayChar() {
        return state.getDisplayChar();
    }


}
