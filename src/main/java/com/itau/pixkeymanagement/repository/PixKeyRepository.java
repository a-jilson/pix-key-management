package com.itau.pixkeymanagement.repository;

import com.itau.pixkeymanagement.model.entity.PixKeyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyDTO, String> {

    int countByAccountNumberAndAgencyNumber(Long accountNumber, Long agencyNumber);
    boolean existsByKeyValue(String keyValue);
    PixKeyDTO getKeyById(String Id);

    PixKeyDTO findByAccountNumber(Long accountNumber);
    PixKeyDTO findByAgencyNumber(Long agencyNumber);
    PixKeyDTO findByAccountHolderName(String accountHolderName);
    PixKeyDTO findByInsertionTime(LocalDateTime insertionTime);
    PixKeyDTO findByDeactivationTime(LocalDateTime deactivationTime);
    PixKeyDTO findByKeyType(String keyType);

}