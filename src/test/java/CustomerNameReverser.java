package string.class_problems;

import java.util.Scanner;

/**
 * Practice 5: Reverse Customer Name
 * Reverses a customer's name without changing the original.
 */
public class CustomerNameReverser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine().trim();            // User input

            validateCustomerName(customerName);
            String reversedName = reverseCustomerName(customerName);    // Result

            System.out.println("Original Name: " + customerName);
            System.out.println("Reversed Name: " + reversedName);
        } catch (InvalidCustomerNameException e) {                     // Checked exception
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {                             // Unchecked exception
            System.out.println("Error: Customer name is missing.");
        } finally {
            scanner.close();
        }
    }

    // Ensures the name is not empty
    public static void validateCustomerName(String customerName) throws InvalidCustomerNameException {
        if (customerName.isEmpty()) {
            throw new InvalidCustomerNameException("Customer name cannot be empty.");
        }
    }

    // Returns a new reversed string; the original string is not modified
    public static String reverseCustomerName(String customerName) {
        char[] nameChars = customerName.toCharArray();
        char[] reversedChars = new char[nameChars.length];
        for (int i = 0; i < nameChars.length; i++) {
            reversedChars[i] = nameChars[nameChars.length - 1 - i];
        }
        return new String(reversedChars);
    }
}

// Custom checked exception used by this program
class InvalidCustomerNameException extends Exception {
    public InvalidCustomerNameException(String message) {
        super(message);
    }
}
