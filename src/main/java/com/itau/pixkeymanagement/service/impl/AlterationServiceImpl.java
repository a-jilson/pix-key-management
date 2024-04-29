package com.itau.pixkeymanagement.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itau.pixkeymanagement.gsonadapter.LocalDateTimeAdapter;
import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.AlterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class AlterationServiceImpl implements AlterationService {

    @Autowired
    private PixKeyManagementRepository pixKeyManagementRepository;

    @Override
    public HttpResponseStructure alterKey(PixKeyAlterRequestDTO requestDTO) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        PixKey pixKey = pixKeyManagementRepository.getKeyById(requestDTO.getId());
        if (pixKey == null)
        {
            return new HttpResponseStructure(HttpStatus.NOT_FOUND, "PIX key id not found.");
        } else if (!pixKey.getIsActive())
        {
            return new HttpResponseStructure(HttpStatus.UNPROCESSABLE_ENTITY, "PIX key is not active");
        }

        pixKey.setAccountType(requestDTO.getAccountType());
        pixKey.setAgencyNumber(requestDTO.getAgencyNumber());
        pixKey.setAccountNumber(requestDTO.getAccountNumber());
        pixKey.setAccountHolderName(requestDTO.getAccountHolderName());
        pixKey.setAccountHolderSurname(requestDTO.getAccountHolderSurname());
        pixKey.setAccountHolderType(requestDTO.getAccountHolderType());

        pixKey = pixKeyManagementRepository.updateKey(pixKey);

        if (pixKey != null)
        {
            return new HttpResponseStructure(HttpStatus.OK, gson.toJson(pixKey));
        } else
        {
            return new HttpResponseStructure(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating Key");
        }

    }
}
