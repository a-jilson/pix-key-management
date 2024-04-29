package com.itau.pixkeymanagement.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PIX_TABLE") // Specify the table name
public class PixKeyDTO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "key_type")
    private String keyType;

    @Column(name = "key_value")
    private String keyValue;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "agency_number")
    private Long agencyNumber;

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "account_holder_surname")
    private String accountHolderSurname;

    @Column(name = "account_holder_type")
    private String accountHolderType;

    @Column(name = "insertion_time")
    private LocalDateTime insertionTime;

    @Column(name = "deactivation_time")
    private LocalDateTime deactivationTime;

    @Column(name = "is_active")
    private Boolean isActive;
}