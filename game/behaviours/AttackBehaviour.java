package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.actions.AttackAction;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Class for attack behaviour, allowing NPCs to perform AttackActions.
 */
public class AttackBehaviour implements Behaviour {

    private Random random = new Random();

    /**
     * Returns an AttackAction to attack another actor, if possible. If there is no one to attack,
     * returns null.
     *
     * @param actor The Actor that is attacking
     * @param map   The map that Actor is currently on
     * @return An AttackAction, or null if there is no one to attack.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Actor otherActor = exit.getDestination().getActor();
            if (Objects.nonNull(otherActor) && (
                    (actor.hasCapability(Status.ENEMY) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY))
                            || (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(
                            Status.ENEMY)))) {
                actions.add(new AttackAction(otherActor, ""));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        } else {
            return null;
        }
    }
}