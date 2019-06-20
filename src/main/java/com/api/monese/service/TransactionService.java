package com.api.monese.service;

import com.api.monese.dao.entities.Transaction;
import com.api.monese.model.SuccessResponse;
import com.api.monese.model.TransactionRequest;
import com.api.monese.util.ApplicationException;


public interface TransactionService {


    public SuccessResponse doTransfer(TransactionRequest transactionRequest) throws ApplicationException;

    public void saveTransaction(Transaction transaction) ;
}
