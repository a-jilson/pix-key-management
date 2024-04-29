package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.impl.RegistrationServiceImpl;
import com.itau.pixkeymanagement.util.PixKeyValueValidatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegistrationServiceImplTest {

    @Mock
    private PixKeyManagementRepository pixKeyManagementRepository;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterKey_Success() throws MethodArgumentNotValidException {
        // Given
        PixKeyInsertRequestDTO requestDTO = new PixKeyInsertRequestDTO();
        requestDTO.setKeyType("email"); // Using "email" as the key type
        requestDTO.setKeyValue("test@example.com");
        requestDTO.setAccountNumber(123456L);
        requestDTO.setAgencyNumber(7890L);
        requestDTO.setAccountType("corrente"); // Using "corrente" as the account type
        requestDTO.setAccountHolderName("John");
        requestDTO.setAccountHolderSurname("Doe");
        requestDTO.setAccountHolderType("PJ");

        // Mock the behavior of the repository methods
        when(pixKeyManagementRepository.countRegisteredKeys(requestDTO.getAccountNumber(), requestDTO.getAgencyNumber())).thenReturn(0);
        when(pixKeyManagementRepository.keyExistByValue(requestDTO.getKeyValue())).thenReturn(false);

        // Mock the behavior of the registerKey method to return a predefined PixKey object
        PixKey registeredPixKey = new PixKey();
        registeredPixKey.setId(UUID.randomUUID().toString());
        when(pixKeyManagementRepository.registerKey(any(PixKey.class))).thenReturn(registeredPixKey);

        // When
        HttpResponseStructure response = registrationService.registerkey(requestDTO);

        // Then
        assertEquals(HttpStatus.OK, response.getHttpCode());
        assertEquals(registeredPixKey.getId(), response.getBodyMessage()); // Check if returned ID matches the registered PixKey's ID
        verify(pixKeyManagementRepository, times(1)).registerKey(any(PixKey.class));
    }

    @Test
    void testRegisterKey_MaximumKeysReached() throws MethodArgumentNotValidException {
        // Given
        PixKeyInsertRequestDTO requestDTO = new PixKeyInsertRequestDTO();
        requestDTO.setKeyType("email"); // Using a valid keyType
        requestDTO.setKeyValue("test@example.com");
        requestDTO.setAccountNumber(123456L);
        requestDTO.setAgencyNumber(7890L);
        requestDTO.setAccountType("CHECKING");
        requestDTO.setAccountHolderName("John");
        requestDTO.setAccountHolderSurname("Doe");
        requestDTO.setAccountHolderType("PJ");

        when(pixKeyManagementRepository.countRegisteredKeys(requestDTO.getAccountNumber(), requestDTO.getAgencyNumber())).thenReturn(20);

        // When
        HttpResponseStructure response = registrationService.registerkey(requestDTO);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
        assertEquals("Maximum number of registered keys reached for account.", response.getBodyMessage());
        verify(pixKeyManagementRepository, never()).registerKey(any());
    }

    @Test
    void testRegisterKey_KeyAlreadyExists() throws MethodArgumentNotValidException {
        // Given
        PixKeyInsertRequestDTO requestDTO = new PixKeyInsertRequestDTO();
        requestDTO.setKeyType("email"); // Using a valid keyType
        requestDTO.setKeyValue("test@example.com");
        requestDTO.setAccountNumber(123456L);
        requestDTO.setAgencyNumber(7890L);
        requestDTO.setAccountType("CHECKING");
        requestDTO.setAccountHolderName("John");
        requestDTO.setAccountHolderSurname("Doe");
        requestDTO.setAccountHolderType("PJ");

        when(pixKeyManagementRepository.countRegisteredKeys(requestDTO.getAccountNumber(), requestDTO.getAgencyNumber())).thenReturn(0);
        when(pixKeyManagementRepository.keyExistByValue(requestDTO.getKeyValue())).thenReturn(true);

        // When
        HttpResponseStructure response = registrationService.registerkey(requestDTO);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
        assertEquals("Key value already exist", response.getBodyMessage());
        verify(pixKeyManagementRepository, never()).registerKey(any());
    }
}
