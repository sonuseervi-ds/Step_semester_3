package string.class_problems;

import java.util.Scanner;

/**
 * Practice 2: Palindrome Checker (3 Approaches)
 * Checks a text using iteration, recursion and array reversal.
 */
public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a word or phrase: ");
            String inputText = scanner.nextLine();                      // User input
            String text = cleanText(inputText);

            String iterativeResult = formatResult(isPalindromeIterative(text));
            String recursiveResult = formatResult(isPalindromeRecursive(text));
            String reversalResult = formatResult(isPalindromeArrayReversal(text));

            System.out.println("Iterative: " + iterativeResult + " | Recursive: " + recursiveResult
                    + " | Array Reversal: " + reversalResult);
        } catch (EmptyTextException e) {                               // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (StackOverflowError e) {                               // Very long input for recursion
            System.out.println("Error: Text is too long for the recursive check.");
        } finally {
            scanner.close();
        }
    }

    // Lower-cases the text and removes spaces/punctuation so "Race car" is checked as "racecar"
    public static String cleanText(String inputText) throws EmptyTextException {
        String cleaned = inputText.toLowerCase().replaceAll("[^a-z0-9]", "");
        if (cleaned.isEmpty()) {
            throw new EmptyTextException("Please enter at least one letter or digit.");
        }
        return cleaned;
    }

    // Approach 1: compare characters from both ends moving to the middle
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: compare first and last characters, then recurse on the inner substring
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // Approach 3: reverse a character array and compare it with the original
    public static boolean isPalindromeArrayReversal(String text) {
        char[] originalChars = text.toCharArray();
        char[] reversedChars = new char[originalChars.length];
        for (int i = 0; i < originalChars.length; i++) {
            reversedChars[i] = originalChars[originalChars.length - 1 - i];
        }
        return new String(reversedChars).equals(text);
    }

    // Converts a boolean result to readable text
    public static String formatResult(boolean isPalindrome) {
        return isPalindrome ? "Palindrome" : "Not Palindrome";
    }
}

// Custom checked exception used by this program
class EmptyTextException extends Exception {
    public EmptyTextException(String message) {
        super(message);
    }
}
