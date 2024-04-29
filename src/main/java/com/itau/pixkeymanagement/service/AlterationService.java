package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;

public interface AlterationService {

    HttpResponseStructure alterKey(PixKeyAlterRequestDTO requestDTO);
}
