package com.itau.pixkeymanagement.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itau.pixkeymanagement.gsonadapter.LocalDateTimeAdapter;
import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.RegistrationService;
import com.itau.pixkeymanagement.util.PixKeyValueValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private PixKeyManagementRepository pixKeyManagementRepository;

    public static final Integer MAX_KEY_PJ = 20;
    public static final Integer MAX_KEY_PF = 5;
    public static final String ACCOUNT_TYPE_PJ = "PJ";
    public static final String ACCOUNT_TYPE_PF = "PF";

    @Override
    public HttpResponseStructure registerkey(PixKeyInsertRequestDTO requestDTO) throws MethodArgumentNotValidException {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        // validate the key value
        PixKeyValueValidatorUtil.validateKeyValue(requestDTO.getKeyType(), requestDTO.getKeyValue());
        // check if client's number of registeredKey is less than 5 or 20 depending on client type
        int registeredKeys = pixKeyManagementRepository.
                                    countRegisteredKeys(requestDTO.getAccountNumber(), requestDTO.getAgencyNumber());
        if ((ACCOUNT_TYPE_PJ.equals(requestDTO.getAccountHolderType()) && registeredKeys >= MAX_KEY_PJ)
            || (ACCOUNT_TYPE_PF.equals(requestDTO.getAccountHolderType()) && registeredKeys >= MAX_KEY_PF))
        {
            return new HttpResponseStructure(HttpStatus.UNPROCESSABLE_ENTITY, "Maximum number of registered keys reached for account.");
        }

        // check if key already exist
        Boolean doesKeyExist = pixKeyManagementRepository.keyExistByValue(requestDTO.getKeyValue());
        if (doesKeyExist)
        {
            return new HttpResponseStructure(HttpStatus.UNPROCESSABLE_ENTITY, "Key value already exist");
        }

        // map request  to PixKey
        PixKey pixKey = getPixKey(requestDTO);
        pixKey.setId(UUID.randomUUID().toString());
        pixKey.setIsActive(true);
        pixKey.setInsertionTime(LocalDateTime.now());

        pixKey = pixKeyManagementRepository.registerKey(pixKey);
        if (pixKey != null)
        {
            pixKey.setIsActive(null);
            pixKey.setDeactivationTime(null);
            pixKey.setInsertionTime(null);
            return new HttpResponseStructure(HttpStatus.OK, pixKey.getId());
        } else
        {
            return new HttpResponseStructure(HttpStatus.INTERNAL_SERVER_ERROR, "Error inserting Key");
        }
    }

    private static PixKey getPixKey(PixKeyInsertRequestDTO requestDTO) {
        PixKey pixKey = new PixKey();
        pixKey.setKeyType(requestDTO.getKeyType());
        pixKey.setKeyValue(requestDTO.getKeyValue());
        pixKey.setAccountNumber(requestDTO.getAccountNumber());
        pixKey.setAgencyNumber(requestDTO.getAgencyNumber());
        pixKey.setAccountType(requestDTO.getAccountType());
        pixKey.setAccountHolderName(requestDTO.getAccountHolderName());
        pixKey.setAccountHolderSurname(requestDTO.getAccountHolderSurname());
        pixKey.setAccountHolderType(requestDTO.getAccountHolderType());
        return pixKey;
    }
}
