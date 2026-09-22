package string.assigment_problems;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program 1: Exam Hall Seat Duplication Checker
 * Checks an array of seat numbers for duplicates using arrays and nested loops only.
 */
public class ExamSeatDuplicateChecker {

    static final int MIN_SEAT_NUMBER = 1;                   // Fixed value

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int[] seatNumbers = readSeatNumbers(scanner);   // User input
            checkDuplicateSeats(seatNumbers);
        } catch (InputMismatchException e) {                // Unchecked exception
            System.out.println("Error: Please enter whole numbers only.");
        } catch (NegativeArraySizeException e) {            // Unchecked exception
            System.out.println("Error: Number of students cannot be negative.");
        } catch (InvalidSeatNumberException e) {            // Checked exception
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Reads the number of students and their seat numbers from the user
    public static int[] readSeatNumbers(Scanner scanner) throws InvalidSeatNumberException {
        System.out.print("Enter number of students: ");
        int studentCount = scanner.nextInt();
        int[] seatNumbers = new int[studentCount];

        System.out.println("Enter " + studentCount + " seat numbers:");
        for (int i = 0; i < studentCount; i++) {
            seatNumbers[i] = scanner.nextInt();
            if (seatNumbers[i] < MIN_SEAT_NUMBER) {
                throw new InvalidSeatNumberException("Seat number must be " + MIN_SEAT_NUMBER + " or above.");
            }
        }
        return seatNumbers;
    }

    // Returns true if the seat at position 'index' already appeared earlier in the array
    public static boolean isAlreadyReported(int[] seatNumbers, int index) {
        for (int k = 0; k < index; k++) {
            if (seatNumbers[k] == seatNumbers[index]) {
                return true;
            }
        }
        return false;
    }

    // Compares every seat number with every other seat number and prints duplicates
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean isDuplicateFound = false;                   // Result variable

        for (int i = 0; i < seatNumbers.length; i++) {
            if (isAlreadyReported(seatNumbers, i)) {
                continue;                                   // Avoid printing the same duplicate twice
            }
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    isDuplicateFound = true;
                    break;
                }
            }
        }

        if (!isDuplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }
}

// Custom checked exception used by this program
class InvalidSeatNumberException extends Exception {        // Checked exception
    public InvalidSeatNumberException(String message) {
        super(message);
    }
}
