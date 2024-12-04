package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.SpawnFactory;
import game.actors.enemies.Enemy;

import java.util.Random;

/**
 * Class representing Crater Ground type.
 */
public class Crater<Creature extends Enemy> extends Ground {
    private final SpawnFactory<Creature> spawner;

    /**
     * Constructor for crater, a Ground object that can spawn specific monsters.
     */
    public Crater(SpawnFactory<Creature> spawner) {
        super('u');
        this.spawner = spawner;
    }

    /**
     * The Crater can spawn enemy at each turn.
     *
     * @param location The location of the Crater
     */
    @Override
    public void tick(Location location) {
        Enemy creature = spawner.spawn();
        double spawnChance = creature.getSpawnChance();
        if (spawnChance > Math.random()){
            while (true) {
                int randNum = new Random().nextInt(8);
                Location spawnLocation = location.getExits().get(randNum).getDestination();
                if (spawnLocation.canActorEnter(creature)) {
                    if (!spawnLocation.containsAnActor()) {
                        spawnLocation.addActor(creature);
                        break;
                    }
                }
            }
        }
    }
}