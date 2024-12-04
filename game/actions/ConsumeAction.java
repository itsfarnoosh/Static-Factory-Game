package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumables.Consumable;

public class ConsumeAction extends Action {
    /** The consumable item. */
    private final Consumable consumable;
    /**
     * Constructor.
     *
     * @param consumable the Fruit to be consumed
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the ConsumeAction, removing the Fruit from the Actor's inventory and healing the Actor.
     *
     * @param actor the Actor performing the action
     * @param map   the GameMap where the action is to be executed
     * @return a description of the outcome of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consume(actor);
    }
    /**
     * Provides a description of the ConsumeAction for display in menu.
     *
     * @param actor the Actor performing the action
     * @return a description of the ConsumeAction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
