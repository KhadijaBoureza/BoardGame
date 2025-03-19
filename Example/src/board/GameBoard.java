package board;

/**
 * The GameBoard interface declares a method for advancing a player's position.
 */
public interface GameBoard {
    /**
     * Advances the player's position by the given dice roll.
     * @param roll the dice roll value.
     */
    void advance(int roll);
}

