package com.shubham.productcatalogservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    // test_when_then
    @Test
    public void Test_WhenTwoIntegersAreAdded_RunsSuccessfully(){
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(10,20);

        //Assert
        assert (result == 30);
        assertEquals(result, 30, "The result of addition is not 30");
    }

    @Test
    public void Test_DivideByZero_ResultsInArithmeticException(){
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
//        int result = calculator.divide(10, 0);
        assertThrows(ArithmeticException.class, ()-> calculator.divide(10, 0));
    }
}
