package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.StealBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Ability;
import game.enums.Monster;
import game.enums.Status;


import java.util.*;

/**
 * AlienBug class that extends Enemy and includes behaviors for following and collecting scraps.
 */
public class AlienBug extends Enemy {
    /**
     * Map of priorities to Behaviours
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private static final Random random = new Random();

    /**
     * SpawnFactory constructor to create an AlienBug instance.
     * Returns a new AlienBug instance to be used in when instantiating a Crater object
     */
    public static SpawnFactory<AlienBug> FACTORY = new SpawnFactory<>() {
        @Override
        public AlienBug spawn() {
            return new AlienBug();
        }
    };

    /**
     * Constructor.
     */
    public AlienBug() {
        super(generateUniqueName(), 'a', 2, Monster.ALIEN_BUG);
        this.behaviours.put(1, new StealBehaviour()); // Custom behavior for stealing scraps
        this.behaviours.put(2, new FollowBehaviour());// within the surroundings of the bug (i.e. one exit away), it will start following the Intern.
        this.behaviours.put(999, new WanderBehaviour()); // Custom behavior for wandering
        this.addCapability(Ability.ENTER_SPACESHIP);
        this.spawnChance = 0.1;

    }


    /**
     * Generates a unique name for each Alien Bug instance.
     *
     * @return A string representing the unique name "Feature-XXX" where XXX are random digits
     */
    private static String generateUniqueName() {
        int randomDigits = 100 + random.nextInt(900); // Generates a number from 100 to 999
        return "Feature-" + randomDigits;
    }
    //the Alien Bug cannot attack the Intern.

    /**
     * Called once per turn, allows the Alien Bug to perform actions like moving or stealing.
     *
     * @param actions    The list of possible actions this turn
     * @param lastAction The last action performed by this Actor
     * @param map        The game map containing this Actor
     * @param display    The I/O object to which messages may be written
     * @return the Action this Actor chooses to perform this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Process normal turn actions if still conscious
        for (Map.Entry<Integer, Behaviour> behaviourEntry : behaviours.entrySet()) {
            Action action = behaviourEntry.getValue().getAction(this, map);
            if (action != null) {
                return action;
            }

        }
        return new DoNothingAction();
    }


    /**
     * The Alien bug can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Drop all the alien bug's possessions.
     *
     * @param map map that the bug is currently in
     * @return description of what it drop(s)
     */
    public List<String> dropAllScraps(GameMap map) {
        List<Item> itemsToDrop = new ArrayList<>(this.getItemInventory());
        List<String> actionDescriptions = new ArrayList<>();
        for (Item item : itemsToDrop) {
            Action dropAction = new DropAction(item);
            String result = "\n" + dropAction.execute(this, map);
            actionDescriptions.add(result);
        }
        return actionDescriptions;
    }

    /**
     * Drop all possessions and remove the alien bug from the map when it is unconscious (die from the hand of other).
     *
     * @param actor the alien bug
     * @param map the map that the bug is currently in
     * @return description of how the alien bug get demise.
     */
    @Override
    public String unconscious(Actor actor, GameMap map){
        List<String> dropDescriptions = dropAllScraps(map);
        map.removeActor(this);
        return this + " met their demise." + String.join("", dropDescriptions);
    }


    /**
     * Drop all possessions and remove the alien bug from the map when it is unconscious (die naturally).
     *
     * @param map the map that the bug is currently in
     * @return description of how the alien bug get demise.
     */
    public String unconscious(GameMap map) {
        List<String> dropDescriptions = dropAllScraps(map);
        map.removeActor(this);
        return this + " ceased to exist." + String.join("", dropDescriptions);
    }
}
