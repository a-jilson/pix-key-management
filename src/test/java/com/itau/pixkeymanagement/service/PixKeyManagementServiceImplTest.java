package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.service.impl.PixKeyManagementServiceImpl;
import com.itau.pixkeymanagement.service.impl.RetrievalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PixKeyManagementServiceImplTest {

    @Mock
    private RegistrationService registrationService;

    @Mock
    private AlterationService alterationService;

    @Mock
    private DeactivationService deactivationService;

    @Mock
    private RetrievalServiceImpl retrievalService;

    @InjectMocks
    private PixKeyManagementServiceImpl pixKeyManagementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterKey() throws MethodArgumentNotValidException {
        // Given
        PixKeyInsertRequestDTO requestDTO = new PixKeyInsertRequestDTO();
        HttpResponseStructure expectedResponse = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(registrationService.registerkey(requestDTO)).thenReturn(expectedResponse);

        // When
        HttpResponseStructure response = pixKeyManagementService.registerKey(requestDTO);

        // Then
        assertEquals(expectedResponse, response);
    }

    @Test
    void testAlterKey() {
        // Given
        PixKeyAlterRequestDTO requestDTO = new PixKeyAlterRequestDTO();
        HttpResponseStructure expectedResponse = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(alterationService.alterKey(requestDTO)).thenReturn(expectedResponse);

        // When
        HttpResponseStructure response = pixKeyManagementService.alterKey(requestDTO);

        // Then
        assertEquals(expectedResponse, response);
    }

    @Test
    void testDeactivateKey() {
        // Given
        String id = "123";
        HttpResponseStructure expectedResponse = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(deactivationService.deactivateKey(id)).thenReturn(expectedResponse);

        // When
        HttpResponseStructure response = pixKeyManagementService.deactivateKey(id);

        // Then
        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetKey() {
        // Given
        String id = "123";
        String keyType = "EMAIL";
        Long accountNumber = 123456L;
        Long agencyNumber = 7890L;
        String accountHolderName = "John";
        LocalDateTime insertionTime = LocalDateTime.now();
        LocalDateTime deactivationTime = LocalDateTime.now();
        HttpResponseStructure expectedResponse = new HttpResponseStructure(HttpStatus.OK, "OK");
        when(retrievalService.getKey(id, keyType, accountNumber, agencyNumber, accountHolderName, insertionTime, deactivationTime)).thenReturn(expectedResponse);

        // When
        HttpResponseStructure response = pixKeyManagementService.getKey(id, keyType, accountNumber, agencyNumber, accountHolderName, insertionTime, deactivationTime);

        // Then
        assertEquals(expectedResponse, response);
    }
}