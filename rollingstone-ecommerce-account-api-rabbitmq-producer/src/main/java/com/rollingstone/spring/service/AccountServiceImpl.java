package com.rollingstone.spring.service;

import com.rollingstone.spring.dao.AccountDaoRepository;
import com.rollingstone.spring.model.Account;

public class AccountServiceImpl implements AccountService{

	
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

}
