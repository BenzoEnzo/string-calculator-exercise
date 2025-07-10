package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void shouldCorrectlyReturnBaseDelimiter() {
        String delimiter = parser.extractDelimiter("1,2,4");

        assertEquals("[,\n]", delimiter);
    }

    @Test
    public void shouldCorrectlyReturnExtractedDelimiter() {
        String delimiter = parser.extractDelimiter("//sepq\n1sepq2sepq3sepq4");

        assertEquals("\\Qsepq\\E", delimiter);
    }

    @Test
    void shouldCorrectlyDivideInputWithNegativeNumber() {
        String input = "1,-44";
        List<String> expected = List.of("1", ",", "-44");

        List<String> result = parser.divideInput(input);

        assertEquals(expected, result);
    }

    @Test
    void shouldCorrectlyDivideInputWithBaseSeparator() {
        String input = "1,4 5\n";
        List<String> expected = List.of("1", ",", "4", " ", "5", "\n");

        List<String> result = parser.divideInput(input);

        assertEquals(expected, result);
    }

    @Test
    void shouldCorrectlyDivideInputWithCustomSeparator() {
        String input = "//sepq\n1sepq5,4;";
        List<String> expected = List.of("1", "sepq", "5", ",", "4", ";");

        List<String> result = parser.divideInput(input);

        assertEquals(expected, result);
    }
}
