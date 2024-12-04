package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.Monster;
import game.enums.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;

public class HuntsmanSpider extends Enemy {
    /**
     * SpawnFactory constructor to create an HuntsmanSpider instance.
     * Returns a new HuntsmanSpider instance to be used in when instantiating a Crater object
     */
    public static SpawnFactory<HuntsmanSpider> FACTORY = new SpawnFactory<>() {
        @Override
        public HuntsmanSpider spawn() {
            return new HuntsmanSpider();
        }
    };

    /**
     * Constructor for HuntsmanSpider.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1, Monster.HUNTSMAN_SPIDER);
        this.behaviours.put(999, new WanderBehaviour());
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
     * Retrieves the intrinsic weapon of the Huntsman Spider.
     *
     * @return the IntrinsicWeapon of the Huntsman Spider
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(1, "kicks", 25);
    }

    /**
     * The huntsman spider can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

}