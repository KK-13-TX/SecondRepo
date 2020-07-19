package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
	private double epsilon = 1e-6;

	@Test
	public void accountCannotHaveNegativeOverdraftLimit() {
		Account account = new Account(-20d);

		Assert.assertEquals(0d, account.getOverdraftLimit(), epsilon);
	}

	@Test
	public void falseIfNegativeDeposit() {
		Account account = new Account(500);

		Assert.assertFalse(account.deposit(-20.0));
	}

	@Test
	public void falseIfNegativeWithdraw() {
		Account account = new Account(5);
		account.deposit(500);
		Assert.assertFalse(account.withdraw(-20.0));
	}

	@Test
	public void accountCannotOverstepItsOverdraftLimit() {
		Account account = new Account(5);
		account.deposit(20);
		Assert.assertFalse(account.withdraw(30));
	}

	@Test
	public void correctAmountOnDeposit() {
		Account account = new Account(5);
		account.deposit(20);
		assertEquals(20.0d, account.getBalance(), epsilon);
	}

	@Test
	public void correctAmountOnWithdraw() {
		Account account = new Account(5);
		account.deposit(30);
		account.withdraw(20);
		Assert.assertEquals(10.0d, account.getBalance(), epsilon);
	}

}
