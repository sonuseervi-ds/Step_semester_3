package string.assigment_problems;

import java.util.Scanner;

/**
 * Program 3: Traffic Signal Streak Analyzer
 * Finds the longest continuous streak of the same signal colour in a log.
 */
public class TrafficSignalStreakAnalyzer {

    static final String VALID_SIGNALS = "RYG";              // Fixed value

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the signal log (R/Y/G): ");
            String signalLog = scanner.nextLine().trim().toUpperCase();   // User input

            validateSignalLog(signalLog);
            findLongestStreak(signalLog);
        } catch (InvalidSignalException e) {               // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {             // Unchecked exception
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Ensures the log is not empty and contains only R, Y or G
    public static void validateSignalLog(String signalLog) throws InvalidSignalException {
        if (signalLog.isEmpty()) {
            throw new IllegalArgumentException("Signal log cannot be empty.");
        }
        for (int i = 0; i < signalLog.length(); i++) {
            char signal = signalLog.charAt(i);
            if (VALID_SIGNALS.indexOf(signal) == -1) {
                throw new InvalidSignalException("Invalid signal \'" + signal
                        + "\' at position " + (i + 1) + ". Use only R, Y or G.");
            }
        }
    }

    // Scans the log, tracks each streak and prints the longest one
    public static void findLongestStreak(String signalLog) {
        char longestColor = signalLog.charAt(0);
        int longestLength = 1;
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestColor = signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: \'" + longestColor + "\' repeated " + longestLength + " times");
    }
}

// Custom checked exception used by this program
class InvalidSignalException extends Exception {            // Checked exception
    public InvalidSignalException(String message) {
        super(message);
    }
}
