package game.items.sellables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.SellAction;
import game.enums.Status;
import game.actions.AttackAction;


/**
 * Class representing the Metal Pipe Scrap Item, which can be used as a Weapon.
 */
public class MetalPipe extends SellableScrap implements Weapon {

    /**
     * Constructor for the Metal Pipe.
     */
    public MetalPipe() {
        super("Metal Pipe", '!', true, 35);
    }

    /***
     * Non-default Constructor.
     *
     * @param price of the item
     */
    public MetalPipe(int price) {
        super("Metal Pipe", '!', true, price);
    }

    /**
     * Generates the possible actions this Metal Pipe can do to another Actor, namely whether it can
     * be used to attack the other Actor.
     *
     * @param otherActor The other actor
     * @param location   The location of the other actor
     * @return An ActionList consisting of an AttackAction against the other actor if possible,
     * otherwise an empty ActionList
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        String direction = null;
        ActionList actionList = new ActionList();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().getItemInventory()
                    .contains(this)) {
                if ((destination.getActor().hasCapability(Status.ENEMY) && otherActor.hasCapability(
                        Status.HOSTILE_TO_ENEMY)) || (
                        destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)
                                && otherActor.hasCapability(Status.ENEMY))) {
                    for (Exit exitBack : destination.getExits()) {
                        if (exitBack.getDestination().containsAnActor()) {
                            if (exitBack.getDestination().getActor() == otherActor) {
                                direction = exitBack.getName();
                                actionList.add(new AttackAction(otherActor, direction, this));
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }

        if (otherActor.hasCapability(Status.TRADER))
            actionList.add(new SellAction(otherActor, this));

        return actionList;
    }

    /**
     * The amount of damage the Metal Pipe will inflict.
     *
     * @return The damage, in hit points
     */
    @Override
    public int damage() {
        return 1;
    }

    /**
     * A verb to use when displaying the results of attacking with the Metal Pipe.
     *
     * @return The verb "bashes" as a String
     */
    @Override
    public String verb() {
        return "hits";
    }

    /**
     * The chance in percentage the Metal Pipe has of landing a hit on the target.
     *
     * @return The integer 20, the percentage chance the Metal Pipe has of hitting the target.
     */
    @Override
    public int chanceToHit() {
        return 20;
    }
}