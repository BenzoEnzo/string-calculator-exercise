package pl.bartkub.exercise.calculator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalculationValidator {

    private final Parser parser = new Parser();

    public void validateSummaryOperation(List<String> negatives,
                                         Map<String, Integer> wrongDelimiters) {

        if (negatives.isEmpty() && wrongDelimiters.isEmpty()) return;

        throw new CalculationException(createCalculatorValidationMessage(negatives, wrongDelimiters));
    }

    private String createCalculatorValidationMessage(List<String> negatives, Map<String, Integer> wrongDelimiters) {
        return Stream.of(
                        negatives.isEmpty() ? null
                                : replacePlaceholder(ErrorMessage.NEGATIVE_NUMBERS.getText(),
                                "numbers", String.join(", ", negatives)),
                        wrongDelimiters.isEmpty() ? null
                                : replacePlaceholder(ErrorMessage.WRONG_DELIMITERS.getText(),
                                "delimiters", createDelimiterMessage(wrongDelimiters))
                ).filter(Objects::nonNull)
                .collect(Collectors.joining("\n "));
    }

    private String createDelimiterMessage(Map<String, Integer> wrongDelimiters) {
        return wrongDelimiters.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(delimiter -> parser.extractRawDelimiter(delimiter.getKey())
                        + " at pos " + delimiter.getValue())
                .collect(Collectors.joining(", "));
    }

    private String replacePlaceholder(String template, String key, String value) {
        return template.replace("{{" + key + "}}", value);
    }
}