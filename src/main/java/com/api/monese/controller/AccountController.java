package com.api.monese.controller;

import com.api.monese.model.TransactionRequest;
import com.api.monese.service.AccountService;
import com.api.monese.util.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
/**
 * The below controller is responsible for handling service request related to current accounts.
 */
public class AccountController {

    @Autowired
    private AccountService accountService;

     /**
     *Below method is responsible to transactionRequest amount from  source account to destination account
     * @param transactionRequest
     * @return
     * @throws ApplicationException
     */
    @PostMapping(value = "/transact", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> doTransfer (@RequestBody TransactionRequest transactionRequest) throws ApplicationException {
        return new ResponseEntity<>(accountService.doTransfer(transactionRequest), HttpStatus.OK);
    }

    /**
     * Below method help to get the  monthly statement for the requested account number.
     * @param accountNo
     * @return
     * @throws ApplicationException
     */
    @GetMapping(value = "/accountStatement/{accountNo}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAccountStatement(@PathVariable String accountNo) throws ApplicationException {
        return new ResponseEntity<>(accountService.getAccountStatement(accountNo), HttpStatus.OK);
    }


}
