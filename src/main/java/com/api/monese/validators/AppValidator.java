package com.api.monese.validators;

import com.api.monese.dao.entities.Account;
import com.api.monese.model.Errors;
import com.api.monese.model.TransactionRequest;
import com.api.monese.dao.AccountDao;
import com.api.monese.util.ApplicationException;
import com.api.monese.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * This class is responsible for handling validation logic.
 */
@Component
public class AppValidator {

    @Autowired
    private AccountDao accountDao;

    /**
     * Below method is responsible for validating transfer request.
     * @param transactionRequest
     * @throws ApplicationException
     */
    public void validate(TransactionRequest transactionRequest) throws ApplicationException {
        Errors errors = new Errors();
        validateRequest(transactionRequest, errors);
        performBusinessValidations(transactionRequest, errors);

    }

    /**
     * Below method is responsible for handling all input validations.
     * @param transactionRequest
     * @param errors
     * @throws ApplicationException
     */
    private void validateRequest(TransactionRequest transactionRequest, Errors errors) throws ApplicationException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<TransactionRequest>> violations = validator.validate(transactionRequest);
        violations.forEach(violation -> errors.reject(violation.getMessage()));
        if (errors.hasErrors()) {
            throw new ApplicationException(errors);
        }
    }

    /**
     * Below method is responsible for handling business validations.
     * @param transactionRequest
     * @param errors
     * @throws ApplicationException
     */
    private void performBusinessValidations(TransactionRequest transactionRequest, Errors errors) throws ApplicationException {
        Account fromAccount = accountDao.getAccountStatement(transactionRequest.getSourceAccount());
        Account toAccount = accountDao.getAccountStatement(transactionRequest.getTargetAccount());
        if (fromAccount.getAccountNo().equals(toAccount.getAccountNo())) {
            errors.reject(Constants.ERROR_SAME_ACCOUNT_TRANSFER);
        }
        if (transactionRequest.getTransferAmount() > fromAccount.getAccountBalance()) {
            errors.reject(Constants.ERROR_INSUFFICIENT_BALANCE);
        }
        if (errors.hasErrors()) {
            throw new ApplicationException(errors);
        }
    }


}
