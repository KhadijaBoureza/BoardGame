package main;

import rules.ExactLandingEndRule;
import rules.HitSendHomeRule;
import board.VariationGameBoard;
import dice.DiceShaker;
import dice.FixedDiceShaker;

/**
 * VariationMain demonstrates a simulation run using the variations.
 *
 * In this example, the game is configured with:
 * - Exact Landing Rule (a player must land exactly on END to win; overshoots cause a bounce).
 * - Hit Sends Home (if a hit occurs, the opponent is returned to HOME).
 *
 * We use a FixedDiceShaker to simulate a predetermined dice sequence.
 * For example, the dice sequence {12,12,7,11,3,3} produces:
 *   Red play 1: 12
 *   Blue play 1: 12
 *   Red play 2: 7
 *   Blue play 2: 11
 *   Red play 3: 3
 *   Blue play 3: 3
 */
public class Main {
    public static void main(String[] args) {
        // Configure the board with the desired rules.
        // For exact landing and hit sends home:
        VariationGameBoard gameBoard = new VariationGameBoard(new ExactLandingEndRule(), new HitSendHomeRule());

        // Fixed dice sequence for simulation.
        int[] fixedRolls = {12, 12, 7, 11, 3, 3};
        DiceShaker fixedDie = new FixedDiceShaker(fixedRolls);

        boolean gameEnded = false;
        while (!gameEnded) {
            // Red player's turn.
            int redRoll = fixedDie.shake();
            gameBoard.advanceRed(redRoll);
            if (gameBoard.hasRedWon()) {
                System.out.println("Red wins in " + gameBoard.getTotalPlays() + " moves!");
                gameEnded = true;
                break;
            }
            // Blue player's turn.
            int blueRoll = fixedDie.shake();
            gameBoard.advanceBlue(blueRoll);
            if (gameBoard.hasBlueWon()) {
                System.out.println("Blue wins in " + gameBoard.getTotalPlays() + " moves!");
                gameEnded = true;
                break;
            }
        }
        System.out.println("Total plays: " + gameBoard.getTotalPlays());
    }
}
