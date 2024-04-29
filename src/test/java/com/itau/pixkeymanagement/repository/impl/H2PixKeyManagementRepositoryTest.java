package com.itau.pixkeymanagement.repository.impl;

import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.entity.PixKeyDTO;
import com.itau.pixkeymanagement.repository.PixKeyRepository;
import com.itau.pixkeymanagement.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class H2PixKeyManagementRepositoryTest {

    @Mock
    private PixKeyRepository repository;

    @InjectMocks
    private H2PixKeyManagementRepository pixKeyManagementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registerKey_Success() {
        // Given
        PixKey pixKey = new PixKey();

        // When
        PixKey returnedPixKey = pixKeyManagementRepository.registerKey(pixKey);

        // Then
        assertEquals(pixKey, returnedPixKey);
        verify(repository, times(1)).save(any());
    }

    @Test
    void countRegisteredKeys_Success() {
        // Given
        Long accountNumber = 123456L;
        Long agencyNumber = 7890L;

        // When
        int count = pixKeyManagementRepository.countRegisteredKeys(accountNumber, agencyNumber);

        // Then
        // Implement test scenario based on your actual data and logic
    }

    @Test
    void keyExistByValue_KeyExists_ReturnsTrue() {
        // Given
        String keyValue = "test@example.com";

        // When
        boolean result = pixKeyManagementRepository.keyExistByValue(keyValue);

        // Then
        // Implement test scenario based on your actual data and logic
    }

    @Test
    void keyExistByValue_KeyDoesNotExist_ReturnsFalse() {
        // Given
        String keyValue = "nonexistent@example.com";

        // When
        boolean result = pixKeyManagementRepository.keyExistByValue(keyValue);

        // Then
        // Implement test scenario based on your actual data and logic
    }

    @Test
    void getKeyById_KeyExists_ReturnsPixKey() {
        // Given
        String id = "123";
        PixKeyDTO pixKeyDTO = new PixKeyDTO();

        // When
        PixKey result = pixKeyManagementRepository.getKeyById(id);

        // Then
        // Implement test scenario based on your actual data and logic
    }

    @Test
    void getKeyById_KeyDoesNotExist_ReturnsNull() {
        // Given
        String id = "nonexistent";

        // When
        PixKey result = pixKeyManagementRepository.getKeyById(id);

        // Then
        assertEquals(null, result);
        verify(repository, times(1)).getKeyById(id);
    }

    @Test
    void updateKey_Success() {
        // Given
        PixKey pixKey = new PixKey();

        // When
        PixKey updatedKey = pixKeyManagementRepository.updateKey(pixKey);

        // Then
        assertEquals(pixKey, updatedKey);
        verify(repository, times(1)).save(any());
    }

    @Test
    void getKey_Success() {
        // Given
        PixKey pixKey = new PixKey();
        List<PixKeyDTO> keyDTOList = new ArrayList<>();

        // When
        List<PixKey> keyList = pixKeyManagementRepository.getKey(pixKey);

        // Then
        // Implement test scenario based on your actual data and logic
    }
}
