// LambdaOperations.java
import java.util.function.BiFunction;

public class LambdaOperations {

    // Lambda for addition
    public static final BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

    // Lambda for subtraction
    public static final BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;

    // Lambda for division
    public static final BiFunction<Integer, Integer, Integer> divide = (a, b) -> {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    };
}
