package pl.bartkub.exercise.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {

    private final Parser parser = new Parser();
    private final Stack<Integer> numbersToAdd = new Stack<>();
    private final List<String> errors = new ArrayList<>();

    public Calculator() {
    }

    public int add(String numbers) {
        if (numbers.isBlank()) {
            return 0;
        }

        String delimiter = parser.extractDelimiter(numbers);
        List<String> parts = parser.divideInput(numbers);

        return calculate(parts, delimiter);
    }

    private int calculate(List<String> parts, String delimiter) {
        int sum = 0;

        for (String part : parts) {
            if (part.matches(delimiter)) {
                sum = addValueToSum(numbersToAdd.pop(), sum);
            } else {
                int value = Integer.parseInt(part);
                numbersToAdd.push(value);
            }
        }

        sum = addValueToSum(numbersToAdd.pop(), sum);

        return sum;
    }

    private int addValueToSum(int value, int sum) {
        if (value < 1001) {
            sum += value;
        }

        return sum;
    }
}
