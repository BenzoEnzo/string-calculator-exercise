package pl.bartkub.exercise.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    public void shouldReturnZeroForBlankInput(){
        int sum = calculator.add("  ");

        assertEquals(sum, 0);
    }

}
