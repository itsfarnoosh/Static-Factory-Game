package game.items.printers;

import edu.monash.fit2099.engine.items.Item;
import game.items.DragonSlayer;

import java.util.Random;

public class DragonSlayerPrinter implements Printer {
    // I would love to call this class Godot. RIP.
    private final int PRICE;
    private final int SUCCESS_CHANCE; // chance of DragonSlayer printing.

    /**
     * Constructor
     *
     */
    public DragonSlayerPrinter(){
        this.PRICE = 50;
        this.SUCCESS_CHANCE = 50;
    }

    /**
     * Provide the Dragon-Slayer.
     *
     * @return defensive copy / replica of Dragon-Slayer.
     */
    public Item getItem() {
        return new DragonSlayer();
    }

    /**
     * Provide the price of the Dragon-Slayer
     *
     * @return the price of the Dragon-Slayer
     */
    public int creditPayable() {
        return PRICE;
    }

    /**
     * Process the chance of printing dragon-slayer
     *
     * @return whether the dragon-slayer is printable.
     */
    public boolean printsItem(){
        int percentage = new Random().nextInt(100);

        if (percentage < this.SUCCESS_CHANCE){
            return false;
        }
        return true;
    }

    /**
     * provide the name of the sword (which is Dragon Slayer / chunk of metal.)
     *
     * @return Dragon-Slayer in string
     */
    @Override
    public String toString() {
        return "Dragon Slayer";
    }
}
