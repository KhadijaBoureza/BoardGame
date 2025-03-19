package dice;

import java.util.Random;

public class SingleDiceShaker implements DiceShaker {
    private final Random random = new Random();

    @Override
    public int shake() {
        return random.nextInt(6) + 1;
    }
}



