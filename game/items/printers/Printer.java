package game.items.printers;

import edu.monash.fit2099.engine.items.Item;

public interface Printer {

    /**
     * Weather or not the printer prints the item
     *
     * @return true if item is printed, else false
     */
    boolean printsItem();

    /**
     * Getter to get the item
     *
     * @return the item
     */
    Item getItem();

    /**
     * The credit of the item
     *
     * @return the credit
     */
    int creditPayable();
}
