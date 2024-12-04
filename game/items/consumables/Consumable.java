package game.items.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Represents items that can be consumed by an actor within the game.
 * Provides a method to consume the item, which affects the specified actor.
 */
public interface Consumable {

    /**
     * Consumes the item, which has an effect on the specified actor that consumes it.
     *
     * @param actor the actor who consumes the item.
     * @return a string describing the result of the consumption.
     */
    String consume(Actor actor);
}
