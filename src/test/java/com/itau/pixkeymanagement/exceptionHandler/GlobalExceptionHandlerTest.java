package com.itau.pixkeymanagement.exceptionHandler;

import com.itau.pixkeymanagement.exceptionhandler.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @Test
    void handleValidationErrors_ReturnsErrorResponse() {
        // Given
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("fieldName", "errorCode", "Error message 1"));
        fieldErrors.add(new FieldError("fieldName", "errorCode", "Error message 2"));

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // When
        ResponseEntity<Map<String, List<String>>> responseEntity = exceptionHandler.handleValidationErrors(exception);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        assertEquals(new HttpHeaders(), responseEntity.getHeaders());
        assertEquals(2, responseEntity.getBody().get("errors").size());
        assertEquals("Error message 1", responseEntity.getBody().get("errors").get(0));
        assertEquals("Error message 2", responseEntity.getBody().get("errors").get(1));
    }
}
