package game.items.sellables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.enums.Status;


public class SellableScrap extends Item implements SellableItem {

    private int price;
    private int chance;


    /**
     * Constructor (price only)
     *
     * @param name name of the item
     * @param displayChar display character of the item
     * @param portable pick-up-ability
     * @param price price of the item
     */
    public SellableScrap(String name, char displayChar, boolean portable, int price){
        super(name, displayChar, portable);
        this.setPrice(price);
    }

    /**
     * Constructor (price, and chance)
     *
     * @param name name of the item
     * @param displayChar display character of the item
     * @param portable pick-up-ability
     * @param price price of the item
     * @param chance chance for the item to trigger its special affects
     */
    public SellableScrap(String name, char displayChar, boolean portable, int price, int chance){
        super(name, displayChar, portable);
        this.setPrice(price);
        this.setChance(chance);
    }


    /**
     * Assign the price of the item
     *
     * @param price of the item
     */
    protected void setPrice(int price){
        this.price = price;
    }

    /**
     * Assign the chance of the item
     *
     * @param chance for the item's special affect to trigger
     */
    protected void setChance(int chance){
        this.chance = chance;
    }

    /**
     * Return the chance of the item
     *
     * @return chance for the item's special affect to trigger
     */
    protected int getChance(){
        return this.chance;
    }

    /**
     * Provide the price of this item
     *
     * @return price of this object in int
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Provide the appropriate price for this item
     *
     * @return price of this object in int
     */
    @Override
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
    @Override
    public void sellItem(Actor actor, GameMap map) {
        // add item's price to actor's balance
        actor.addBalance(this.getPrice());
        // remove the sold item from actor's inventory
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
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(location);
        // if the other is an object of TraderActor
        if (otherActor.hasCapability(Status.TRADER))
            // allow item to be sellable to them
            actions.add(new SellAction(otherActor, this));
        // return the produced action
        return actions;
    }
}
