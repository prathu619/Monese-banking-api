package com.api.monese.dao.repository;

import com.api.monese.dao.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account, String> {
}
