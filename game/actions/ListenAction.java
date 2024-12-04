package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Astley;

public class ListenAction extends Action {
    private Astley astley;

    /**
     * Constructor for the ListenAction.
     *
     * @param astley The Astley AI device that the actor will interact with.
     */
    public ListenAction(Astley astley) {
        this.astley = astley;
    }

    /**
     * Perform the action of listening to Astley's monologue.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened as a result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // This method calls getMonologue to get a random monologue based on the conditions.
        String monologue = astley.getMonologue(actor);
        return actor + " listens to Astley: \"" + monologue + "\"";
    }

    /**
     * A string describing the action suitable for feedback in the UI.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Listen to Astley"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to Astley";
    }
}
