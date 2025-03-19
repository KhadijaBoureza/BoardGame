package rules;

import player.PlayerColor;
import board.VariationGameBoard;

/**
 * HitIgnoredRule does nothing on a hit.
 */
public class HitIgnoredRule implements HitRule {
    @Override
    public void handleHit(VariationGameBoard board, PlayerColor mover) {
        System.out.println("Hit! (ignored)");
    }
}

