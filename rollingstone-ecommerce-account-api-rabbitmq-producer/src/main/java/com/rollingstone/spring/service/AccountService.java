package com.rollingstone.spring.service;

import com.rollingstone.spring.model.Account;

public interface AccountService {

	Account save(Account account);
	
	void update(Account account);
	void delete(long id);
}
