/**
 * 
 */
package com.sample.ruidas.pojo;

/**
 * @author Ujjal
 *
 */
public class Account {
	
	private String customerName;
	private String currency;
	private double accountBalance;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
			
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Account [customerName=" + customerName + ", currency=" + currency + ", accountBalance=" + accountBalance + "]";
	}
	
	
	
	

}
