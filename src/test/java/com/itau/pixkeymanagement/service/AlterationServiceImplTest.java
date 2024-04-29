package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.impl.AlterationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlterationServiceImplTest {

    @Mock
    private PixKeyManagementRepository pixKeyManagementRepository;

    @InjectMocks
    private AlterationServiceImpl alterationService;

    @Test
    public void testAlterKey() {
        // Given
        PixKeyAlterRequestDTO requestDTO = new PixKeyAlterRequestDTO();
        requestDTO.setId("123");
        requestDTO.setAccountType("Checking");
        requestDTO.setAgencyNumber(1234L);
        requestDTO.setAccountNumber(5678L);
        requestDTO.setAccountHolderName("John");
        requestDTO.setAccountHolderSurname("Doe");
        requestDTO.setAccountHolderType("Individual");

        PixKey pixKey = new PixKey();
        pixKey.setId("123");
        pixKey.setIsActive(true);

        when(pixKeyManagementRepository.getKeyById("123")).thenReturn(pixKey);
        when(pixKeyManagementRepository.updateKey(pixKey)).thenReturn(pixKey);

        // When
        HttpResponseStructure response = alterationService.alterKey(requestDTO);

        // Then
        assertEquals(HttpStatus.OK, response.getHttpCode());
    }

    @Test
    public void testAlterKeyNotFound() {
        // Given
        PixKeyAlterRequestDTO requestDTO = new PixKeyAlterRequestDTO();
        requestDTO.setId("456");

        when(pixKeyManagementRepository.getKeyById("456")).thenReturn(null);

        // When
        HttpResponseStructure response = alterationService.alterKey(requestDTO);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getHttpCode());
    }

    @Test
    public void testAlterKeyNotActive() {
        // Given
        PixKeyAlterRequestDTO requestDTO = new PixKeyAlterRequestDTO();
        requestDTO.setId("789");

        PixKey pixKey = new PixKey();
        pixKey.setId("789");
        pixKey.setIsActive(false);

        when(pixKeyManagementRepository.getKeyById("789")).thenReturn(pixKey);

        // When
        HttpResponseStructure response = alterationService.alterKey(requestDTO);

        // Then
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getHttpCode());
    }
}