package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.enums.Status;

public class DragonSlayer extends WeaponItem {

    // Godot was ordered by the king to forge a sword that has the capability to defeat a dragon.
    // So he made this unwieldable weapon.
    // note that the (brackets) are reference to Berserk. RIP Miura Kentaro.

    /**
     * Constructor (Godot)
     *
     */
    public DragonSlayer(){
        super("Dragon Slayer", 'x', 50, "slays", 75);
    }

    /**
     * Allow the wielder to deploy an attack.
     *
     * @param otherActor the other actor (Apostle)
     * @param location the location of the other actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(location);
        if (otherActor.hasCapability(Status.ENEMY))
            actions.add(new AttackAction(otherActor, location.toString(), this));
        return actions;
    }
}

