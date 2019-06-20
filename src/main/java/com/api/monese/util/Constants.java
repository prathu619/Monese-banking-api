package com.api.monese.util;

public class Constants {

   public static final String TRANSFER_MSG = "Transfer Request {0} Account: {1}";
    public static final String TRANSACTION_SUCCESS_MSG = "Requested transfer has been successfully completed.";
    public static final String ERROR_ACCOUNT_NOT_FOUND = "Account number {0} does not exist.";
    public static final String DEBIT_TRANSACTION_TYPE = "Debit";
    public static final String CREDIT_TRANSACTION_TYPE = "Credit";
    public static final String ERROR_INSUFFICIENT_BALANCE = "The account have insufficient balance to make the transfer.";
    public static final String ERROR_SAME_ACCOUNT_TRANSFER = "Source and Destination account cannot be the same";
}
