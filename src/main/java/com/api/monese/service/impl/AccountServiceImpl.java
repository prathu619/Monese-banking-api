package com.api.monese.service.impl;

import com.api.monese.dao.AccountDao;
import com.api.monese.dao.entities.Account;
import com.api.monese.model.SuccessResponse;
import com.api.monese.model.TransactionRequest;
import com.api.monese.service.AccountService;
import com.api.monese.service.TransactionService;
import com.api.monese.util.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Below is the service implementation class for Account Operations.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private TransactionService transactionServiceImpl;

    @Autowired
    private AccountDao accountDao;

    /**
     * Method is resposible for handling service logic for transfer request.
     * @param transactionRequest
     * @return
     * @throws ApplicationException
     */
    public SuccessResponse doTransfer(TransactionRequest transactionRequest) throws ApplicationException {
        return transactionServiceImpl.doTransfer(transactionRequest);
    }

    /**
     * below method is responsible for handling service logic for retrieving account statement details.
     * @param accountNo
     * @return
     * @throws ApplicationException
     */
    public Account getAccountStatement(String accountNo) throws ApplicationException {
        return accountDao.getAccountStatement(accountNo);
    }
}
