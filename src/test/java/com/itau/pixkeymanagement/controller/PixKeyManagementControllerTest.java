package com.itau.pixkeymanagement.controller;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.service.PixKeyManagementService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PixKeyManagementControllerTest {

    @Mock
    private PixKeyManagementService pixKeyManagementService;

    @InjectMocks
    private PixKeyManagementController pixKeyManagementController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerKey_Success() throws MethodArgumentNotValidException {
        // Given
        PixKeyInsertRequestDTO requestDTO = new PixKeyInsertRequestDTO();
        HttpResponseStructure response = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(pixKeyManagementService.registerKey(requestDTO)).thenReturn(response);

        // When
        ResponseEntity<String> result = pixKeyManagementController.registerKey(requestDTO);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", result.getBody());
    }

    @Test
    void registerKey_ValidationException() throws MethodArgumentNotValidException {
        // Given
        PixKeyInsertRequestDTO requestDTO = new PixKeyInsertRequestDTO();
        when(pixKeyManagementService.registerKey(requestDTO)).thenThrow(new ValidationException());

        // When
        assertThrows(ValidationException.class, () -> pixKeyManagementController.registerKey(requestDTO));
    }

    @Test
    void alterKey_Success() {
        // Given
        PixKeyAlterRequestDTO requestDTO = new PixKeyAlterRequestDTO();
        HttpResponseStructure response = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(pixKeyManagementService.alterKey(requestDTO)).thenReturn(response);

        // When
        ResponseEntity<String> result = pixKeyManagementController.alterKey(requestDTO);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", result.getBody());
    }

    @Test
    void deactivateKey_Success() {
        // Given
        String id = "123";
        HttpResponseStructure response = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(pixKeyManagementService.deactivateKey(id)).thenReturn(response);

        // When
        ResponseEntity<String> result = pixKeyManagementController.deactivateKey(id);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", result.getBody());
    }

    @Test
    void getKey_Success() {
        // Given
        String id = "123";
        HttpResponseStructure response = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(pixKeyManagementService.getKey(eq(id), any(), any(), any(), any(), any(), any())).thenReturn(response);

        // When
        ResponseEntity<String> result = pixKeyManagementController.getKey(id, null, null, null, null, null, null);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("OK", result.getBody());
    }
}
