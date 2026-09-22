package string.assigment_problems;

import java.util.Scanner;

/**
 * Program 5: Movie Review Word Length Profiler
 * Splits a review into words and counts Short, Medium and Long words.
 */
public class MovieReviewWordProfiler {

    static final int SHORT_WORD_MAX_LENGTH = 4;             // Fixed values
    static final int MEDIUM_WORD_MAX_LENGTH = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the movie review: ");
            String review = scanner.nextLine();             // User input

            validateReview(review);
            classifyWordLengths(review);
        } catch (EmptyReviewException e) {                 // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {                 // Unchecked exception
            System.out.println("Error: Review text is missing.");
        } finally {
            scanner.close();
        }
    }

    // Ensures the review contains some text
    public static void validateReview(String review) throws EmptyReviewException {
        if (review.trim().isEmpty()) {
            throw new EmptyReviewException("Review cannot be empty.");
        }
    }

    // Returns the word with punctuation removed so only letters are counted
    public static String removePunctuation(String word) {
        return word.replaceAll("[^a-zA-Z]", "");
    }

    // Splits the review into words and counts each length category
    public static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");      // String array
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            int letterCount = removePunctuation(word).length();
            if (letterCount == 0) {
                continue;                                   // Skip tokens with no letters, e.g. "--"
            } else if (letterCount <= SHORT_WORD_MAX_LENGTH) {
                shortCount++;
            } else if (letterCount <= MEDIUM_WORD_MAX_LENGTH) {
                mediumCount++;
            } else {
                longCount++;
            }
        }
        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }
}

// Custom checked exception used by this program
class EmptyReviewException extends Exception {              // Checked exception
    public EmptyReviewException(String message) {
        super(message);
    }
}
