package rules;

import player.PlayerColor;
import board.VariationGameBoard;

/**
 * HitSendHomeRule sends the opponent back to HOME when hit.
 */
public class HitSendHomeRule implements HitRule {
    @Override
    public void handleHit(VariationGameBoard board, PlayerColor mover) {
        if(mover == PlayerColor.RED) {
            if(board.getAbsoluteRed() == board.getAbsoluteBlue() && !(board.getRedIndex() == 0 && board.getBlueIndex() == 0)) {
                System.out.println("Hit! Blue is sent HOME.");
                board.setBlueIndex(0);
            }
        } else { // mover == PlayerColor.BLUE
            if(board.getAbsoluteBlue() == board.getAbsoluteRed() && !(board.getBlueIndex() == 0 && board.getRedIndex() == 0)) {
                System.out.println("Hit! Red is sent HOME.");
                board.setRedIndex(0);
            }
        }
    }
}

