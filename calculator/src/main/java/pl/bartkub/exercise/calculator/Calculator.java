package pl.bartkub.exercise.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    private final Parser parser = new Parser();
    private final List<String> errors = new ArrayList<>();

    public Calculator() {
    }

    public int add(String numbers) {
        if (numbers.isBlank()) {
            return 0;
        }

        String delimiter = parser.extractDelimiter(numbers);

        if (numbers.startsWith("//")) {
            numbers = numbers.substring(numbers.indexOf('\n') + 1);
        }

        String[] parts = numbers.split(delimiter);

        return calculate(transformToNumbers(parts));
    }

    private int[] transformToNumbers(String[] parts) {
        return Arrays.stream(parts)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int calculate(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n < 1001)
                .sum();
    }
}
