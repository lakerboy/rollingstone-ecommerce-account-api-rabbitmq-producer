package com.rollingstone.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.aspects.RestControllerAspect;
import com.rollingstone.events.AccountEvent;
import com.rollingstone.spring.model.Account;
import com.rollingstone.spring.model.AccountDTO;
import com.rollingstone.spring.service.AccountService;

@RestController
@RequestMapping(value="rsecommerce/pdp-service")
public class AccountController extends AbstractController{

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	/* Add New Account */
	@PostMapping("/account")
	public ResponseEntity<?> createAccount(@RequestBody Account account){
		Account savedAccount = accountService.save(account);
		
		AccountDTO accountDto = accountService.getAccount(savedAccount.getId());
		
		AccountEvent accountCreatedEvent = new AccountEvent(this, "AccountCreatedEvent", accountDto);
		eventPublisher.publishEvent(accountCreatedEvent);
		
		return ResponseEntity.ok().body("New account has been seved with ID :"+savedAccount.getId());
	}
	
	@GetMapping("/account/{id}")
	public AccountDTO getAccount(@PathVariable("id")long id) {
		if (accountService == null) {
			log.info("accountService is null");
		}
		
		AccountDTO account = accountService.getAccount(id);
		AccountEvent accountRetrievedEvent = new AccountEvent(this, "AccountRetrievedEvent", account);
		eventPublisher.publishEvent(accountRetrievedEvent);
		return account;
	}
	
	@GetMapping("/account")
	@ResponseBody Page<Account> getAccoutsByPage(
			@RequestParam(value = "pagenumber", required=true, defaultValue="0") Integer pageNumber,
			@RequestParam(value = "pagesize", required=true, defaultValue="20") Integer pageSize){
		
		Page<Account> pagedAccounts = accountService.getAccountsByPage(pageNumber, pageSize);
		
		return pagedAccounts;
	}
	
	@PutMapping("/account/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody Account account){
		checkResourceFound(this.accountService.getAccount(id));
		accountService.update(account);
		
		return ResponseEntity.ok().body("Account has been updated successfully");
	}
	
	@PutMapping("/account/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable("id") long id){
		checkResourceFound(this.accountService.getAccount(id));
		accountService.delete(id);
		
		return ResponseEntity.ok().body("Account has been deleted successfully");
	}
}
