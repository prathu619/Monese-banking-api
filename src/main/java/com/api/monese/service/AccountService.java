package com.api.monese.service;

import com.api.monese.dao.entities.Account;
import com.api.monese.model.SuccessResponse;
import com.api.monese.model.TransactionRequest;
import com.api.monese.util.ApplicationException;


public interface AccountService {

    SuccessResponse doTransfer(TransactionRequest transactionRequest) throws ApplicationException;


    Account getAccountStatement(String accountNo) throws ApplicationException;
}
