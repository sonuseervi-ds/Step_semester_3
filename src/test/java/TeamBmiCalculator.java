package string.class_problems;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Practice 3: BMI Calculator for a Team
 * Calculates BMI for each person and prints a wellness report table.
 */
public class TeamBmiCalculator {

    static final double UNDERWEIGHT_LIMIT = 18.5;                      // Fixed values
    static final double NORMAL_LIMIT = 25.0;
    static final double OVERWEIGHT_LIMIT = 30.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter number of team members: ");
            int teamSize = scanner.nextInt();                           // User input
            double[] heights = new double[teamSize];
            double[] weights = new double[teamSize];

            readMeasurements(scanner, heights, weights);
            printWellnessReport(heights, weights);
        } catch (InputMismatchException e) {                            // Unchecked exception
            System.out.println("Error: Please enter numbers only.");
        } catch (NegativeArraySizeException e) {                        // Unchecked exception
            System.out.println("Error: Team size cannot be negative.");
        } catch (InvalidMeasurementException e) {                       // Checked exception
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Reads height (m) and weight (kg) for every person
    public static void readMeasurements(Scanner scanner, double[] heights, double[] weights)
            throws InvalidMeasurementException {
        for (int i = 0; i < heights.length; i++) {
            System.out.print("Person " + (i + 1) + " - Height (m) and Weight (kg): ");
            heights[i] = scanner.nextDouble();
            weights[i] = scanner.nextDouble();
            if (heights[i] <= 0 || weights[i] <= 0) {
                throw new InvalidMeasurementException("Height and weight must be greater than zero.");
            }
        }
    }

    // BMI = weight / (height x height)
    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    // Classifies BMI into a health status
    public static String getBmiStatus(double bmi) {
        if (bmi < UNDERWEIGHT_LIMIT) {
            return "Underweight";
        } else if (bmi < NORMAL_LIMIT) {
            return "Normal";
        } else if (bmi < OVERWEIGHT_LIMIT) {
            return "Overweight";
        }
        return "Obese";
    }

    // Prints Person | Height | Weight | BMI | Status for the whole team
    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("\nPerson | Height (m) | Weight (kg) | BMI   | Status");
        System.out.println("----------------------------------------------------");
        for (int i = 0; i < heights.length; i++) {
            double bmi = calculateBmi(heights[i], weights[i]);
            System.out.printf("%-6d | %-10.2f | %-11.1f | %-5.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
    }
}

// Custom checked exception used by this program
class InvalidMeasurementException extends Exception {
    public InvalidMeasurementException(String message) {
        super(message);
    }
}
