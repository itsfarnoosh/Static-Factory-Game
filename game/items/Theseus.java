package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;

/**
 * A special item called THESEUS that allows an Actor to teleport within the same map.
 */
public class Theseus extends Item {

    /**
     * Constructor for the Theseus item.
     */
    public Theseus() {
        super("Theseus", '^', true);
    }

    /**
     * Returns a list of allowable actions for this item.
     * In this case, it provides a TeleportAction to the actor who is standing on it/at the same location.
     * It does not add a DropAction here to avoid duplication.
     *
     * @param location the location of the ground on which the item lies
     * @return A list of actions that can be performed with this item.
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();
        if (location.getItems().contains(this)) {
            actions.add(new TeleportAction(this));
        }
        return actions;
    }
}
