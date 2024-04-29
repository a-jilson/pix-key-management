package com.itau.pixkeymanagement.model.dto.request;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PixKeyInsertRequestDTO {

        @NotNull(message = "[keyType] cannot be null")
        @Pattern(regexp = "^(celular|email|cpf|cnpj|aleatorio)$", message = "Invalid [keyType]. Allowed values: celular, email, cpf, cnpj, aleatorio")
        @Size(max = 9, message = "[keyType] must not have more than 9 characters")
        private String keyType;

        @NotNull(message = "[keyValue] cannot be null")
        private String keyValue;

        @NotNull(message = "[accountType] cannot be null")
        @Pattern(regexp = "^(corrente|poupança)$", message = "Invalid [accountType]. Must be 'corrente' or 'poupança'")
        @Size(max = 10, message = "[accountType] must not have more than 10 characters")
        private String accountType;


        @NotNull(message = "[agencyNumber] cannot be null")
        @Digits(integer = 4, fraction = 0, message = "[agencyNumber] should not have more than 4 digits")
        private Long agencyNumber;

        @NotNull(message = "[accountNumber] cannot be null")
        @Digits(integer = 8, fraction = 0, message = "[accountNumber] should not have more than 8 digits")
        private Long accountNumber;

        @NotNull(message = "[accountHolderName] cannot be null")
        @Size(max = 30, message = "[accountHolderName] should not have more than 30 characters")
        private String accountHolderName;

        @Size(max = 45, message = "[accountHolderSurname] should not have more than 45 characters")
        private String accountHolderSurname;

        @NotNull(message = "[accountHolderType] cannot be null")
        private String accountHolderType;
}
