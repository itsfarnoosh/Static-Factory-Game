package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public abstract class Fruit extends Item implements Consumable {
    private int healingPoints;

    /**
     * Constructor.
     *
     * @param name of the fruit
     * @param displayChar for the fruit in the map
     * @param portable
     * @param healingPoints from the fruit
     */
    public Fruit(String name, char displayChar, boolean portable, int healingPoints) {
        super(name, displayChar, portable);
        this.healingPoints = healingPoints;
    }

    /**
     * Provide the healing points by the fruit
     *
     * @return healingPoints
     */
    public int getHealingPoints() {
        return healingPoints;
    }

    /**
     * Heals the actor that is consuming the fruit
     *
     * @param actor the consuming actor
     * @return description showing how much the actor is healed by
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.heal(healingPoints);
        return actor + " is healed by " + this.getHealingPoints() + " points! ";
    }

    /**
     * Retrieves the list of allowable actions for the owner of the fruit.
     *
     * @param owner the Actor that owns the fruit
     * @return a list of allowable actions for the owner of the fruit
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        return new ActionList(new ConsumeAction(this));
    }
}
