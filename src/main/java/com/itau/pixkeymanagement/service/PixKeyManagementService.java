package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

public interface PixKeyManagementService {


    public HttpResponseStructure registerKey(PixKeyInsertRequestDTO requestDTO) throws MethodArgumentNotValidException;

    public HttpResponseStructure alterKey(PixKeyAlterRequestDTO requestDTO);

    public HttpResponseStructure deactivateKey(String id);

    public HttpResponseStructure getKey(String id, String keyType, Long accountNumber, Long agencyNumber, String accountHolderName, LocalDateTime insertionTime, LocalDateTime deactivationTime);
}
