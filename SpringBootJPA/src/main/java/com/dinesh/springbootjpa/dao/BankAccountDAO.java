package com.dinesh.springbootjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dinesh.springbootjpa.entity.BankAccount;
import com.dinesh.springbootjpa.exception.BankTransactionException;
import com.dinesh.springbootjpa.model.BankAccountInfo;

import net.bytebuddy.description.type.TypeDescription.Generic.OfNonGenericType;

import java.util.Optional;

@Repository
public class BankAccountDAO {

	@Autowired
	private EntityManager entityManager;

	public BankAccountDAO() {
		super();
	}

	@Cacheable(value = "Bank_Account")
	public BankAccount findById(long id) {
		return this.entityManager.find(BankAccount.class, id);
	}

	@Cacheable(value = "Bank_Account")
	public List<BankAccountInfo> listBankAccountInfo() {
		String sql = "Select new " + BankAccountInfo.class.getName() + " (e.id, e.fullName, e.balance)" + " from "
				+ BankAccount.class.getName() + " e ";
		Query query = entityManager.createQuery(sql, BankAccountInfo.class);
		return query.getResultList();
	}

	// MANDATORY: Transaction must be created before.
	@Transactional(propagation = Propagation.MANDATORY)
	@CacheEvict(value = "Bank_Account", allEntries = true)
	public void addAmount(long id, double amount) throws BankTransactionException {
		BankAccount account = this.findById(id);
		Optional<BankAccount> accountNullable = Optional.ofNullable(account);
		if (accountNullable.isPresent()) {
			double newBalance = account.getBalance() + amount;
			if (account.getBalance() + amount < 0) {
				throw new BankTransactionException(
						"The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
			}
			account.setBalance(newBalance);
		} else {
			System.out.println("The Accout is null");
			throw new BankTransactionException("Account not found " + id);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	@CacheEvict(value = "Bank_Account", allEntries = true)
	public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
		addAmount(toAccountId, amount);
		addAmount(fromAccountId, -amount);
	}
	
	@Transactional
	@CacheEvict(value = "Bank_Account", allEntries = true)
	public void createAccount(BankAccount account) throws BankTransactionException {
		entityManager.persist(account);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	@CacheEvict(value = "Bank_Account", allEntries = true)
	public void depositMoney(Long toAccountId, double amount) throws BankTransactionException {
		addAmount(toAccountId, amount);
	}
}
