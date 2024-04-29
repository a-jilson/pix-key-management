package com.itau.pixkeymanagement.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itau.pixkeymanagement.gsonadapter.LocalDateTimeAdapter;
import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.service.RetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RetrievalServiceImpl implements RetrievalService {

    @Autowired
    private PixKeyManagementRepository pixKeyManagementRepository;

    @Override
    public HttpResponseStructure getKey(String id, String keyType, Long accountNumber,
                                        Long agencyNumber, String accountHolderName, LocalDateTime insertionTime,
                                        LocalDateTime deactivationTime) {


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        if (id != null && (keyType != null || accountNumber != null || agencyNumber != null || accountHolderName != null || deactivationTime != null || insertionTime != null))
        {
            return new HttpResponseStructure(HttpStatus.UNPROCESSABLE_ENTITY, "If present, key id should be the only filter");
        }
        if (deactivationTime != null && insertionTime != null)
        {
            return new HttpResponseStructure(HttpStatus.UNPROCESSABLE_ENTITY, "You have to choose between insertion and deactivation time");
        }


        PixKey pixKey = null;
        if (id != null)
        {
            pixKey = pixKeyManagementRepository.getKeyById(id);
            if (pixKey == null)
            {
                pixKey.setIsActive(null);
                return new HttpResponseStructure(HttpStatus.NOT_FOUND, "PIX key not found.");
            }

            return new HttpResponseStructure(HttpStatus.OK,  gson.toJson(pixKey));
        }

        List<PixKey> listKey;
        PixKey pixKeyRequest = new PixKey();
        pixKeyRequest.setKeyType(keyType);
        pixKeyRequest.setAgencyNumber(agencyNumber);
        pixKeyRequest.setAccountNumber(accountNumber);
        pixKeyRequest.setAccountHolderName(accountHolderName);
        pixKeyRequest.setInsertionTime(insertionTime);
        pixKeyRequest.setDeactivationTime(deactivationTime);
        listKey = pixKeyManagementRepository.getKey(pixKeyRequest);



        if (listKey == null || listKey.isEmpty())
        {
            return new HttpResponseStructure(HttpStatus.NOT_FOUND, "PIX key not found.");
        }
        return new HttpResponseStructure(HttpStatus.OK, gson.toJson(listKey));

    }

}
