package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface RegistrationService {

    HttpResponseStructure registerkey(PixKeyInsertRequestDTO requestDTO) throws MethodArgumentNotValidException;
}
