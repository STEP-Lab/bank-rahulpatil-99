package com.example.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
  @Test
  public void mustRecordCorrectDateOfDebitTransaction() {
    Date date = new Date();
    Transaction transaction = new DebitTransaction(date,1000.00, "Vishal");
    assertThat(transaction.getDate(),is(date));
  }

  @Test
  public void mustRecordCorrectDateOfCreditTransaction() {
    Transaction transaction = new CreditTransaction(1000.00, "Vijay");
    assertThat(transaction.getDate().toString(),is(new Date().toString()));
  }

  @Test
  public void mustGetAmountOfAnTransaction() {
    Transaction transaction = new CreditTransaction(1000.00, "Vijay");
    assertThat(transaction.getAmount(),is(1000.00));
  }

  @Test
  public void mustGetSourceOfAnTransaction() {
    Transaction transaction = new DebitTransaction(1000.00, "Vishal");
    assertThat(transaction.getSource(),is("Vishal"));
  }
}
