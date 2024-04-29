package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;

public interface DeactivationService {

    HttpResponseStructure deactivateKey(String keyId);
}
