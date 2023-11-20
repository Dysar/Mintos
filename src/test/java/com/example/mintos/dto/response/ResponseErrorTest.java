package com.example.mintos.dto.response;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseErrorTest {

    @Test
    public void testGetterSetter() {
        // Create an instance of ResponseError
        ResponseError responseError = new ResponseError();

        // Set values using setters
        responseError.setTimestamp(LocalDateTime.now());
        responseError.setDetails("Test Details");

        // Verify values using getters
        assertNotNull(responseError.getTimestamp());
        assertEquals("Test Details", responseError.getDetails());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create an instance of ResponseError using the no-args constructor
        ResponseError responseError = new ResponseError();

        // Verify that the instance is not null
        assertNotNull(responseError);

        // Verify that the default values are set (assuming default values are meaningful)
        assertNull(responseError.getTimestamp());
        assertNull(responseError.getDetails());
    }

    @Test
    public void testValidationConstraints() {
        // Create an instance of ResponseError
        ResponseError responseError = new ResponseError();

        // Create a validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validate the instance
        Set<ConstraintViolation<ResponseError>> violations = validator.validate(responseError);

        // Verify that there are violations for the 'timestamp' and 'details' fields
        assertEquals(2, violations.size());

        for (ConstraintViolation<ResponseError> violation : violations) {
            assertTrue(violation.getMessage().contains("cannot be null"));
        }
    }

    @Test
    public void testValidationSuccess() {
        // Create an instance of ResponseError with valid values
        ResponseError responseError = new ResponseError()
                .setTimestamp(LocalDateTime.now())
                .setDetails("Valid Details");

        // Create a validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validate the instance
        Set<ConstraintViolation<ResponseError>> violations = validator.validate(responseError);

        // Verify that there are no violations
        assertTrue(violations.isEmpty());
    }

    // Add more test cases as needed
}
