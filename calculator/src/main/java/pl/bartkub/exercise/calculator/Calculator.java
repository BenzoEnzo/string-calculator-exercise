package pl.bartkub.exercise.calculator;

import java.util.Arrays;

public class Calculator {

    public Calculator() {
    }

    public int add(String numbers) {
        if (numbers.isBlank()) {
            return 0;
        }

        String[] parts = numbers.split("[,\n]");

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
