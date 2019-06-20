package com.api.monese.dao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    private Long transactionId;
    private Date transactionDate;
    private String transactionType;
    private String transactionDescription;
    private String accountNo;
    private Integer amount;
    private Integer initialBalance;
    private Integer accountBalance;

    public Transaction()
    {

    }

    public Transaction(String accountNo, Integer amount, String transactionType, String transactionDescription, Integer initialBalance, Integer accountBalance) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDescription = transactionDescription;
        this.initialBalance = initialBalance;
        this.accountBalance = accountBalance;
    }

    @Id
    @GeneratedValue
    @JsonIgnore
    public Long getTransactionId() {
        return transactionId;
    }

    @JsonIgnore
    public String getAccountNo() {
        return accountNo;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Integer initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }
}
