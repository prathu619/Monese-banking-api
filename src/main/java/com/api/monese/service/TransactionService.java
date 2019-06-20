package com.api.monese.service;

import com.api.monese.validators.AppValidator;
import com.api.monese.dao.entities.Account;
import com.api.monese.dao.entities.Transaction;
import com.api.monese.dao.repository.TransactionRepository;
import com.api.monese.model.SuccessResponse;
import com.api.monese.model.TransactionRequest;
import com.api.monese.util.ApplicationException;
import com.api.monese.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

/**
 * This class handles the logic for business logic related to transfers.
 */
@Service
public class TransactionService {

    @Autowired
    private AppValidator validator;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Business logic to transfer money from one account to another.
     * @param transactionRequest
     * @return
     * @throws ApplicationException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {ApplicationException.class, Exception.class})
    public SuccessResponse doTransfer(TransactionRequest transactionRequest) throws ApplicationException {

        //validation logic for transfer request
        validator.validate(transactionRequest);

        //processing the request
        String sourceMessage = MessageFormat.format(Constants.TRANSFER_MSG, "to", transactionRequest.getTargetAccount());
        String targetMessage = MessageFormat.format(Constants.TRANSFER_MSG, "from", transactionRequest.getSourceAccount());

        //deduct from source account
        Account sourceAccount = accountDao.getAccountStatement(transactionRequest.getSourceAccount());
        Integer currentBalance = sourceAccount.getAccountBalance();
        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - transactionRequest.getTransferAmount());
        Transaction debitTransaction = new Transaction(transactionRequest.getSourceAccount(), transactionRequest.getTransferAmount(),
                Constants.DEBIT_TRANSACTION_TYPE, sourceMessage, currentBalance, sourceAccount.getAccountBalance());

        //credit to target account
        Account targetAccount = accountDao.getAccountStatement(transactionRequest.getTargetAccount());
        currentBalance = targetAccount.getAccountBalance();
        targetAccount.setAccountBalance(targetAccount.getAccountBalance() + transactionRequest.getTransferAmount());
        Transaction creditTransaction = new Transaction(transactionRequest.getTargetAccount(), transactionRequest.getTransferAmount(),
                Constants.CREDIT_TRANSACTION_TYPE, targetMessage, currentBalance, targetAccount.getAccountBalance());

        //save to db
        accountDao.saveAccount(sourceAccount);
        this.saveTransaction(debitTransaction);
        accountDao.saveAccount(targetAccount);
        this.saveTransaction(creditTransaction);

        return new SuccessResponse(Constants.TRANSACTION_SUCCESS_MSG);
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

}
