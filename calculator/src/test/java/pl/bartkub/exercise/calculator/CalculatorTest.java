package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void shouldThrowExceptionForNegativeNumber() {
        assertThrows(CalculationException.class,()->{
            int sum = calculator.add("//|\n1|2|-8");
        });
    }

    @Test
    public void shouldThrowExceptionWhenInputEndsWithDelimiter() {
        assertThrows(CalculationException.class,()->{
            int sum = calculator.add("//|\n1|2|8|");
        });
    }

    @Test
    public void shouldThrowAnExceptionForNotSpecifiedDelimiter() {
        assertThrows(CalculationException.class,()->{
            int sum = calculator.add("//|\n1|2,8");
        });
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
    public void shouldReturnZeroForBlankInput() {
        int sum = calculator.add("  ");

        assertEquals(0, sum);
    }

}
