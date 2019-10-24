package com.rollingstone.spring.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.spring.model.Account;

public interface AccountDaoRepository extends PagingAndSortingRepository<Account, Long> {

	Page<Account> findAll(Pageable pageable);
	
	
}
