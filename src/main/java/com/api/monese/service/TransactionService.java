package com.api.monese.service;

import com.api.monese.dao.entities.Transaction;
import com.api.monese.model.SuccessResponse;
import com.api.monese.model.TransactionRequest;
import com.api.monese.util.ApplicationException;


public interface TransactionService {


    SuccessResponse doTransfer(TransactionRequest transactionRequest) throws ApplicationException;

    void saveTransaction(Transaction transaction) ;
}
