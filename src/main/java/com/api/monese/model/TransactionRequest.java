package com.api.monese.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class TransactionRequest {
    @NotBlank(message = "Source Account cannot be empty.")
    private String sourceAccount;

    @NotBlank(message = "Target Account cannot be empty.")
    private String targetAccount;

    @NotNull(message = " Transfer Amount value is required.")
    @Min(value = 1, message = " Transfer Amount value should have a positive value.")
    private Integer transferAmount;

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }
}
