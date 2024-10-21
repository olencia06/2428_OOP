// GenericOperation.java
import java.util.function.BiFunction;

public class GenericOperation {

    // Generic method to apply an operation to two numbers
    public static <T extends Number> T applyOperation(T a, T b, BiFunction<T, T, T> operation) {
        return operation.apply(a, b);
    }
}
