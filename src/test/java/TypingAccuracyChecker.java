package string.assigment_problems;

import java.util.Scanner;

/**
 * Program 2: Typing Speed Test Accuracy Checker
 * Compares original and typed text character by character and reports accuracy.
 */
public class TypingAccuracyChecker {

    static final double PERCENTAGE_FACTOR = 100.0;          // Fixed value

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the original passage: ");
            String original = scanner.nextLine();           // User input
            System.out.print("Enter your typed text: ");
            String typed = scanner.nextLine();              // User input

            validateInputs(original, typed);
            checkTypingAccuracy(original, typed);
        } catch (LengthMismatchException e) {              // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {             // Unchecked exception
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Validates that both strings are non-empty and of equal length
    public static void validateInputs(String original, String typed) throws LengthMismatchException {
        if (original == null || original.isEmpty()) {
            throw new IllegalArgumentException("Original passage cannot be empty.");
        }
        if (original.length() != typed.length()) {
            throw new LengthMismatchException("Both texts must be of equal length (original = "
                    + original.length() + ", typed = " + typed.length() + ").");
        }
    }

    // Calculates accuracy percentage from matched and total characters
    public static double calculateAccuracy(int matchedCount, int totalCount) {
        return ((double) matchedCount / totalCount) * PERCENTAGE_FACTOR;
    }

    // Compares both strings position by position and prints the accuracy report
    public static void checkTypingAccuracy(String original, String typed) {
        int totalCount = original.length();
        int matchedCount = 0;
        int firstMismatchIndex = -1;

        for (int i = 0; i < totalCount; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchedCount++;
            } else if (firstMismatchIndex == -1) {
                firstMismatchIndex = i;
            }
        }

        double accuracy = calculateAccuracy(matchedCount, totalCount);
        String report = String.format("Matched: %d/%d | Accuracy: %.2f%%", matchedCount, totalCount, accuracy);

        if (firstMismatchIndex == -1) {
            report += " | No Mismatches";
        } else {
            char expectedChar = original.charAt(firstMismatchIndex);
            char typedChar = typed.charAt(firstMismatchIndex);
            // Position is shown 1-based; \' escape sequence used for quotes
            report += " | First Mismatch at position " + (firstMismatchIndex + 1)
                    + " (\'" + expectedChar + "\' vs \'" + typedChar + "\')";
            report += "\nASCII codes: \'" + expectedChar + "\' = " + (int) expectedChar
                    + ", \'" + typedChar + "\' = " + (int) typedChar;
        }
        System.out.println(report);
    }
}

// Custom checked exception used by this program
class LengthMismatchException extends Exception {           // Checked exception
    public LengthMismatchException(String message) {
        super(message);
    }
}
