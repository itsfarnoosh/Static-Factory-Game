package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.PickUpAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A behaviour for Actors to steal a single scrap from their current location randomly.
 */
public class StealBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * Returns an action for the Actor to steal a single scrap from the current location randomly.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the GameMap containing the Actor
     * @return an Action for the Actor to perform, or null if no action is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        List<Item> itemsAtLocation = new ArrayList<>(currentLocation.getItems());

        if (itemsAtLocation.isEmpty()) {
            return null;  // No items to steal
        }

        // Randomly select one item to steal
        Item itemToSteal = itemsAtLocation.get(random.nextInt(itemsAtLocation.size()));

        // Return a PickUpAction for the item stolen for game mechanics (like logging or messages)
        return new PickUpAction(itemToSteal);
    }
}
