package com.itau.pixkeymanagement.controller;


import com.itau.pixkeymanagement.model.HttpResponseStructure;
import com.itau.pixkeymanagement.model.dto.request.PixKeyAlterRequestDTO;
import com.itau.pixkeymanagement.model.dto.request.PixKeyInsertRequestDTO;
import com.itau.pixkeymanagement.service.PixKeyManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RequestMapping("api/v1/pix-key-management")
@RestController
public class PixKeyManagementController {

    @Autowired
    private PixKeyManagementService pixKeyManagementService;

    @PostMapping("/register")
    public ResponseEntity<String> registerKey(@RequestBody @Valid PixKeyInsertRequestDTO requestDTO) throws MethodArgumentNotValidException {

        HttpResponseStructure response = pixKeyManagementService.registerKey(requestDTO);
        return new ResponseEntity<>(response.getBodyMessage(), response.getHttpCode());
    }

    @PutMapping("/alter")
    public ResponseEntity<String> alterKey(@RequestBody @Valid PixKeyAlterRequestDTO requestDTO)  {

        HttpResponseStructure response = pixKeyManagementService.alterKey(requestDTO);
        return new ResponseEntity<>(response.getBodyMessage(), response.getHttpCode());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deactivateKey(@RequestParam(required = true) String id)  {

        HttpResponseStructure response = pixKeyManagementService.deactivateKey(id);
        return new ResponseEntity<>(response.getBodyMessage(), response.getHttpCode());
    }

    @GetMapping("/get")
    public ResponseEntity<String> getKey(@RequestParam(required = false)  String id,
                                         @RequestParam(required = false) String keyType,
                                         @RequestParam(required = false) Long accountNumber,
                                         @RequestParam(required = false) Long agencyNumber,
                                         @RequestParam(required = false) String accountHolderName,
                                         @RequestParam(required = false) LocalDateTime insertionTime,
                                         @RequestParam(required = false) LocalDateTime deactivationTime)  {

        HttpResponseStructure response = pixKeyManagementService.getKey(id, keyType, accountNumber, agencyNumber, accountHolderName, insertionTime, deactivationTime);
        return new ResponseEntity<>(response.getBodyMessage(), response.getHttpCode());
    }


}
