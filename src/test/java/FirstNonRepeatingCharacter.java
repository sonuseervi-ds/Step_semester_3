package string.class_problems;

import java.util.Scanner;

/**
 * Practice 4: First Non-Repeating Character
 * Uses an ASCII frequency array to find the first character that appears only once.
 */
public class FirstNonRepeatingCharacter {

    static final int ASCII_SIZE = 256;                                  // Fixed value
    static final char NOT_FOUND = '\0';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a word or sentence: ");
            String text = scanner.nextLine();                           // User input

            validateText(text);
            char resultChar = findFirstNonRepeatingChar(text);

            if (resultChar == NOT_FOUND) {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: \'" + resultChar
                        + "\' (ASCII " + (int) resultChar + ")");
            }
        } catch (EmptyInputException e) {                              // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {                   // Unchecked exception
            System.out.println("Error: Only standard ASCII characters are supported.");
        } finally {
            scanner.close();
        }
    }

    // Makes sure the input is not empty
    public static void validateText(String text) throws EmptyInputException {
        if (text.isEmpty()) {
            throw new EmptyInputException("Input cannot be empty.");
        }
    }

    // Counts each character by its ASCII code, then scans left to right for a count of 1
    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[ASCII_SIZE];
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);                                  // Early exit
            }
        }
        return NOT_FOUND;
    }
}

// Custom checked exception used by this program
class EmptyInputException extends Exception {
    public EmptyInputException(String message) {
        super(message);
    }
}
