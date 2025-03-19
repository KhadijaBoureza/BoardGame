package board;

public class ObservedGameBoard implements GameBoard {
    // Board configuration:
    // MAIN_LENGTH: positions on the main board (18 positions)
    // TAIL_LENGTH: positions in the Tail (3 positions; the last is End)
    private static final int MAIN_LENGTH = 18;
    private static final int TAIL_LENGTH = 3;
    private static final int TOTAL_LENGTH = MAIN_LENGTH + TAIL_LENGTH; // 21 steps

    // Each player’s progress is tracked by an index [0, TOTAL_LENGTH-1]
    // For Red: index 0 represents HOME (Position 1)
    // For Blue: index 0 represents HOME (Position calculated from base 10)
    private int redIndex;
    private int blueIndex;

    // Turn counters for reporting
    private int redTurns;
    private int blueTurns;

    public ObservedGameBoard() {
        redIndex = 0;
        blueIndex = 0;
        redTurns = 0;
        blueTurns = 0;
    }

    /**
     * Not used – we provide dedicated methods for each player.
     */
    @Override
    public void advance(int roll) {
        throw new UnsupportedOperationException("Use advanceRed or advanceBlue instead.");
    }

    /**
     * Advances the Red player's piece by the given roll.
     * If the move overshoots the End, Red wins.
     * After moving, if Red lands on the square occupied by Blue, a Hit is announced.
     * @param roll the dice roll (sum of two dice)
     */
    public void advanceRed(int roll) {
        int oldIndex = redIndex;
        redTurns++;
        int newIndex = redIndex + roll;
        if (newIndex >= TOTAL_LENGTH) {  // overshoot or exact landing: win
            newIndex = TOTAL_LENGTH - 1;
        }
        redIndex = newIndex;
        System.out.println("Red play " + redTurns + " rolls " + roll);
        System.out.println("Red moves from " + getRedPositionString(oldIndex)
                + " to " + getRedPositionString(redIndex));
        System.out.println("Total Red turns: " + redTurns);

        // Check for Hit: if Red's new absolute position equals Blue's absolute position.
        if (getAbsoluteRedPosition() == getAbsoluteBluePosition()) {
            // Only announce a Hit if Red did not start from HOME (to avoid false hit on first move)
            // and if the players are not both at their HOME.
            if (!(oldIndex == 0 && blueIndex == 0)) {
                System.out.println("Hit! Blue at " + getBluePositionString(blueIndex) + " is hit!");
            }
        }
        System.out.println("-----------------------------------");
    }

    /**
     * Advances the Blue player's piece by the given roll.
     * If the move overshoots the End, Blue wins.
     * After moving, if Blue lands on the square occupied by Red, a Hit is announced.
     * @param roll the dice roll (sum of two dice)
     */
    public void advanceBlue(int roll) {
        int oldIndex = blueIndex;
        blueTurns++;
        int newIndex = blueIndex + roll;
        if (newIndex >= TOTAL_LENGTH) {  // overshoot or exact landing: win
            newIndex = TOTAL_LENGTH - 1;
        }
        blueIndex = newIndex;
        System.out.println("Blue play " + blueTurns + " rolls " + roll);
        System.out.println("Blue moves from " + getBluePositionString(oldIndex)
                + " to " + getBluePositionString(blueIndex));
        System.out.println("Total Blue turns: " + blueTurns);

        // Check for Hit: if Blue's new absolute position equals Red's absolute position.
        if (getAbsoluteBluePosition() == getAbsoluteRedPosition()) {
            if (!(oldIndex == 0 && redIndex == 0)) {
                System.out.println("Hit! Red at " + getRedPositionString(redIndex) + " is hit!");
            }
        }
        System.out.println("-----------------------------------");
    }

    /**
     * Returns true if Red has reached the End.
     */
    public boolean hasRedWon() {
        return redIndex == TOTAL_LENGTH - 1;
    }

    /**
     * Returns true if Blue has reached the End.
     */
    public boolean hasBlueWon() {
        return blueIndex == TOTAL_LENGTH - 1;
    }

    /**
     * Returns the total number of plays (sum of Red and Blue turns).
     */
    public int getTotalPlays() {
        return redTurns + blueTurns;
    }

    // Helper methods to convert a path index into a display string for each player.
    // For Red:
    // - Index 0 is HOME (Position 1).
    // - Index 1 to MAIN_LENGTH-1 map to Positions 2 to 18.
    // - Index MAIN_LENGTH to TOTAL_LENGTH-1 map to Tail positions (1 to TAIL_LENGTH),
    //   with the last tail position labeled as END.
    private String getRedPositionString(int index) {
        if (index < MAIN_LENGTH) {
            if (index == 0) {
                return "HOME (Position 1)";
            } else {
                return "Position " + (index + 1);
            }
        } else {
            int tailPos = index - MAIN_LENGTH + 1;
            if (tailPos == TAIL_LENGTH) {
                return "END (Tail Position " + tailPos + ")";
            } else {
                return "TAIL (Tail Position " + tailPos + ")";
            }
        }
    }

    // For Blue:
    // - Blue's HOME is Position 10.
    // - The main board for Blue is computed by starting at 10 and moving clockwise.
    //   The mapping is: absolute position = (((10 - 1) + index) mod MAIN_LENGTH) + 1.
    // - The Tail is handled similarly as for Red.
    private String getBluePositionString(int index) {
        if (index < MAIN_LENGTH) {
            int pos = (((10 - 1) + index) % MAIN_LENGTH) + 1;
            if (index == 0) {
                return "HOME (Position " + pos + ")";
            } else {
                return "Position " + pos;
            }
        } else {
            int tailPos = index - MAIN_LENGTH + 1;
            if (tailPos == TAIL_LENGTH) {
                return "END (Tail Position " + tailPos + ")";
            } else {
                return "TAIL (Tail Position " + tailPos + ")";
            }
        }
    }

    /**
     * Returns the absolute board position for Red.
     * For display purposes, if on the main board, it is (index+1); if in the Tail, it shows as TAIL.
     * (This helper is used for Hit detection; we compare the absolute position numbers.)
     */
    private int getAbsoluteRedPosition() {
        if (redIndex < MAIN_LENGTH) {
            return redIndex + 1;
        } else {
            // For Tail positions, we assign positions beyond the main board.
            return MAIN_LENGTH + redIndex - MAIN_LENGTH + 1; // simply index+1
        }
    }

    /**
     * Returns the absolute board position for Blue.
     */
    private int getAbsoluteBluePosition() {
        if (blueIndex < MAIN_LENGTH) {
            return (((10 - 1) + blueIndex) % MAIN_LENGTH) + 1;
        } else {
            return MAIN_LENGTH + blueIndex - MAIN_LENGTH + 1; // index+1
        }
    }
}
