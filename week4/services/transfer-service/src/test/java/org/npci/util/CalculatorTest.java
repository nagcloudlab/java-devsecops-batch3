package org.npci.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

// Test cases for the Calculator class
public class CalculatorTest {


    // AAA process
    // Arrange: Set up the necessary objects and state
    // Act: Call the method under test
    // Assert: Verify the result

    Calculator calculator;

    @BeforeEach
    public void setUp() {
        // This method can be used to set up any common state before each test
        // Currently, it does nothing but can be extended if needed
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        // Act
        int result = calculator.add(2, 3);
        // Assert
        assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    public void testSubtract() {
        // Act
        int result = calculator.subtract(5, 3);
        // Assert
        assertEquals(2, result, "5 - 3 should equal 2");
    }

    @Test
    public void testMultiply() {
        // Act
        int result = calculator.multiply(4, 5);
        // Assert
        assertEquals(20, result, "4 * 5 should equal 20");
    }

    @Test
    public void testDivide() {
        // Act
        double result = calculator.divide(10, 2);
        // Assert
        assertEquals(5.0, result, "10 / 2 should equal 5.0");
    }

    @Test
    public void testDivideByZero() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }

    // Data-driven tests can be added here if needed
    // For example, using @ParameterizedTest for multiple inputs and expected results

    @ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({
            "1, 2, 3",
            "4, 5, 9",
            "10, 20, 30"
    })
    public void testAddWithMultipleInputs(int a, int b, int expected) {
        // Act
        int result = calculator.add(a, b);
        // Assert
        assertEquals(expected, result, a + " + " + b + " should equal " + expected);
    }


}
