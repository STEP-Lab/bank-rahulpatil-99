package com.example.bank;

import com.example.bank.DebitTransaction;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TransactionTest {
  @Test
  public void mustRecordCorrectDateOfTransaction() {
    Date date = new Date();
    Transaction transaction = new DebitTransaction(date,1000.00, "Vishal");
    assertThat(transaction.getDate(),is(date));
  }
}
