package es.ucm.fdi.calculator.Calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorUnitTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void suma_isCorrectReal() {
        assertEquals(2.0 + 2.0, calculator.suma(2,2), 0.001);
    }

    @Test
    public void suma_isCorrectReal2() {
        assertEquals(4.0, calculator.suma(2,2), 0.001);
    }

    @Test
    public void suma_isCorrectDecimal() {
        assertEquals(2.5 + 2.0, calculator.suma(2.5,2), 0.001);
    }

    @Test
    public void suma_isCorrectDecimal2() {
        assertEquals(4.5, calculator.suma(2.5,2), 0.001);
    }
}
