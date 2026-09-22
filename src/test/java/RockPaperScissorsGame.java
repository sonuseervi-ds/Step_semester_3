package string.class_problems;

import java.util.Random;
import java.util.Scanner;

/**
 * Practice 1: Rock-Paper-Scissors Game
 * Plays N rounds between the player and the computer and prints a scoreboard.
 */
public class RockPaperScissorsGame {

    static final int TOTAL_ROUNDS = 5;                                  // Fixed values
    static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    static final double PERCENTAGE_FACTOR = 100.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[][] roundTable = new String[TOTAL_ROUNDS][3];            // Player | Computer | Result

        try {
            for (int round = 0; round < TOTAL_ROUNDS; round++) {
                System.out.print("Round " + (round + 1) + " - Enter your move (Rock/Paper/Scissors): ");
                String playerMove = normalizeMove(scanner.nextLine());  // User input
                String computerMove = generateComputerMove(random);
                String result = playRound(playerMove, computerMove);

                roundTable[round][0] = playerMove;
                roundTable[round][1] = computerMove;
                roundTable[round][2] = result;
                System.out.println("Player: " + playerMove + ", Computer: " + computerMove + " -> " + result);
            }
            printSummary(roundTable);
        } catch (InvalidMoveException e) {                              // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (java.util.NoSuchElementException e) {                  // Unchecked exception
            System.out.println("Error: Input ended before all rounds were played.");
        } finally {
            scanner.close();
        }
    }

    // Converts input like "rock" or " ROCK " into "Rock", or throws if it is not a valid move
    public static String normalizeMove(String input) throws InvalidMoveException {
        String cleanedInput = input.trim();
        for (String move : MOVES) {
            if (move.equalsIgnoreCase(cleanedInput)) {
                return move;
            }
        }
        throw new InvalidMoveException("\"" + cleanedInput + "\" is not a valid move. Use Rock, Paper or Scissors.");
    }

    // Picks a random move for the computer
    public static String generateComputerMove(Random random) {
        return MOVES[random.nextInt(MOVES.length)];
    }

    // Decides the result of one round using standard rules
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }
        boolean isPlayerWinner = (playerMove.equals("Rock") && computerMove.equals("Scissors"))
                || (playerMove.equals("Paper") && computerMove.equals("Rock"))
                || (playerMove.equals("Scissors") && computerMove.equals("Paper"));
        return isPlayerWinner ? "Player Wins" : "Computer Wins";
    }

    // Prints the round table and the final scoreboard
    public static void printSummary(String[][] roundTable) {
        int winCount = 0;
        int lossCount = 0;
        int drawCount = 0;

        System.out.println("\nRound | Player Move | Computer Move | Result");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < roundTable.length; i++) {
            System.out.printf("%-5d | %-11s | %-13s | %s%n", i + 1, roundTable[i][0], roundTable[i][1], roundTable[i][2]);
            if (roundTable[i][2].equals("Player Wins")) {
                winCount++;
            } else if (roundTable[i][2].equals("Computer Wins")) {
                lossCount++;
            } else {
                drawCount++;
            }
        }
        double winPercentage = (winCount * PERCENTAGE_FACTOR) / roundTable.length;
        System.out.printf("%nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", winCount, lossCount, drawCount, winPercentage);
    }
}

// Custom checked exception used by this program
class InvalidMoveException extends Exception {
    public InvalidMoveException(String message) {
        super(message);
    }
}
