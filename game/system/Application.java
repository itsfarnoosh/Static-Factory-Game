package game.system;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.trees.SaplingState;
import game.system.teleportLocation.InternTeleportLocation;
import game.system.teleportLocation.TeleportLocation;
import game.actors.enemies.SuspiciousAstronaut;
import game.actors.traders.HumanoidFigure;
import game.actors.Player;
import game.actors.enemies.AlienBug;
import game.actors.enemies.HuntsmanSpider;
import game.grounds.*;
import game.grounds.trees.Inheritree;
import game.grounds.trees.SproutState;
import game.items.*;
import game.items.printers.*;
import game.items.consumables.GoldPot;
import game.items.consumables.PickleJar;
import game.items.sellables.*;

import java.util.*;

/**
 * The main class to start the game.
 * Created by: Adrian Kristanto
 * Modified by:
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Puddle());

        // Create the main game map
        List<String> map = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#___#..........~~",
                ".............##_##.........~~~",
                ".................~~........~~~",
                "................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        // Create the parking lot map
        List<String> parkingLot = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#___#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");
        GameMap parkingLotMap = new GameMap(groundFactory, parkingLot);
        world.addGameMap(parkingLotMap);

        // Create the new moon map
        List<String> moon = Arrays.asList(
                "..........................~~~~",
                "..........................~~~~",
                "..........................~~~~",
                "~..........................~..",
                "~~...........#####............",
                "~~~..........#___#............",
                "~~~..........#___#............",
                "~~~..........##_##............",
                "~~~..................~~.......",
                "~~~~................~~~~......",
                "~~~~...............~~~~~......",
                "..~................~~~~.......",
                "....................~~........",
                ".............~~...............",
                "............~~~~..............");
        GameMap moonMap = new GameMap(groundFactory, moon);
        world.addGameMap(moonMap);

        // Display the game title
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Add actors and items to the main game map
        gameMap.at(7, 9).addActor(new HuntsmanSpider());
        Player player = new Player("Intern", '@', 4);
        player.addBalance(10000);


        world.addPlayer(player, gameMap.at(15, 6));
        player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1000);

        AlienBug alienBug = new AlienBug();
        gameMap.at(3, 5).addActor(alienBug);

        gameMap.at(11, 9).addItem(new LargeBolt());
        gameMap.at(3, 10).addItem(new LargeBolt());
        gameMap.at(25, 4).addItem(new LargeBolt());

        gameMap.at(5, 7).addItem(new MetalSheet());
        gameMap.at(23, 7).addItem(new MetalSheet());
        gameMap.at(14, 2).addItem(new MetalSheet());

        gameMap.at(24, 1).setGround(new Inheritree(new SaplingState()));
        gameMap.at(8, 5).setGround(new Inheritree(new SaplingState()));
        gameMap.at(23, 11).setGround(new Inheritree(new SproutState()));

        gameMap.at(21, 3).setGround(new Crater<>(AlienBug.FACTORY));
        gameMap.at(3, 4).setGround(new Crater<>(HuntsmanSpider.FACTORY));
        Crater<SuspiciousAstronaut> testingCrater = new Crater<>(SuspiciousAstronaut.FACTORY);
        gameMap.at(26, 13).setGround(testingCrater);

        gameMap.at(2, 12).addItem(new MetalPipe());
        gameMap.at(25, 9).addItem(new MetalPipe());
        gameMap.at(13, 8).addItem(new MetalPipe());

        gameMap.at(4, 1).addItem(new GoldPot());
        gameMap.at(24, 9).addItem(new GoldPot());
        gameMap.at(2, 13).addItem(new GoldPot());

        gameMap.at(14, 8).addItem(new PickleJar());
        gameMap.at(19, 9).addItem(new PickleJar());
        gameMap.at(9, 13).addItem(new PickleJar());

        gameMap.at(14, 8).addItem(new Astley());

        // Set up item printers
        ArrayList<Printer> itemPrinters = new ArrayList<>();
        itemPrinters.add(new EnergyDrinkPrinter());
        itemPrinters.add(new DragonSlayerPrinter());
        itemPrinters.add(new ToiletPaperPrinter());
        itemPrinters.add(new AstleyPrinter());
        itemPrinters.add(new TheseusPrinter()); // Add TheseusPrinter to the list

      
        Map<GameMap, TeleportLocation> maps = new HashMap<>();
        maps.put(gameMap, new InternTeleportLocation("Polymorphia", 15, 6));
        maps.put(moonMap, new InternTeleportLocation("Refactario", 15, 6));
        maps.put(parkingLotMap, new InternTeleportLocation("Parking Lot", 3, 3));

        ComputerTerminal terminal = new ComputerTerminal(itemPrinters, maps);
        gameMap.at(16, 6).setGround(terminal);
        moonMap.at(16, 6).setGround(terminal);
        parkingLotMap.at(4, 3).setGround(terminal);

        parkingLotMap.at(3, 8).addActor(new HumanoidFigure());
        moonMap.at(24, 1).setGround(new Inheritree(new SproutState()));
        moonMap.at(8, 5).setGround(new Inheritree(new SproutState()));
        moonMap.at(23, 11).setGround(new Inheritree(new SproutState()));
        
        // Run the game
        world.run();

        System.out.println(FancyMessage.YOU_ARE_FIRED);
    }
}
