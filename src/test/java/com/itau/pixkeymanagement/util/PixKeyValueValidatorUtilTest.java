package com.itau.pixkeymanagement.util;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PixKeyValueValidatorUtilTest {

    @InjectMocks
    private PixKeyValueValidatorUtil validatorUtil;

    @Test
    void validateKeyValue_ValidCelular_ReturnsTrue() {
        String keyType = "celular";
        String keyValue = "+5511999999999";
        assertDoesNotThrow(() -> validatorUtil.validateKeyValue(keyType, keyValue));
    }

    @Test
    void validateKeyValue_InvalidCelular_ThrowsMethodArgumentNotValidException() {
        String keyType = "celular";
        String keyValue = "+55abc99999999";
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
                () -> validatorUtil.validateKeyValue(keyType, keyValue));
        assertTrue(exception.getMessage().contains("Invalid Celular value"));
    }

    @Test
    void validateKeyValue_ValidEmail_ReturnsTrue() {
        String keyType = "email";
        String keyValue = "test@example.com";
        assertDoesNotThrow(() -> validatorUtil.validateKeyValue(keyType, keyValue));
    }

    @Test
    void validateKeyValue_InvalidEmail_ThrowsMethodArgumentNotValidException() {
        String keyType = "email";
        String keyValue = "invalid_email";
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
                () -> validatorUtil.validateKeyValue(keyType, keyValue));
        assertTrue(exception.getMessage().contains("Invalid Email value"));
    }

    @Test
    void validateKeyValue_ValidCPF_ReturnsTrue() {
        String keyType = "cpf";
        String keyValue = "12345678901";
        assertDoesNotThrow(() -> validatorUtil.validateKeyValue(keyType, keyValue));
    }

    @Test
    void validateKeyValue_InvalidCPF_ThrowsMethodArgumentNotValidException() {
        String keyType = "cpf";
        String keyValue = "123abc78901";
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
                () -> validatorUtil.validateKeyValue(keyType, keyValue));
        assertTrue(exception.getMessage().contains("Invalid CPF value"));
    }

    @Test
    void validateKeyValue_ValidCNPJ_ReturnsTrue() {
        String keyType = "cnpj";
        String keyValue = "12345678901234";
        assertDoesNotThrow(() -> validatorUtil.validateKeyValue(keyType, keyValue));
    }

    @Test
    void validateKeyValue_InvalidCNPJ_ThrowsMethodArgumentNotValidException() {
        String keyType = "cnpj";
        String keyValue = "1234567890abc34";
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
                () -> validatorUtil.validateKeyValue(keyType, keyValue));
        assertTrue(exception.getMessage().contains("Invalid CNPJ value"));
    }

    @Test
    void validateKeyValue_ValidAleatorio_ReturnsTrue() {
        String keyType = "aleatorio";
        String keyValue = "abcdefghijklmnopqrstuvwxyz1234567890";
        assertDoesNotThrow(() -> validatorUtil.validateKeyValue(keyType, keyValue));
    }

    @Test
    void validateKeyValue_InvalidAleatorio_ThrowsMethodArgumentNotValidException() {
        String keyType = "aleatorio";
        String keyValue = "too_short";
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
                () -> validatorUtil.validateKeyValue(keyType, keyValue));
        assertTrue(exception.getMessage().contains("Invalid Aleatorio value"));
    }

    @Test
    void validateKeyValue_InvalidKeyType_ThrowsMethodArgumentNotValidException() {
        String keyType = "invalid";
        String keyValue = "value";
        MethodArgumentNotValidException exception = assertThrows(MethodArgumentNotValidException.class,
                () -> validatorUtil.validateKeyValue(keyType, keyValue));
        assertTrue(exception.getMessage().contains("Invalid keyType"));
    }
}