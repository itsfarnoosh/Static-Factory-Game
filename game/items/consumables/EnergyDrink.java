package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

public class EnergyDrink extends Item implements Consumable {
    private final int healingPoints;

    /**
     * Constructor.
     *
     * @param healingPoints the amount of healingPoints the consumable can restore when consume.
     */
    public EnergyDrink(int healingPoints){
        super("Energy Drink", '*', true);
        this.healingPoints = healingPoints;
    }

    /**
     * Heals the holder of the drink (by consuming).
     *
     * @param actor the holder of the drink.
     * @return description of who consume the drink as well as how much hitPoints is restored.
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.heal(healingPoints);
        return actor + " is healed by " + healingPoints + " points! ";
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
