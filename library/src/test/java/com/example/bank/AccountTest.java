package com.example.bank;

import com.example.bank.*;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.bank.Account.createAccount;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() throws InvalidAccountNumber, MinimumAccountBalance, InvalidAmount {
    Money balance = Money.getMoney(1000.00);
    account = createAccount(new AccountNumber("1111-1111"),"Rahul", balance);
  }

  @Test
  public void checkBalance() {
    assertEquals(account.getBalance(),1000.00);
  }

  @Test
  public void shouldCreditAmountIntoAccountBalance() throws InvalidAmount {
    Money balance = Money.getMoney(500.50);
    account.credit(balance,"Rahul");
    assertThat(account.getBalance(),is(1500.50));
  }

  @Test
  public void shouldDebitAmountFromAccountBalance() throws InvalidAmount, MinimumAccountBalance {
    Money balance = Money.getMoney(200);
    account.debit(balance,"Rahul");
    assertThat(account.getBalance(),is(800.00));
  }

  @Test(expected = MinimumAccountBalance.class)
  public void shouldHandleMinimumAccountBalanceException() throws InvalidAmount, MinimumAccountBalance {
    Money balance = Money.getMoney(600.0);
    account.debit(balance,"Rahul");
  }

  @Test
  public void shouldCheckSummaryOfAnAccount() {
    assertThat(account.getSummary(),is("Your Account Number:- 1111-1111 \nYour name:- Rahul \nYour balance:- 1000.00"));
  }

  @Test
  public void shouldCheckNameOfAnAccount() {
    assertThat(account.getName(),is("Rahul"));
  }

  @Test
  public void getAllTransactionHappened() throws InvalidAmount, MinimumAccountBalance {
    Money balance = Money.getMoney(1000);
    account.credit(balance,"Rahul");
    Money anotherBalance = Money.getMoney(500);
    account.debit(anotherBalance,"Vijay");
    CreditTransaction creditTransaction = new CreditTransaction(1000.00, "Rahul");
    DebitTransaction debitTransaction = new DebitTransaction(500.00, "Vijay");
    assertThat(account.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }
}
