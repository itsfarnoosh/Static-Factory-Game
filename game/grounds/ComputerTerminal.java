package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.GameMap;
import game.system.teleportLocation.TeleportLocation;
import game.actions.PurchaseAction;
import game.actions.TravelAction;
import game.items.printers.Printer;

import java.util.ArrayList;
import java.util.Map;

/**
 * A computer terminal that allows the actor to purchase items and travel to different maps.
 */
public class ComputerTerminal extends Ground {
    private final ArrayList<Printer> ITEM_PRINTERS;
    private final Map<GameMap, TeleportLocation> MAPS;


    /**
     * Constructor for the ComputerTerminal.
     *
     * @param ITEM_PRINTERS A list of item printers available at this terminal.
     * @param MAPS A HashMap of maps to teleport to
     */
    public ComputerTerminal(ArrayList<Printer> ITEM_PRINTERS, Map<GameMap, TeleportLocation> MAPS) {
        super('=');
        this.ITEM_PRINTERS = ITEM_PRINTERS;
        this.MAPS = MAPS;
    }

    /**
     * Returns a list of allowable actions for this ground.
     * In this case, it provides actions to purchase items and travel to different maps.
     *
     * @param actor     The actor performing the actions.
     * @param location  The current location of the ground.
     * @param direction The direction of the ground from the actor.
     * @return A list of actions that can be performed on this ground.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Add purchase actions
        for (Printer printer : ITEM_PRINTERS) {
            actions.add(new PurchaseAction(printer));
        }

        // loop for the given map from application.
        for (GameMap map: MAPS.keySet()){
            // if current map isn't the same as map
            if (map != location.map()){
                // get the TeleportLocation object.
                TeleportLocation internLocation = MAPS.get(map);
                // obtain the map's name and appropriate x and y coordinate for intern to teleport to.
                String mapName = internLocation.getMoonName();
                int x = internLocation.getXCoordinate();
                int y = internLocation.getYCoordinate();
                // generate TravelAction with the obtained x, y, and map's name.
                actions.add(new TravelAction(map.at(x, y), mapName));
            }
        }

        return actions;
    }
    /**
     * Determines if the ground blocks thrown objects.
     *
     * @return true, indicating that this ground blocks thrown objects.
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }
}
