package com.itau.pixkeymanagement.util;

import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.entity.PixKeyDTO;

import java.time.LocalDateTime;

public class MapperUtil {

    public static PixKeyDTO mapToPixKeyDTO(PixKey pixKey) {
        PixKeyDTO pixKeyDTO = new PixKeyDTO();
        pixKeyDTO.setId(pixKey.getId());
        pixKeyDTO.setKeyType(pixKey.getKeyType());
        pixKeyDTO.setKeyValue(pixKey.getKeyValue());
        pixKeyDTO.setAccountType(pixKey.getAccountType());
        pixKeyDTO.setAgencyNumber(pixKey.getAgencyNumber());
        pixKeyDTO.setAccountNumber(pixKey.getAccountNumber());
        pixKeyDTO.setAccountHolderName(pixKey.getAccountHolderName());
        pixKeyDTO.setAccountHolderSurname(pixKey.getAccountHolderSurname());
        pixKeyDTO.setAccountHolderType(pixKey.getAccountHolderType());
        pixKeyDTO.setInsertionTime(pixKey.getInsertionTime());
        pixKeyDTO.setDeactivationTime(pixKey.getDeactivationTime());
        pixKeyDTO.setIsActive(pixKey.getIsActive());
        return pixKeyDTO;
    }

    public static PixKey mapToPixKey(PixKeyDTO pixKeyDTO) {
        PixKey pixKey = new PixKey();
        pixKey.setId(pixKeyDTO.getId());
        pixKey.setKeyType(pixKeyDTO.getKeyType());
        pixKey.setKeyValue(pixKeyDTO.getKeyValue());
        pixKey.setAccountType(pixKeyDTO.getAccountType());
        pixKey.setAgencyNumber(pixKeyDTO.getAgencyNumber());
        pixKey.setAccountNumber(pixKeyDTO.getAccountNumber());
        pixKey.setAccountHolderName(pixKeyDTO.getAccountHolderName());
        pixKey.setAccountHolderSurname(pixKeyDTO.getAccountHolderSurname());
        pixKey.setAccountHolderType(pixKeyDTO.getAccountHolderType());
        pixKey.setInsertionTime(pixKeyDTO.getInsertionTime());
        pixKey.setDeactivationTime(pixKeyDTO.getDeactivationTime());
        pixKey.setIsActive(pixKeyDTO.getIsActive());
        return pixKey;
    }
}
