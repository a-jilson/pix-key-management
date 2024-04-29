package com.itau.pixkeymanagement.repository;


import com.itau.pixkeymanagement.model.PixKey;

import java.util.List;

public interface PixKeyManagementRepository {

    PixKey registerKey(PixKey pixKey);

    int countRegisteredKeys(Long accountNumber, Long agencyNumber);

    Boolean keyExistByValue(String keyValue);

    PixKey getKeyById(String id);

    PixKey updateKey(PixKey pixKey);

    List<PixKey> getKey(PixKey pixKey);
}
