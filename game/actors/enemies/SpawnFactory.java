package game.actors.enemies;

/**
 * Represents a factory for creating instances of enemies in the game.
 * Defines a method to spawn a new creature of type Enemy.
 *
 * @param <Creature> the type of enemy that this factory produces.
 */
public interface SpawnFactory<Creature extends Enemy> {

    /**
     * Spawns a new instance of the specified type of enemy.
     *
     * @return a new instance of Creature.
     */
    Creature spawn();
}
