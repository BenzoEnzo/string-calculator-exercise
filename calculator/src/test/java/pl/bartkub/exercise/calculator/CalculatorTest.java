package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

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
    public void shouldReturnZeroForBlankInput() {
        int sum = calculator.add("  ");

        assertEquals(0, sum);
    }

}
