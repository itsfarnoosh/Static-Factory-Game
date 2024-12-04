package game.items.sellables;

import java.util.Random;

/**
 * A class representing a Metal Sheet item in the game.
 */
public class MetalSheet extends SellableScrap {

    /***
     * Constructor.
     */
    public MetalSheet() {
        super("Metal Sheet", '%', true, 20, 60);
    }

    /***
     * Non-default Constructor.
     *
     * @param price price of the item
     * @param chance chance for the item to trigger its special affects
     */
    public MetalSheet(int price, int chance) {
        super("Metal Sheet", '%', true, price, chance);
    }

    /**
     * Calculate whether the item's chance will be met.
     * If item's chance is met, change the price to 10.
     * if not, the price remains the same as the original (20)
     *
     * @return The calculated/appropriate price in for the item based on the item's chance.
     */
    @Override
    public int getSellingPrice() {
        int percentage = new Random().nextInt(100);

        if (percentage < super.getChance()){
            super.setPrice(10);
        }

        return super.getPrice();
    }
}

