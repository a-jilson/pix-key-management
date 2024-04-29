package com.itau.pixkeymanagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PixKey {

    private String id;
    private String keyType;
    private String keyValue;
    private String accountType;
    private Long agencyNumber;
    private Long accountNumber;
    private String accountHolderName;
    private String accountHolderSurname;
    private String accountHolderType;
    private LocalDateTime insertionTime;
    private LocalDateTime deactivationTime;
    private Boolean isActive;
}
