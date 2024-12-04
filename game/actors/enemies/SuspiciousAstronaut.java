package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Monster;

public class SuspiciousAstronaut extends Enemy {
    /**
     * SpawnFactory constructor to create an SuspiciousAstronaut instance.
     * Returns a new SuspiciousAstronaut instance to be used in when instantiating a Crater object
     */
    public static SpawnFactory<SuspiciousAstronaut> FACTORY = new SpawnFactory<>() {
        @Override
        public SuspiciousAstronaut spawn() {
            return new SuspiciousAstronaut();
        }
    };
    /**
     * Constructor.
     *
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99, Monster.SUSPICIOUS_ASTRONAUT);
        this.spawnChance = 0.05;

    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;

        }
        return new DoNothingAction();
    }

    /**
     * Retrieves the intrinsic weapon of the SuspiciousAstronaut .
     *
     * @return the IntrinsicWeapon of the SuspiciousAstronaut
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(Integer.MAX_VALUE, "bonks", 100);
    }


}