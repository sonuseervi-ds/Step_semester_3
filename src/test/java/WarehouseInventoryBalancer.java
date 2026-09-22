package string.assigment_problems;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program 4: Warehouse Inventory Balancer
 * Compares totals of two sections and finds the highest quantity item.
 */
public class WarehouseInventoryBalancer {

    static final String SECTION_A_NAME = "Section A";       // Fixed values
    static final String SECTION_B_NAME = "Section B";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter number of items in each section: ");
            int itemCount = scanner.nextInt();              // User input

            int[] sectionA = readQuantities(scanner, SECTION_A_NAME, itemCount);
            int[] sectionB = readQuantities(scanner, SECTION_B_NAME, itemCount);

            analyzeInventory(sectionA, sectionB);
        } catch (InputMismatchException e) {                // Unchecked exception
            System.out.println("Error: Quantities must be whole numbers.");
        } catch (NegativeArraySizeException e) {            // Unchecked exception
            System.out.println("Error: Item count cannot be negative.");
        } catch (IllegalArgumentException e) {             // Unchecked exception
            System.out.println("Error: " + e.getMessage());
        } catch (NegativeQuantityException e) {            // Checked exception
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Reads item quantities for one section
    public static int[] readQuantities(Scanner scanner, String sectionName, int itemCount)
            throws NegativeQuantityException {
        int[] quantities = new int[itemCount];
        System.out.println("Enter " + itemCount + " quantities for " + sectionName + ":");
        for (int i = 0; i < itemCount; i++) {
            quantities[i] = scanner.nextInt();
            if (quantities[i] < 0) {
                throw new NegativeQuantityException("Quantity cannot be negative in " + sectionName + ".");
            }
        }
        return quantities;
    }

    // Returns the total of all quantities in a section
    public static int calculateTotal(int[] quantities) {
        int total = 0;
        for (int quantity : quantities) {
            total += quantity;
        }
        return total;
    }

    // Computes totals, balance status and the highest quantity across both sections
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        if (sectionA.length != sectionB.length || sectionA.length == 0) {
            throw new IllegalArgumentException("Both sections must have the same, non-zero number of items.");
        }

        int totalA = calculateTotal(sectionA);
        int totalB = calculateTotal(sectionB);
        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int highestQuantity = sectionA[0];
        String highestSection = SECTION_A_NAME;
        int highestIndex = 0;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highestQuantity) {            // '>' keeps the first occurrence on ties
                highestQuantity = sectionA[i];
                highestSection = SECTION_A_NAME;
                highestIndex = i;
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highestQuantity) {
                highestQuantity = sectionB[i];
                highestSection = SECTION_B_NAME;
                highestIndex = i;
            }
        }

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB
                + " | Status: " + status + " | Highest Quantity: " + highestQuantity
                + " (" + highestSection + ", Item " + (highestIndex + 1) + ")");
    }
}

// Custom checked exception used by this program
class NegativeQuantityException extends Exception {         // Checked exception
    public NegativeQuantityException(String message) {
        super(message);
    }
}
