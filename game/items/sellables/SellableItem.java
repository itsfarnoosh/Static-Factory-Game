package game.items.sellables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Represents items that can be sold within the game.
 * Provides methods to get the price of the item, the selling price, and to sell the item to an actor.
 */
public interface SellableItem {

    /**
     * Gets the price of the item.
     *
     * @return the price of the item.
     */
    int getPrice();

    /**
     * Gets the selling price of the item.
     *
     * @return the selling price of the item.
     */
    int getSellingPrice();

    /**
     * Sells the item to the specified actor.
     *
     * @param actor the actor to whom the item is being sold.
     * @param map current map.
     */
    void sellItem(Actor actor, GameMap map);
}
