package com.example.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount,Long> {

}
