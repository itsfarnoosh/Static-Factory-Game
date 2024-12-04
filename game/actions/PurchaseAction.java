package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.printers.Printer;

public class PurchaseAction extends Action {
    private Printer printer;

    /**
     * Constructor
     *
     * @param printer the Printer that actor purchase from
     */
    public PurchaseAction(Printer printer){
        this.printer = printer;
    }

    /**
     * Purchasing process at the terminal when player is purchasing an item.
     *
     * @return description of the purchasing action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        int credit = printer.creditPayable();
        Item item = printer.getItem();

        if (actor.getBalance() >= credit) {
            actor.deductBalance(credit);
            result += credit + " credit is deducted from " + actor + "'s balance\n";
            if (printer.printsItem()) {
                actor.addItemToInventory(item);
                result += actor + " has purchased a " + item;
            }else {
                result += "Transaction failed. " + actor + " did not purchase a " + item;
            }
        } else {
            result += "Insufficient balance.";
        }

        return result;
    }

    /**
     * Description of the action of purchasing that player can make.
     *
     * @return description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " wants to purchase a " + printer;
    }
}