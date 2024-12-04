package game.items.sellables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.Random;

import static edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes.HEALTH;

public class ToiletPaper extends SellableScrap {
    /**
     * Constructor
     *
     */
    public ToiletPaper(){
        super("Toilet Paper", 's', true, 1, 50);
    }

    /***
     * Non-default Constructor.
     *
     * @param price price of the item
     * @param chance chance for the item to trigger its special affects
     */
    public ToiletPaper(int price, int chance){
        super("Toilet Paper", 's', true, price, chance);
    }

    /**
     * sell the item to the seller/provided actor.
     * if the chance of this item is met, the seller will be punished/instantly killed
     *
     * @param actor the seller of the item.
     * @param map current map.
     */
    @Override
    public void sellItem(Actor actor, GameMap map) {
        int percentage = new Random().nextInt(100);
        if (percentage < super.getChance()){
            // deal max damage to intern
            actor.hurt(Integer.MAX_VALUE);
            // if the intern's health is 0 / dead.
            if (actor.getAttribute(HEALTH) == 0){
                // remove intern from the map and terminate the game.
                actor.unconscious(actor, map);
            }
        } else {
            actor.addBalance(super.getPrice());
            actor.removeItemFromInventory(this);
        }
    }
}
