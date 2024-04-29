package com.itau.pixkeymanagement.service;
import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.impl.RetrievalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RetrievalServiceImplTest {

    @Mock
    private PixKeyManagementRepository pixKeyManagementRepository;

    @InjectMocks
    private RetrievalServiceImpl retrievalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetKeyById() {
        // Given
        String id = "123";
        PixKey pixKey = new PixKey();
        pixKey.setId(id);
        when(pixKeyManagementRepository.getKeyById(id)).thenReturn(pixKey);

        // When
        HttpResponseStructure response = retrievalService.getKey(id, null, null, null, null, null, null);

        // Then
        assertEquals(HttpStatus.OK, response.getHttpCode());
        assertEquals("{\"id\":\"123\"}", response.getBodyMessage());
    }


    @Test
    public void testGetKeyWithOtherFilters() {
        // Given
        String id = null;
        LocalDateTime insertionTime = LocalDateTime.now();
        LocalDateTime deactivationTime = null;

        // When
        HttpResponseStructure response = retrievalService.getKey(id, "email", 123L, 456L, "John Doe", insertionTime, deactivationTime);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpCode());
        assertEquals("PIX key not found.", response.getBodyMessage());
    }

    @Test
    public void testGetKeyWithInvalidIdAndOtherFilters() {
        // Given
        String id = "123";
        when(pixKeyManagementRepository.getKeyById(id)).thenReturn(null);

        // When
        HttpResponseStructure response = retrievalService.getKey(id, "email", 123L, 456L, "John Doe", LocalDateTime.now(), null);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
        assertEquals("If present, key id should be the only filter", response.getBodyMessage());
    }

    @Test
    public void testGetKeyWithIdAndOtherFilters() {
        // Given
        String id = "123";
        PixKey pixKey = new PixKey();
        pixKey.setId(id);
        when(pixKeyManagementRepository.getKeyById(id)).thenReturn(pixKey);

        // When
        HttpResponseStructure response = retrievalService.getKey(id, "email", 123L, 456L, "John Doe", LocalDateTime.now(), LocalDateTime.now());

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
        assertEquals("If present, key id should be the only filter", response.getBodyMessage());
    }

    @Test
    public void testGetKeyWithInsertionAndDeactivationTime() {
        // When
        HttpResponseStructure response = retrievalService.getKey(null, null, null, null, null, LocalDateTime.now(), LocalDateTime.now());

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
        assertEquals("You have to choose between insertion and deactivation time", response.getBodyMessage());
    }

    @Test
    public void testGetKeyWithValidFilters() {
        // Given
        PixKey pixKey = new PixKey();
        when(pixKeyManagementRepository.getKey(any())).thenReturn(Arrays.asList(pixKey));

        // When
        HttpResponseStructure response = retrievalService.getKey(null, "email", 123L, 456L, "John Doe", null, null);

        // Then
        assertEquals(HttpStatus.OK, response.getHttpCode());
        assertEquals("[{}]", response.getBodyMessage());
    }
}