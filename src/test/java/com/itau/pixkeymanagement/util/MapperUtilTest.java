package com.itau.pixkeymanagement.util;

import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.entity.PixKeyDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperUtilTest {

    @Test
    void mapToPixKeyDTO_ShouldMapCorrectly() {
        // Given
        PixKey pixKey = new PixKey();
        pixKey.setId("1");
        pixKey.setKeyType("email");
        pixKey.setKeyValue("example@example.com");
        pixKey.setAccountType("savings");
        pixKey.setAgencyNumber(123L);
        pixKey.setAccountNumber(456L);
        pixKey.setAccountHolderName("John");
        pixKey.setAccountHolderSurname("Doe");
        pixKey.setAccountHolderType("individual");
        pixKey.setInsertionTime(LocalDateTime.now());
        pixKey.setDeactivationTime(LocalDateTime.now().plusDays(1));
        pixKey.setIsActive(true);

        // When
        PixKeyDTO pixKeyDTO = MapperUtil.mapToPixKeyDTO(pixKey);

        // Then
        assertEquals(pixKey.getId(), pixKeyDTO.getId());
        assertEquals(pixKey.getKeyType(), pixKeyDTO.getKeyType());
        assertEquals(pixKey.getKeyValue(), pixKeyDTO.getKeyValue());
        assertEquals(pixKey.getAccountType(), pixKeyDTO.getAccountType());
        assertEquals(pixKey.getAgencyNumber(), pixKeyDTO.getAgencyNumber());
        assertEquals(pixKey.getAccountNumber(), pixKeyDTO.getAccountNumber());
        assertEquals(pixKey.getAccountHolderName(), pixKeyDTO.getAccountHolderName());
        assertEquals(pixKey.getAccountHolderSurname(), pixKeyDTO.getAccountHolderSurname());
        assertEquals(pixKey.getAccountHolderType(), pixKeyDTO.getAccountHolderType());
        assertEquals(pixKey.getInsertionTime(), pixKeyDTO.getInsertionTime());
        assertEquals(pixKey.getDeactivationTime(), pixKeyDTO.getDeactivationTime());
        assertEquals(pixKey.getIsActive(), pixKeyDTO.getIsActive());
    }

    @Test
    void mapToPixKey_ShouldMapCorrectly() {
        // Given
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId("1");
        pixKeyDTO.setKeyType("email");
        pixKeyDTO.setKeyValue("example@example.com");
        pixKeyDTO.setAccountType("savings");
        pixKeyDTO.setAgencyNumber(123L);
        pixKeyDTO.setAccountNumber(456L);
        pixKeyDTO.setAccountHolderName("John");
        pixKeyDTO.setAccountHolderSurname("Doe");
        pixKeyDTO.setAccountHolderType("individual");
        pixKeyDTO.setInsertionTime(LocalDateTime.now());
        pixKeyDTO.setDeactivationTime(LocalDateTime.now().plusDays(1));
        pixKeyDTO.setIsActive(true);

        // When
        PixKey pixKey = MapperUtil.mapToPixKey(pixKeyDTO);

        // Then
        assertEquals(pixKeyDTO.getId(), pixKey.getId());
        assertEquals(pixKeyDTO.getKeyType(), pixKey.getKeyType());
        assertEquals(pixKeyDTO.getKeyValue(), pixKey.getKeyValue());
        assertEquals(pixKeyDTO.getAccountType(), pixKey.getAccountType());
        assertEquals(pixKeyDTO.getAgencyNumber(), pixKey.getAgencyNumber());
        assertEquals(pixKeyDTO.getAccountNumber(), pixKey.getAccountNumber());
        assertEquals(pixKeyDTO.getAccountHolderName(), pixKey.getAccountHolderName());
        assertEquals(pixKeyDTO.getAccountHolderSurname(), pixKey.getAccountHolderSurname());
        assertEquals(pixKeyDTO.getAccountHolderType(), pixKey.getAccountHolderType());
        assertEquals(pixKeyDTO.getInsertionTime(), pixKey.getInsertionTime());
        assertEquals(pixKeyDTO.getDeactivationTime(), pixKey.getDeactivationTime());
        assertEquals(pixKeyDTO.getIsActive(), pixKey.getIsActive());
    }
}