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

    @Query("SELECT p FROM PixKeyDTO p WHERE (:keyType IS NULL OR p.keyType = :keyType) " +
            "AND (:accountNumber IS NULL OR p.accountNumber = :accountNumber) " +
            "AND (:agencyNumber IS NULL OR p.agencyNumber = :agencyNumber) " +
            "AND (:accountHolderName IS NULL OR p.accountHolderName = :accountHolderName) " +
            "AND (:insertionTime IS NULL OR p.insertionTime = :insertionTime) " +
            "AND (:deactivationTime IS NULL OR p.deactivationTime = :deactivationTime)")
    List<PixKeyDTO> findByCriteria(@Param("keyType") String keyType,
                                   @Param("accountNumber") Long accountNumber,
                                   @Param("agencyNumber") Long agencyNumber,
                                   @Param("accountHolderName") String accountHolderName,
                                   @Param("insertionTime") LocalDateTime insertionTime,
                                   @Param("deactivationTime") LocalDateTime deactivationTime);

}