package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;
/**
 * An Action to teleport an Actor to a random location within the same map.
 */
public class TeleportAction extends Action {
    private Item teleport;
    /**
     * Constructor for the TeleportAction.
     *
     * @param teleport The item that allows the Actor to teleport.
     */
    public TeleportAction(Item teleport) {
        this.teleport = teleport;
    }

    /**
     * Executes the teleport action, moving the Actor to a random location within the same map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description of the action result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Random rand = new Random();
        int x, y;
        Location newLocation;

        // Ensure the teleportation happens within the same map
        do {
            x = rand.nextInt(map.getXRange().max() - map.getXRange().min() + 1) + map.getXRange().min();
            y = rand.nextInt(map.getYRange().max() - map.getYRange().min() + 1) + map.getYRange().min();
            newLocation = map.at(x, y);
        } while (!newLocation.canActorEnter(actor));

        map.moveActor(actor, newLocation);
        return actor + " uses " + teleport + " to teleport to (" + x + ", " + y + ").";
    }
    /**
     * Returns a description of the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport with THESEUS";
    }
}
