package rules;

import player.PlayerColor;
import board.VariationGameBoard;

/**
 * HitRule defines how to handle a hit (when one player lands on a square occupied by the opponent).
 */
public interface HitRule {
    /**
     * Applies the hit rule.
     * @param board the game board (which holds player positions).
     * @param mover the player who just moved.
     */
    void handleHit(VariationGameBoard board, PlayerColor mover);
}

