package com.api.monese.dao.initializer;

import com.api.monese.dao.repository.AccountsRepository;
import com.api.monese.dao.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Class to initialize database during application startup
 */
@Component
public class DataLoader implements ApplicationRunner {

    private AccountsRepository accountsRepository;

    @Autowired
    public DataLoader(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Account account1 = new Account("123456", "Test Account 1", "Current", 5000);
        Account account2  = new Account("987654", "Test Account 2", "Current", 500);
        List<Account> accountList = Arrays.asList(account1, account2);
        accountsRepository.saveAll(accountList);
    }
}
