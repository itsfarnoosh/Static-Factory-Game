package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;
import game.items.sellables.SellableScrap;

import java.util.Random;

public class GoldPot extends SellableScrap implements Consumable {
    private final int INCREASE_AMOUNT = 10;

    /**
     * Constructor.
     *
     */
    public GoldPot() {
        super("Pot of Gold", '$', true, 500, 25);
    }

    /***
     * Non-default Constructor.
     *
     * @param price price of the item
     * @param chance chance for the item to trigger its special affects
     */
    public GoldPot(int price, int chance) {

        super("Pot of Gold", '$', true, price, chance);
    }

    /**
     * Add the amount of gold to the actor's balance / wallet
     *
     * @param actor the Actor.
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.addBalance(INCREASE_AMOUNT);
        return "Wallet amount is increased by " + INCREASE_AMOUNT + " points!";
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

    /**
     * Calculate whether the item's chance will be met.
     * If item's chance is met, change the price to 0.
     * if not, the price remains the same as the original (500)
     *
     * @return The calculated/appropriate price in for the item based on the item's chance.
     */
    @Override
    public int getSellingPrice() {
        // generate a number between 0 - 99.
        int percentage = new Random().nextInt(100);

        // if the generated number/percentage is less than the chance of the item.
        // e.g. chance is 50, then any number that is 49 or less consider a success.
        // This is because the generated number/percentage is between 0 - 99 not 0 - 100.
        // Thus, < instead of <= in the if condition.
        if (percentage < super.getChance()){
            // change the price of the item.
            super.setPrice(0);
        }
        // return the item's price.
        return super.getPrice();
    }
}
