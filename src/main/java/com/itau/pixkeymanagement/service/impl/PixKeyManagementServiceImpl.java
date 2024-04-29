package com.itau.pixkeymanagement.service.impl;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;


@Service
public class PixKeyManagementServiceImpl implements PixKeyManagementService {

    private final RegistrationService registrationService;
    private final AlterationService alterationService;
    private final DeactivationService deactivationService;
    private final RetrievalServiceImpl retrievalService;

    @Autowired
    public PixKeyManagementServiceImpl(RegistrationService registrationService, AlterationService alterationService,
                                       DeactivationService deactivationService, RetrievalServiceImpl retrievalService )
    {
        this.registrationService = registrationService;
        this.alterationService = alterationService;
        this.deactivationService = deactivationService;
        this.retrievalService = retrievalService;
    }

    @Override
    public HttpResponseStructure registerKey(PixKeyInsertRequestDTO requestDTO) throws MethodArgumentNotValidException {
        return registrationService.registerkey(requestDTO);
    }

    @Override
    public HttpResponseStructure alterKey(PixKeyAlterRequestDTO requestDTO) {
        return alterationService.alterKey(requestDTO);
    }

    @Override
    public HttpResponseStructure deactivateKey(String id) {
        return deactivationService.deactivateKey(id);
    }

    @Override
    public HttpResponseStructure getKey(String id, String keyType, Long accountNumber,
                                        Long agencyNumber, String accountHolderName, LocalDateTime insertionTime,
                                        LocalDateTime deactivationTime) {

        return retrievalService.getKey(id, keyType, accountNumber, agencyNumber, accountHolderName, insertionTime, deactivationTime);
    }
}