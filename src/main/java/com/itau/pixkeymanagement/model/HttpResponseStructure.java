package com.itau.pixkeymanagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Data
public class HttpResponseStructure {

    private HttpStatusCode httpCode;
    private String bodyMessage;
}
