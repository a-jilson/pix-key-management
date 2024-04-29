package com.itau.pixkeymanagement.service;

import com.itau.pixkeymanagement.model.HttpResponseStructure;

import java.time.LocalDateTime;

public interface RetrievalService {

    HttpResponseStructure getKey(String id, String keyType, Long accountNumber,
                                 Long agencyNumber, String accountHolderName, LocalDateTime insertionTime,
                                 LocalDateTime deactivationTime);
}
