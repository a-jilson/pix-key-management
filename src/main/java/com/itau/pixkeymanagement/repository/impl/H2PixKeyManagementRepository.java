package com.itau.pixkeymanagement.repository.impl;

import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.PixKey;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.model.entity.PixKeyDTO;
import com.itau.pixkeymanagement.repository.PixKeyManagementRepository;
import com.itau.pixkeymanagement.repository.PixKeyRepository;
import com.itau.pixkeymanagement.service.PixKeyManagementService;
import com.itau.pixkeymanagement.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class H2PixKeyManagementRepository implements PixKeyManagementRepository {

    @Autowired
    private PixKeyRepository repository;

    @Override
    public PixKey registerKey(PixKey pixKey) {

        PixKeyDTO pixKeyDto = MapperUtil.mapToPixKeyDTO(pixKey);
        repository.save(pixKeyDto);
        return pixKey;
    }

    @Override
    public int countRegisteredKeys(Long accountNumber, Long agencyNumber) {
       return repository.countByAccountNumberAndAgencyNumber(accountNumber, agencyNumber);
    }

    @Override
    public Boolean keyExistByValue(String keyValue) {
        return repository.existsByKeyValue(keyValue);
    }

    @Override
    public PixKey getKeyById(String id) {

        PixKeyDTO pixKey = repository.getKeyById(id);
        if (pixKey == null) {
            return null;
        }
        return MapperUtil.mapToPixKey(pixKey);
    }

    @Override
    public PixKey updateKey(PixKey pixKey) {
        PixKeyDTO pixKeyDto = MapperUtil.mapToPixKeyDTO(pixKey);
        repository.save(pixKeyDto);
        return pixKey;
    }

    @Override
    public List<PixKey> getKey(PixKey pixKey) {

        List<PixKeyDTO> keyList = new ArrayList<>();
        if (pixKey.getAccountNumber() != null)
        {
            PixKeyDTO keyDTO = repository.findByAccountNumber(pixKey.getAccountNumber());
            keyList.add(keyDTO);

        } else if (pixKey.getAgencyNumber() != null)
        {
            PixKeyDTO keyDTO = repository.findByAgencyNumber(pixKey.getAgencyNumber());
            keyList.add(keyDTO);
        } else if (pixKey.getAccountHolderName() != null)
        {
            PixKeyDTO keyDTO = repository.findByAccountHolderName(pixKey.getAccountHolderName());
            keyList.add(keyDTO);
        }else if (pixKey.getKeyType() != null)
        {
            PixKeyDTO keyDTO = repository.findByKeyType(pixKey.getKeyType());
            keyList.add(keyDTO);
        }else if (pixKey.getInsertionTime() != null)
        {
            PixKeyDTO keyDTO = repository.findByInsertionTime(pixKey.getInsertionTime());
            keyList.add(keyDTO);

        }else if (pixKey.getDeactivationTime() != null)
        {
            PixKeyDTO keyDTO = repository.findByDeactivationTime(pixKey.getDeactivationTime());
            keyList.add(keyDTO);
        }

        List<PixKey> returnList = new ArrayList<>();

        for (PixKeyDTO keyDTO : keyList)
        {
            if (keyDTO != null)
            {
                keyDTO.setIsActive(null);
                returnList.add(MapperUtil.mapToPixKey(keyDTO));
            }

        }
        return returnList;
    }
}
