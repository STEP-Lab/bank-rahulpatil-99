package com.example.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

  private Transactions transactions;

  @Before
  public void setUp() {
    transactions = new Transactions();
  }

  @Test
  public void mustStoreCreditTransaction() {
    Transactions transactions = new Transactions();
    transactions.credit(200.00,"Rahul");
    assertThat(transactions.getTransactions(),hasItem(new CreditTransaction(200.00,"Rahul")));
  }

  @Test
  public void mustStoreDebitTransaction() {
    transactions.debit(200.00,"Vijay");
    assertThat(transactions.getTransactions(),hasItem(new DebitTransaction(200.00,"Vijay")));
  }

  @Test
  public void mustStoreBothDebitAndCreditTransaction() {
    transactions.credit(1000.00,"Rahul");
    transactions.debit(500,"Vijay");
    assertThat(transactions.getTransactions(),hasItem(new CreditTransaction(1000.00,"Rahul")));
    assertThat(transactions.getTransactions(),hasItem(new DebitTransaction(500.00,"Vijay")));
  }
}
