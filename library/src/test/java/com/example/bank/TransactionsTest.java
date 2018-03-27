package com.example.bank;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
  private Transactions transactions;
  SimpleDateFormat dateFormatter;

  @Before
  public void setUp() {
    transactions = new Transactions();
    dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
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

  @Test
  public void mustFilterCreditTransaction() {
    transactions.credit(700.50,"Rahul");
    transactions.credit(1200.00,"Vijay");
    transactions.debit(800.33,"Vijay");
    Transactions expected = transactions.getCreditTransactions();
    CreditTransaction creditTransaction = new CreditTransaction(700.50, "Rahul");
    CreditTransaction creditTransaction1 = new CreditTransaction(1200.00, "Vijay");
    assertThat(expected.getTransactions(),hasItems(creditTransaction,creditTransaction1));
  }

  @Test
  public void mustFilterDebitTransactions() {
    transactions.credit(1200.10,"Vijay");
    transactions.debit(700.50,"Rahul");
    transactions.credit(1200.10,"Rahul");
    transactions.debit(1500.30,"Vijay");
    Transactions expected = transactions.getDebitTransactions();
    DebitTransaction debitTransaction = new DebitTransaction(700.50, "Rahul");
    DebitTransaction debitTransaction1 = new DebitTransaction(1500.30, "Vijay");
    assertThat(expected.getTransactions(),hasItems(debitTransaction,debitTransaction1));
  }

  @Test
  public void mustFilterTransactionOnParticulardate() throws ParseException {
    Date febTwenty = dateFormatter.parse("2018-02-20");
    Date febTwentyOne = dateFormatter.parse("2018-02-21");
    transactions.credit(febTwenty,1200.10,"Vijay");
    transactions.debit(febTwenty,700.50,"Rahul");
    transactions.credit(febTwentyOne,1500.00,"Vijay");

    CreditTransaction creditTransaction = new CreditTransaction(febTwenty,1200.10, "Vijay");
    DebitTransaction debitTransaction = new DebitTransaction(febTwenty,700.50, "Rahul");

    Transactions expected = transactions.getTransactionsHappenedOn(febTwenty);
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }
}
