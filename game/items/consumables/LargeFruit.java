package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Status;
import game.items.sellables.SellableItem;


/**
 * A class representing a Large Fruit item in the game.
 */
public class LargeFruit extends Fruit implements SellableItem {

    private int price;

    /**
     * Constructor.
     */
    public LargeFruit() {
        super("Large Fruit", 'O', true, 2);
        this.price = 30;
    }

    /**
     * Non-default Constructor.
     *
     * @param price of the item.
     */
    public LargeFruit(int price) {
        super("Large Fruit", 'O', true, 2);
        this.price = price;
    }

    /**
     * Provide the item's price
     *
     * @return price of the item.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Provide the item's price
     *
     * @return price of the item.
     */
    public int getSellingPrice() {
        return this.price;
    }

    /**
     * Responsible for the selling process
     * Add the item's price to the seller's balance
     * Remove the item from their inventory
     *
     * @param actor the seller.
     * @param map current map.
     */
    public void sellItem(Actor actor, GameMap map) {
        actor.addBalance(this.getPrice());
        actor.removeItemFromInventory(this);
    }

    /**
     * Responsible for producing SellingAction of the item
     * Checks if the other actor has TRADER status in their capability.
     * If they do, SellingAction is produced where the other actor becomes the buyer of the item
     * If not, no action is made
     *
     * @param otherActor the other actor/the buyer
     * @param location the location of the other actor/the buyer
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(location);
        if (otherActor.hasCapability(Status.TRADER))
            actions.add(new SellAction(otherActor, this));
        return actions;
    }
}
