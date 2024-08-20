package com.picpaysimplificado.picpaysimplificado.controllers;

import com.picpaysimplificado.picpaysimplificado.dto.TransactionDTO;
import com.picpaysimplificado.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TransactionDTO dto) throws Exception {
        this.transactionService.createTransaction(dto);
        return ResponseEntity.ok(null);
    }
}
