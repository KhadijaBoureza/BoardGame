package dice;

/**
 * FixedDiceShaker returns a predetermined sequence of dice values.
 * Useful for simulating fixed scenarios.
 */
public class FixedDiceShaker implements DiceShaker {
    private final int[] rolls;
    private int index;

    public FixedDiceShaker(int[] rolls) {
        this.rolls = rolls;
        this.index = 0;
    }

    @Override
    public int shake() {
        if (index < rolls.length) {
            return rolls[index++];
        } else {
            return 1; // Default value if sequence is exhausted.
        }
    }
}
