package com.rollingstone.spring.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rollingstone.spring.dao.AccountDaoRepository;
import com.rollingstone.spring.model.Account;
import com.rollingstone.spring.model.AccountDTO;

public class AccountServiceImpl implements AccountService{

	final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	AccountDaoRepository accountDaoRepository;
	
	public AccountServiceImpl(AccountDaoRepository accountDaoRepository) {
		this.accountDaoRepository = accountDaoRepository;
	}
	
	@Override
	public Account save(Account account) {
		return accountDaoRepository.save(account);
	}

	@Override
	public void update(Account account) {
		accountDaoRepository.save(account);
	}

	@Override
	public void delete(long id) {
		accountDaoRepository.deleteById(id);
	}

	@Override
	public AccountDTO getAccount(long id) {
		logger.info("Inside GetAccount Method");
		
		AccountDTO accountDTO = accountDaoRepository.getAccountById(id);
		
		if (accountDTO == null) {
			logger.info("Inside GetAccount Method accountDTO is null");
		}
		else {
			logger.info("Inside GetAccount Method accountDTO is not null");
		}
		return accountDTO;
	}

	@Override
	public Page<Account> getAccountsByPage(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("accountNumber").descending());
		
		return accountDaoRepository.findAll(pageable);
	}

}
