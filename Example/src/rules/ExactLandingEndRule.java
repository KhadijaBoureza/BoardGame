package rules;

/**
 * ExactLandingEndRule requires the dice roll to land exactly on END.
 * If the roll is too high, the piece bounces back from END by the overshoot amount.
 */
public class ExactLandingEndRule implements EndRule {
    @Override
    public int adjustPosition(int currentIndex, int roll, int totalLength) {
        int required = (totalLength - 1) - currentIndex;
        if (roll > required) {
            int overshoot = roll - required;
            System.out.println("Overshoot detected!");
            return (totalLength - 1) - overshoot;
        } else {
            return currentIndex + roll;
        }
    }
}

