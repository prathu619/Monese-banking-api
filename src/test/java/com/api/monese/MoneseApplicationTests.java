package com.api.monese;

import com.api.monese.dao.entities.Account;
import com.api.monese.dao.repository.AccountsRepository;
import com.api.monese.model.ErrorResponse

		;
import com.api.monese.model.SuccessResponse;
import com.api.monese.model.TransactionRequest;
import com.api.monese.service.AccountDao;
import com.api.monese.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoneseApplicationTests {

	@Autowired
	private AccountDao bankingService;

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private TestRestTemplate template;

	@LocalServerPort
	private int portNumber;

	private final String URL = "http://localhost:";
	private final String ACCT_STATEMENT_ENDPOINT = "/accountStatement/";
	private final String TRANSFER_ENDPOINT = "/transact";

	@Before
	public void setUp() {
		createTestData();
		template.getRestTemplate().setInterceptors(
				Collections.singletonList((request, body, execution) -> {
					request.getHeaders()
							.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
					return execution.execute(request, body);
				}));
	}

	@Test
	public void willTestGetAccountForExistingAccount() {
		//given
		String expectedAccount = "123456";
		String path = this.URL + this.portNumber + this.ACCT_STATEMENT_ENDPOINT + expectedAccount;

		//when
		Account response = this.template.getForObject(path, Account.class);

		//when
		assertThat(response.getAccountNo()).isEqualTo(expectedAccount);
	}

	@Test
	public void willTestGetAccountForInvalidAccount() {
		//given
		String path = this.URL + this.portNumber + this.ACCT_STATEMENT_ENDPOINT + "00000";

		String errorMessage = "Account number 00000 does not exist.";

		//when
		ErrorResponse response = this.template.getForObject(path, ErrorResponse.class);

		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getAppErrors().get(0).getErrorMessage()).isEqualTo(errorMessage);
	}

	@Test
	public void willTestMoneyTransfer() {
		//given
		String sourceAccount = "123456";
		String targetAccount = "987654";
		Integer transferAmount = 500;

		TransactionRequest transfer = new TransactionRequest();
		transfer.setSourceAccount(sourceAccount);
		transfer.setTargetAccount(targetAccount);
		transfer.setTransferAmount(transferAmount);

		String path = this.URL + this.portNumber + this.TRANSFER_ENDPOINT;

		Integer sourceAccountBalance = this.getAccount(sourceAccount).getAccountBalance() - transferAmount;
		Integer targetAccountBalance = this.getAccount(targetAccount).getAccountBalance() + transferAmount;

		//when
		SuccessResponse response = this.template.postForObject(path, transfer, SuccessResponse.class);
		Integer actualFromAccountBalance = this.getAccount(sourceAccount).getAccountBalance();
		Integer actualToAccountBalance = this.getAccount(targetAccount).getAccountBalance();

		//then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
		assertThat(response.getResponseMessage()).isEqualTo(Constants.TRANSACTION_SUCCESS_MSG);
		assertThat(actualFromAccountBalance).isEqualTo(sourceAccountBalance);
		assertThat(actualToAccountBalance).isEqualTo(targetAccountBalance);
	}

	//simlarly we will write test cases for other scenarios



	private void createTestData() {
		Account account1 = new Account("123456", "Test Account 1", "Current", 5000);
		Account account2  = new Account("987654", "Test Account 2", "Current", 500);
		List<Account> accountList = Arrays.asList(account1, account2);
		accountsRepository.saveAll(accountList);
	}

	private Account getAccount (String accountNo) {
		String path = this.URL + this.portNumber + this.ACCT_STATEMENT_ENDPOINT + accountNo;
		return this.template.getForObject(path, Account.class);
	}


}
