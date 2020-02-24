package com.ibm.service;

import java.util.ArrayList;
import java.util.List;

import com.ibm.bean.Customer;
import com.ibm.bean.Transaction;
import com.ibm.dao.DaoApply;

public class ServiceClass implements BankService {
	
	DaoApply dap = new DaoApply();
	
	
	@Override
	public boolean validatePassword(Customer c) {
		 
		return (c.getPassword().matches(USERPASSWORDPATTERN));
	}


	@Override
	public boolean createAccount(Customer c) {
		return dap.createAccount(c);
	}


	@Override
	public boolean login(Customer c) {
		return dap.login(c);
	}


	@Override
	public ArrayList<Customer>viewBalance(Customer c) {
		return dap.viewBalance(c);
	}


	@Override
	public boolean lowBalance(Customer c) {
		return dap.isLowBalance(c);
	}


	@Override
	public boolean deposit(Customer c, int amount) {
		return dap.deposit(c, amount);
	}


	@Override
	public boolean withdrawal(Customer c, int amount) {
		return dap.withdrawal(c, amount);
	}


	@Override
	public boolean send(Customer c, int account_no_to, int amount) {
		return dap.send(c, account_no_to, amount);
	}

	@Override
	public ArrayList<Transaction> printTransaction(Customer c) {
		return dap.printTransaction(c);
	}
	


	@Override
	public boolean validateMail(Customer c) {
		return(c.getEmail().matches(USERMAILPATTERN));
	}


	@Override
	public boolean validatePhone(Customer c) {
		return(c.getPhone().matches(USERPHONEPATTERN));
	}
	
	public boolean validateBalance(Customer c) {
		if(c.a.getBalance()>500)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean isAccountExist(int to_account) {
		return dap.isAccountExist(to_account);
	}

}

