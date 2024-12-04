package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

public class TraderActor extends Actor {

    /**
     * The constructor of the TraderActor class.
     *
     * @param name the name of the Actor
     * @param displayChar display character that represents the actor
     * @param hitPoint Actor's health
     */
    public TraderActor(String name, char displayChar, int hitPoint){
        super(name, displayChar, hitPoint);
        this.addCapability(Status.TRADER);
    }

    /**
     * Action for the actor to perform on in each turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return null. un-decide action as there could be able to do anything in the future.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}