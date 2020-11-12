package com.dinesh.springbootjpa.form;

public class CreateAccountForm {

	private String accountName;
	private double amount;
	
	
	public CreateAccountForm() {
		
	}


	public CreateAccountForm(String accountName, double amount) {
		super();
		this.accountName = accountName;
		this.amount = amount;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
