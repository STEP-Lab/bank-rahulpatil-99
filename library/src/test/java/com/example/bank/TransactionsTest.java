package com.example.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItems;
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

  @Test
  public void mustFilterTransactionsAbove() {
    transactions.credit(2000.00,"Rahul");
    transactions.credit(900.00,"Vijay");
    transactions.debit(1100.00,"Vijay");
    Transactions expected = transactions.getTransactionsAbove(1000);
    CreditTransaction creditTransaction = new CreditTransaction(2000.0, "Rahul");
    DebitTransaction debitTransaction = new DebitTransaction(1100.0, "Vijay");
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }

  @Test
  public void mustFilterTransactionBelow() {
    transactions.credit(700.50,"Rahul");
    transactions.credit(1200.00,"Vijay");
    transactions.debit(800.33,"Vijay");
    Transactions expected = transactions.getTransactionsBelow(1000);
    CreditTransaction creditTransaction = new CreditTransaction(700.50, "Rahul");
    DebitTransaction debitTransaction = new DebitTransaction(800.33, "Vijay");
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }
}
