package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void shouldThrowExceptionForNegativeNumberAndWrongDelimiters() {
        CalculationException exception = assertThrows(CalculationException.class, () -> {
            calculator.add("//|\n1|2,-8|-10");
        });

        assertTrue(exception.getMessage().contains("Negative number(s) not allowed: -8, -10\n " +
                "Found invalid delimiter(s): , at pos 3"));
    }


    @Test
    public void shouldThrowExceptionForNegativeNumber() {
        CalculationException exception = assertThrows(CalculationException.class, () ->
                calculator.add("//|\n1|2|-8|-10")
        );

        assertTrue(exception.getMessage().contains("Negative number(s) not allowed: -8, -10"));
    }

    @Test
    public void shouldThrowExceptionWhenInputEndsWithDelimiter() {
        CalculationException exception = assertThrows(CalculationException.class, () ->
                calculator.add("//|\n1|2|8|")
        );

        String expectedMessage = "Found invalid delimiter(s): | at pos 5";

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldThrowAnExceptionForNotSpecifiedDelimiter() {
        assertThrows(CalculationException.class, () -> calculator.add("//|\n1|2,8"));
    }

    @Test
    public void shouldCorrectlySumValuesForUniqueDelimiterWithNumbers() {
        int sum = calculator.add("//kk88s\n1kk88s2kk88s8");

        assertEquals(11, sum);
    }

    @Test
    public void shouldCorrectlySumValuesForUniqueDelimiter() {
        int sum = calculator.add("//|\n1|2|8");

        assertEquals(11, sum);
    }

    @Test
    public void shouldCorrectlyIgnoreBigNumberDuringSumOperation() {
        int sum = calculator.add("1,2,1001");

        assertEquals(3, sum);
    }

    @Test
    public void shouldCorrectlySumThreeNumbersWithSpaceAsDelimiter() {
        int sum = calculator.add("1,2\n4");

        assertEquals(7, sum);
    }

    @Test
    public void shouldCorrectlySumTwoNumbersWithCommaAsDelimiter() {
        int sum = calculator.add("1,2");

        assertEquals(3, sum);
    }

    @Test
    public void shouldCorrectlyReturnSingleNumber() {
        int sum = calculator.add("999");

        assertEquals(999, sum);
    }


    @Test
    public void shouldReturnZeroForBlankInput() {
        int sum = calculator.add("  ");

        assertEquals(0, sum);
    }
}
