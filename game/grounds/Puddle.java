package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.items.consumables.Consumable;

import static edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes.HEALTH;

public class Puddle extends Ground implements Consumable {

    /**
     * Constructor.
     * Initializes puddle of water.
     *
     */
    public Puddle() {
        super('~');
    }

    /**
     * Retrieves the list of allowable actions for the actor to drink the puddle.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the puddle from the Actor
     * @return drinkAction choice for the actor to drink the water from the puddle
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        if (location.containsAnActor() && (location.getActor()).equals(actor)) {
            return new ActionList(new ConsumeAction(this));
        }
        return new ActionList();
    }

    /**
     * Consumes the puddle, affecting the specified actor.
     * Increases the actor's maximum health by 1 point.
     *
     * @param actor the actor who consumes the puddle.
     * @return a string describing the result of the consumption.
     */
    @Override
    public String consume(Actor actor) {
        actor.modifyAttributeMaximum(HEALTH, ActorAttributeOperations.INCREASE, 1);
        return actor + "'s Max Health has been increased by 1 point!";
    }

    /**
     * Provides a string representation of this object.
     *
     * @return a simple name of the class "Puddle"
     */
    @Override
    public String toString() {
        return "Puddle";
    }
}
