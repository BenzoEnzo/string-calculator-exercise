package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private final Parser parser = new Parser();

    @Test
    public void shouldCorrectlyExtractRawBaseDelimiter() {
        String delimiter = parser.extractRawDelimiter(",");

        assertEquals(",", delimiter);
    }


    @Test
    public void shouldCorrectlyReturnRawDelimiter() {
        String delimiter = parser.extractRawDelimiter("\\Q|\\E");

        assertEquals("|", delimiter);
    }


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
        String[] expected = new String[]{"1", ",", "-44"};

        String[] result = parser.divideInput(input, "[,\n]");;

        assertArrayEquals(expected, result);
    }

    @Test
    void shouldCorrectlyDivideInputWithBaseSeparator() {
        String input = "1,4 5\n";
        String[] expected = new String[]{"1", ",", "4", " ", "5", "\n"};

        String[] result = parser.divideInput(input, "[,\n]");

        assertArrayEquals(expected, result);
    }

    @Test
    void shouldCorrectlyDivideInputWithCustomSeparator() {
        String input = "//sepq\n1sepq5,4;";
        String[] expected = new String[]{"1", "sepq", "5", ",", "4", ";"};

        String[] result = parser.divideInput(input, "\\Qsepq\\E");

        assertArrayEquals(expected, result);
    }
}
