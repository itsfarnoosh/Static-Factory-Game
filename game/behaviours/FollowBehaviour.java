package game.behaviours;

import edu.monash.fit2099.demo.mars.Application;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import game.actors.Player;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 * @see Application
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class FollowBehaviour implements Behaviour {

    private Actor target;

    @Override
    public Action getAction(Actor actor, GameMap map) {

        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                if (map.at(x, y).containsAnActor()) {
                    if (map.at(x, y).getActor() instanceof Player) {
                        this.target = map.at(x, y).getActor();
                    }
                }
            }
        }

        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;

    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
