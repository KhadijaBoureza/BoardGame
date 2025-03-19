package rules;

/**
 * EndRule encapsulates the logic for moving a piece toward the End.
 */
public interface EndRule {
    /**
     * Adjusts the new position given a current index, the dice roll, and the board length.
     * @param currentIndex the player's current index.
     * @param roll the dice roll value.
     * @param totalLength total number of steps on the board.
     * @return the new index after applying the rule.
     */
    int adjustPosition(int currentIndex, int roll, int totalLength);
}
