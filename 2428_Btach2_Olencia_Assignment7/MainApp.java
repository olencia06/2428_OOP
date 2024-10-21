// MainApp.java
import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        // List of numbers for testing operations
        List<String> numbers = new ArrayList<>();
        numbers.add("10");
        numbers.add("5");
        numbers.add(null); // This will trigger NullPointerException
        numbers.add("invalid"); // This will trigger NumberFormatException

        // Parsing integers from strings with error handling
        System.out.println("Parsed Integer Results:");
        for (String number : numbers) {
            Integer parsed = ExceptionHandling.parseInteger(number);
            System.out.println("Parsed result: " + parsed);
        }

        // Performing division with additional error handling
        System.out.println("\nDivision Results:");
        Integer a = 10;
        Integer b = 0;  // Division by zero

        // This will trigger ArithmeticException
        Integer result = ExceptionHandling.divide(a, b);
        System.out.println(a + " / " + b + " = " + result);

        // This will trigger NullPointerException
        result = ExceptionHandling.divide(a, null);
        System.out.println(a + " / null = " + result);
    }
}
