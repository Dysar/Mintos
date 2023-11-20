package com.example.mintos.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {

    @Test
    public void testGetterSetter() {
        // Create an instance of Response with a generic type (e.g., String)
        Response<String> response = new Response<>();

        // Set values using setters
        response.setData("Test Data");
        response.setErrors("Test Error");

        // Verify values using getters
        assertEquals("Test Data", response.getData());
        assertEquals("Test Error", response.getErrors());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create an instance of Response with a generic type (e.g., Integer) using the no-args constructor
        Response<Integer> response = new Response<>();

        // Verify that the instance is not null
        assertNotNull(response);

        // Verify that the default values are set (assuming default values are meaningful)
        assertNull(response.getData());
        assertNull(response.getErrors());
    }

    @Test
    public void testAddErrorMsgToResponse() {
        // Create an instance of Response with a generic type (e.g., Double)
        Response<Double> response = new Response<>();

        // Add an error message to the response
        response.addErrorMsgToResponse("Test Error Message");

        // Verify that errors field is set with a ResponseError object
        assertNotNull(response.getErrors());
        assertTrue(response.getErrors() instanceof ResponseError);

        // Verify the details and timestamp of the ResponseError
        ResponseError error = (ResponseError) response.getErrors();
        assertEquals("Test Error Message", error.getDetails());
        assertNotNull(error.getTimestamp());
    }

    @Test
    public void testJsonIncludeNonNull() throws Exception {
        // Create an instance of Response with a generic type (e.g., Long)
        Response<Long> response = new Response<>();

        // Serialize the response to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(response);

        // Verify that null fields are excluded in the JSON representation
        assertFalse(json.contains("data"));
        assertFalse(json.contains("errors"));
    }

    // Add more test cases as needed
}
