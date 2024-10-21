public class ExceptionHandling {

    // Method to handle division with exception handling
    public static Integer divide(Integer a, Integer b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
            return null;
        } catch (NullPointerException e) {
            System.out.println("Error: One of the operands is null.");
            return null;
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    // Method to parse a string into an integer with exception handling
    public static Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid format for number: " + str);
            return null;
        } catch (NullPointerException e) {
            System.out.println("Error: The input string is null.");
            return null;
        } catch (Exception e) {
            System.out.println("Error: An unexpected error occurred while parsing: " + e.getMessage());
            return null;
        }
    }

    // General error handling method
    public static <T extends Number> T handleError(T result, Exception e) {
        System.out.println("Error: " + e.getMessage());
        return result;
    }
}
