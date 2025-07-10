package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

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
}
