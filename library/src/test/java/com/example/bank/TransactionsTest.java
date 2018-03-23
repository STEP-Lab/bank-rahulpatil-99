package com.example.bank;

import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
  @Test
  public void mustStoreCreditTransaction() {
    Transactions transactions = new Transactions();
    transactions.credit(200.00,"Rahul");
    assertThat(transactions.getTransactions(),hasItem(new CreditTransaction(200.00,"Rahul")));
  }
}
