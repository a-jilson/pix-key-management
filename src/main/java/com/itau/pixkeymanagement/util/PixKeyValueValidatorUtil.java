package com.itau.pixkeymanagement.util;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class PixKeyValueValidatorUtil {

    public static Boolean validateKeyValue(String keyType, String keyValue) throws MethodArgumentNotValidException {
        switch (keyType) {
            case "celular":
                return validateCelular(keyValue);
            case "email":
                return validateEmail(keyValue);
            case "cpf":
                return validateCPF(keyValue);
            case "cnpj":
                return validateCNPJ(keyValue);
            case "aleatorio":
                return validateAleatorio(keyValue);
            default:
                throwMethodArgumentNotValidException("Invalid keyType: " + keyType);
                throw new IllegalArgumentException("Invalid keyType: " + keyType);
        }
    }

    private static Boolean validateCelular(String keyValue) throws MethodArgumentNotValidException {
        // Regular expression for celular validation
        String celularRegex = "^\\+\\d{2}\\d{2}\\d{9}$";

        if (!keyValue.matches(celularRegex)) {
            throwMethodArgumentNotValidException("Invalid Celular value: it must be in the format '+XXDDXXXXXXXXX'.");
        }

        return true;
    }

    private static Boolean validateEmail(String keyValue) throws MethodArgumentNotValidException {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,77}$";

        // Check if the email address matches the regex pattern
        if (!keyValue.matches(emailRegex)) {
            throwMethodArgumentNotValidException("Invalid Email value: it must be a valid email address with up to 77 characters.");
        }

        return true;
    }

    private static Boolean validateCPF(String keyValue) throws MethodArgumentNotValidException {
        // Regular expression for CPF validation
        String cpfRegex = "^[0-9]{11}$";

        if (!keyValue.matches(cpfRegex)) {
            throwMethodArgumentNotValidException("Invalid CPF value: it must contain 11 digits.");
        }

        return true;
    }

    private static Boolean validateCNPJ(String keyValue) throws MethodArgumentNotValidException {
        // Regular expression for CNPJ validation
        String cnpjRegex = "^[0-9]{14}$";

        if (!keyValue.matches(cnpjRegex)) {
            throwMethodArgumentNotValidException("Invalid CNPJ value: it must contain 14 digits.");
        }

        return true;
    }

    private static Boolean validateAleatorio(String keyValue) throws MethodArgumentNotValidException {
        // Regular expression for alphanumeric characters
        String alphanumericRegex = "^[a-zA-Z0-9]*$";

        // Check if the value has exactly 36 alphanumeric characters
        if (keyValue.length() != 36 || !keyValue.matches(alphanumericRegex)) {
            throwMethodArgumentNotValidException("Invalid Aleatorio value: it must contain exactly 36 alphanumeric characters.");
        }

        return true;
    }

    private static void throwMethodArgumentNotValidException(String errorMessage) throws MethodArgumentNotValidException {
        MethodParameter parameter = new MethodParameter(PixKeyValueValidatorUtil.class.getDeclaredMethods()[0], -1);
        BindingResult result = new BeanPropertyBindingResult(null, null);
        result.addError(new FieldError("", "", errorMessage)); // Using a generic field name
        throw new MethodArgumentNotValidException(parameter, result);
    }
}
