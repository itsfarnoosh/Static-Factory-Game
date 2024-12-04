package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.ActionList;
import game.actions.ListenAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes.HEALTH;
/**
 * An item representing the Astley AI device.
 * The device requires a subscription fee and provides monologues based on the actor's state.
 */
public class Astley extends Item {
    private static final int SUBSCRIPTION_FEE_TICK = 5;
    private int tickCounter = 0;
    private boolean hasSubscription = true;

    /**
     * Constructor for the Astley item.
     */
    public Astley() {
        super("Astley", 'z', true);
    }

    /**
     * Updates the state of the Astley item every tick.
     * Deducts a subscription fee from the actor every 5 ticks.
     *
     * @param currentLocation The current location of the item.
     * @param actor The actor carrying the item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
        if (actor.getItemInventory().contains(this)) {
            tickCounter++;
            if (tickCounter % SUBSCRIPTION_FEE_TICK == 0) {
                if (actor.getBalance() > 0) {
                    actor.deductBalance(1);
                    System.out.println("1 credit has been deducted for your Astley subscription");
                } else {
                    hasSubscription = false;
                    System.out.println("Subscription paused: insufficient credits to interact with Astley");
                }
            }
        }
    }

    /**
     * Returns a list of allowable actions for this item.
     * Provides a ListenAction if the subscription is active.
     *
     * @param owner The actor who owns this item.
     * @return A list of actions that can be performed with this item.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
        if (hasSubscription) {
            actions.add(new ListenAction(this));
        }
        return actions;
    }

    /**
     * Provides a random monologue based on the actor's state.
     *
     * @param actor The actor interacting with the Astley item.
     * @return A string containing the monologue.
     */
    public String getMonologue(Actor actor) {
        List<String> options = new ArrayList<>();
        options.add("The factory will never gonna give you up, valuable intern!");
        options.add("We promise we never gonna let you down with a range of staff benefits.");
        options.add("We never gonna run around and desert you, dear intern!");

        if (actor.getItemInventory().size() > 10) {
            options.add("We never gonna make you cry with unfair compensation.");
        }
        if (actor.getBalance() > 50) {
            options.add("Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.");
        }
        if (actor.getAttribute(HEALTH) < 2) {
            options.add("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.");
        }

        Random random = new Random();
        return options.get(random.nextInt(options.size()));
    }
}