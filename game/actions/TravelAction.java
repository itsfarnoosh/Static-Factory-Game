package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
/**
 * An action to allow an Actor to travel to a different location.
 */
public class TravelAction extends Action {
    private Location destination;
    private String destinationName;
    /**
     * Constructor for the TravelAction.
     *
     * @param destination The destination location to which the actor will travel.
     * @param destinationName The name of the destination, used for display purposes.
     */
    public TravelAction(Location destination, String destinationName) {
        this.destination = destination;
        this.destinationName = destinationName;
    }
    /**
     * Executes the travel action, moving the actor to the specified destination.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is currently on.
     * @return A description of the action result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, destination);
        return actor + " travels to " + destinationName;
    }
    /**
     * Returns a description of the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Travel to " + destinationName;
    }
}
