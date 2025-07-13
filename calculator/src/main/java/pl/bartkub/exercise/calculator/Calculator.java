package pl.bartkub.exercise.calculator;

import java.util.*;

public class Calculator {
    private final Parser parser = new Parser();
    private final CalculationValidator calculationValidator = new CalculationValidator();
    private final List<String> negativeNumbers = new ArrayList<>();
    private final Map<String, Integer> wrongDelimiters = new HashMap<>();

    public int add(String numbers) {
        if (numbers.isBlank()) {
            return 0;
        }

        String delimiter = parser.extractDelimiter(numbers);
        String[] parts = parser.divideInput(numbers);

        int sum = calculate(parts, delimiter);

        calculationValidator.validateSummaryOperation(negativeNumbers, wrongDelimiters);

        return sum;
    }

    private int calculate(String[] parts, String delimiter) {
        int sum = 0;

        for (int i = 0; i < parts.length - 1; i += 2) {
            int j = i + 1;

            if (parts[j].matches(delimiter)) {
                sum = processSummary(parts[i], j, delimiter, sum);
            } else {
                wrongDelimiters.put(parts[j], j);
            }
        }

        sum = processSummary(parts[parts.length - 1], parts.length - 1, delimiter, sum);
        return sum;
    }

    private int processSummary(String value, int index, String separator, int sum) {
        try {
            int parsedValue = Integer.parseInt(value);

            if (parsedValue < 0) {
                negativeNumbers.add(value);
            }

            return addValueToSum(parsedValue, sum);
        } catch (Exception ex) {
            wrongDelimiters.put(separator, index);
        }
        return 0;
    }

    private int addValueToSum(int value, int sum) {
        return (value < 1001) ? sum + value : sum;
    }
}
