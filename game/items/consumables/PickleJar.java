package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;
import game.items.sellables.SellableScrap;

import java.util.Random;

public class PickleJar extends SellableScrap implements Consumable{
    private final int HURT_POINTS = 1;
    private final int HEAL_POINTS = 1;
    private final double EXPIRY_CHANCE = 0.5;

    /**
     * Constructor
     *
     */
    public PickleJar() {
        super("Jar of Pickles", 'n', true, 25, 50);
    }

    /***
     * Non-default Constructor.
     *
     * @param price price of the item
     * @param chance chance for the item to trigger its special affects
     */
    public PickleJar(int price, int chance) {
        super("Jar of Pickles", 'n', true, price, chance);
    }

    /**
     * Responsible for deciding whether the pickles are expired.
     *
     * @return boolean of whether it is or not
     */
    private boolean isExpired() {
        Random random = new Random();
        return random.nextDouble() < EXPIRY_CHANCE;
    }

    /**
     * Responsible for deciding whether the player will gain or loss health from consuming this.
     * if expired, consumer loss a hit-point
     * else, consumer gain a hit-point
     *
     * @param actor the consumer
     */
    @Override
    public String consume(Actor actor){
        if (isExpired()) {
            actor.removeItemFromInventory(this);
            actor.hurt(HURT_POINTS);
            return actor + " consumes " + this + ", but it's expired and hurts them by 1 point!";
        } else {
            actor.removeItemFromInventory(this);
            actor.heal(HEAL_POINTS);
            return actor + " consumes " + this + " and is healed by 1 point!";
        }
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
     * If item's chance is met, change the price to 50.
     * if not, the price remains the same as the original (25)
     *
     * @return The calculated/appropriate price in for the item based on the item's chance.
     */
    @Override
    public int getSellingPrice() {
        int percentage = new Random().nextInt(100);

        if (percentage < super.getChance()){
            super.setPrice(50);
        }

        return super.getPrice();
    }
}
