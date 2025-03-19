package board;

import player.PlayerColor;
import rules.EndRule;
import rules.HitRule;

/**
 * VariationGameBoard implements a board that uses strategy objects (EndRule and HitRule)
 * to adjust moves and handle hits. It represents a board with a configurable number of
 * main positions and tail positions.
 *
 * In this basic variation, the board has 18 main positions and 3 tail positions (total 21 steps).
 * Red's HOME is at Position 1 and Blue's HOME is at Position 10.
 */
public class VariationGameBoard implements GameBoard {
    private final int MAIN_LENGTH = 18;
    private final int TAIL_LENGTH = 3;
    private final int TOTAL_LENGTH = MAIN_LENGTH + TAIL_LENGTH;

    private final EndRule endRule;
    private final HitRule hitRule;

    // Player positions (indexes 0 to TOTAL_LENGTH - 1)
    private int redIndex;
    private int blueIndex;

    // Turn counters
    private int redTurns;
    private int blueTurns;

    public VariationGameBoard(EndRule endRule, HitRule hitRule) {
        this.endRule = endRule;
        this.hitRule = hitRule;
        redIndex = 0;
        blueIndex = 0;
        redTurns = 0;
        blueTurns = 0;
    }

    /**
     * Not used – use advanceRed or advanceBlue.
     */
    @Override
    public void advance(int roll) {
        throw new UnsupportedOperationException("Use advanceRed or advanceBlue instead.");
    }

    /**
     * Advances the Red player's piece using the configured EndRule and then applies the HitRule.
     * @param roll the dice roll (sum of dice).
     */
    public void advanceRed(int roll) {
        int oldIndex = redIndex;
        redTurns++;
        redIndex = endRule.adjustPosition(redIndex, roll, TOTAL_LENGTH);
        System.out.println("Red play " + redTurns + " rolls " + roll);
        System.out.println("Red moves from " + formatRedPosition(oldIndex)
                + " to " + formatRedPosition(redIndex));
        System.out.println("Total Red turns: " + redTurns);
        hitRule.handleHit(this, PlayerColor.RED);
        System.out.println("-----------------------------------");
    }

    /**
     * Advances the Blue player's piece using the configured EndRule and then applies the HitRule.
     * @param roll the dice roll (sum of dice).
     */
    public void advanceBlue(int roll) {
        int oldIndex = blueIndex;
        blueTurns++;
        blueIndex = endRule.adjustPosition(blueIndex, roll, TOTAL_LENGTH);
        System.out.println("Blue play " + blueTurns + " rolls " + roll);
        System.out.println("Blue moves from " + formatBluePosition(oldIndex)
                + " to " + formatBluePosition(blueIndex));
        System.out.println("Total Blue turns: " + blueTurns);
        hitRule.handleHit(this, PlayerColor.BLUE);
        System.out.println("-----------------------------------");
    }

    public boolean hasRedWon() {
        return redIndex == TOTAL_LENGTH - 1;
    }

    public boolean hasBlueWon() {
        return blueIndex == TOTAL_LENGTH - 1;
    }

    public int getTotalPlays() {
        return redTurns + blueTurns;
    }

    // Getters and setters for positions (used by hit rules)
    public int getRedIndex() {
        return redIndex;
    }

    public int getBlueIndex() {
        return blueIndex;
    }

    public void setRedIndex(int index) {
        redIndex = index;
    }

    public void setBlueIndex(int index) {
        blueIndex = index;
    }

    /**
     * Returns Red's absolute board position.
     * For main board positions, this is simply index + 1.
     * For tail positions, we also use index + 1.
     */
    public int getAbsoluteRed() {
        return redIndex + 1;
    }

    /**
     * Returns Blue's absolute board position.
     * For main board positions, computed as (((10 - 1) + blueIndex) mod MAIN_LENGTH) + 1;
     * For tail positions, index + 1 is used.
     */
    public int getAbsoluteBlue() {
        if (blueIndex < MAIN_LENGTH) {
            return (((10 - 1) + blueIndex) % MAIN_LENGTH) + 1;
        } else {
            return blueIndex + 1;
        }
    }

    // Helpers for display.
    private String formatRedPosition(int index) {
        if (index < MAIN_LENGTH) {
            return index == 0 ? "HOME (Position 1)" : "Position " + (index + 1);
        } else {
            int tailPos = index - MAIN_LENGTH + 1;
            return tailPos == TAIL_LENGTH ? "END (Tail Position " + tailPos + ")"
                    : "TAIL (Tail Position " + tailPos + ")";
        }
    }

    private String formatBluePosition(int index) {
        if (index < MAIN_LENGTH) {
            int pos = (((10 - 1) + index) % MAIN_LENGTH) + 1;
            return index == 0 ? "HOME (Position " + pos + ")" : "Position " + pos;
        } else {
            int tailPos = index - MAIN_LENGTH + 1;
            return tailPos == TAIL_LENGTH ? "END (Tail Position " + tailPos + ")"
                    : "TAIL (Tail Position " + tailPos + ")";
        }
    }
}
