package com.minorProject.DigitalLibrary.controller;

import com.minorProject.DigitalLibrary.dto.createTxnRequest;
import com.minorProject.DigitalLibrary.dto.returnTxnRequest;
import com.minorProject.DigitalLibrary.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn")
public class TxnController {
    @Autowired
    TxnService txnService;

    @PostMapping("/create")
    public int createTxn(@RequestBody createTxnRequest createTxnRequest) throws Exception {
        return txnService.createTxn(createTxnRequest);
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody returnTxnRequest returnTxnRequest) throws Exception {
        return ResponseEntity.ok(txnService.returnBook(returnTxnRequest));
    }
}
