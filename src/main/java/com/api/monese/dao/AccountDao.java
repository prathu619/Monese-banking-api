package com.api.monese.dao;

import com.api.monese.dao.entities.Account;
import com.api.monese.dao.repository.AccountsRepository;
import com.api.monese.util.ApplicationException;
import com.api.monese.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

@Repository
public class AccountDao {

    @Autowired
    private AccountsRepository accountsRepository;

    public Account getAccountStatement(String accountNo) throws ApplicationException {
        return accountsRepository
                .findById(accountNo)
                .orElseThrow(() -> new ApplicationException(MessageFormat.format(Constants.ERROR_ACCOUNT_NOT_FOUND, accountNo)));
    }

    public void saveAccount(Account account) {
        accountsRepository.save(account);
    }
}
