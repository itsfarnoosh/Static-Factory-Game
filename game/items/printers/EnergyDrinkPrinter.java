package game.items.printers;

import edu.monash.fit2099.engine.items.Item;
import game.items.consumables.EnergyDrink;

import java.util.Random;

public class EnergyDrinkPrinter implements Printer {

    private final int healingPoints = 1;
    private int credit;

    private final int SUCCESS_CHANCE; // chance of intern paying double

    /**
     * Constructor
     *

     */
    public EnergyDrinkPrinter(){
        this.credit = 10;
        this.SUCCESS_CHANCE = 20;
    }

    /**
     * Provide the item (the energy drink)
     *
     * @return defensive copy of the item (drink)
     */
    public Item getItem() {
        return new EnergyDrink(healingPoints);
    }

    /**
     * Process the price for the energy drink.
     * if the chance is met, the price will be double.
     *
     * @return the price in integer for the energy drink
     */
    public int creditPayable() {
        int percentage = new Random().nextInt(100);

        if (percentage < this.SUCCESS_CHANCE){
            credit = credit * 2;
        }
        return credit;
    }

    /**
     * Provide eligibility to print the drink
     *
     * @return grant boolean.
     */
    public boolean printsItem(){
        return true;
    }


    /**
     * provide the name of product
     *
     * @return Energy Drink in string
     */
    @Override
    public String toString() {
        return "Energy Drink";
    }
}
