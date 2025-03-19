package rules;

/**
 * DefaultEndRule simply moves forward; if the new index is beyond the board, it is clamped at END.
 */
public class DefaultEndRule implements EndRule {
    @Override
    public int adjustPosition(int currentIndex, int roll, int totalLength) {
        int newIndex = currentIndex + roll;
        return newIndex >= totalLength ? totalLength - 1 : newIndex;
    }
}
