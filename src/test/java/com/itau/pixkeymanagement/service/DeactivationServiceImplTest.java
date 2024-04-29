package com.itau.pixkeymanagement.service;
import com.google.gson.Gson;
import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.impl.DeactivationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeactivationServiceImplTest {

    @Mock
    private PixKeyManagementRepository pixKeyManagementRepository;

    @InjectMocks
    private DeactivationServiceImpl deactivationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testDeactivateKey_KeyNotFound() {
        // Given
        String keyId = "456";

        when(pixKeyManagementRepository.getKeyById(keyId)).thenReturn(null);

        // When
        HttpResponseStructure response = deactivationService.deactivateKey(keyId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpCode());
        assertEquals("PIX key id not found.", response.getBodyMessage());
        verify(pixKeyManagementRepository, never()).updateKey(any());
    }

    @Test
    public void testDeactivateKey_KeyAlreadyInactive() {
        // Given
        String keyId = "789";
        PixKey pixKey = new PixKey();
        pixKey.setId(keyId);
        pixKey.setIsActive(false);

        when(pixKeyManagementRepository.getKeyById(keyId)).thenReturn(pixKey);

        // When
        HttpResponseStructure response = deactivationService.deactivateKey(keyId);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
        assertEquals("PIX key is already inactive", response.getBodyMessage());
        verify(pixKeyManagementRepository, never()).updateKey(any());
    }

    @Test
    public void testDeactivateKey_InternalServerError() {
        // Given
        String keyId = "123";
        PixKey pixKey = new PixKey();
        pixKey.setId(keyId);
        pixKey.setIsActive(true);

        when(pixKeyManagementRepository.getKeyById(keyId)).thenReturn(pixKey);
        when(pixKeyManagementRepository.updateKey(pixKey)).thenReturn(null);

        // When
        HttpResponseStructure response = deactivationService.deactivateKey(keyId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getHttpCode());
        assertEquals("Error deactivating Key", response.getBodyMessage());
        verify(pixKeyManagementRepository, times(1)).updateKey(pixKey);
    }
}