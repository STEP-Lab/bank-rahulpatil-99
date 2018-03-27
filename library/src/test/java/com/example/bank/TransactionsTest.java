package com.example.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
  private Transactions transactions;
  SimpleDateFormat dateFormatter;
  Date febTwenty,febTwentyOne,febTwentyFive,febTwentyTwo;

  @Before
  public void setUp() throws ParseException {
    transactions = new Transactions();
    dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    febTwenty = dateFormatter.parse("2018-02-20");
    febTwentyOne = dateFormatter.parse("2018-02-21");
    febTwentyTwo = dateFormatter.parse("2018-02-22");
    febTwentyFive = dateFormatter.parse("2018-02-25");
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
  public void mustFilterTransactionOn() {
    transactions.credit(febTwenty,1200.10,"Vijay");
    transactions.debit(febTwenty,700.50,"Rahul");
    transactions.credit(febTwentyOne,1500.00,"Vijay");
    CreditTransaction creditTransaction = new CreditTransaction(febTwenty,1200.10, "Vijay");
    DebitTransaction debitTransaction = new DebitTransaction(febTwenty,700.50, "Rahul");
    Transactions expected = transactions.getTransactionsHappenedOn(febTwenty);
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }

  @Test
  public void mustFilterTransactionBefore() {
    transactions.debit(febTwenty,700.50,"Rahul");
    transactions.credit(febTwentyOne,1500.00,"Vijay");
    transactions.debit(febTwentyFive,700.00,"Rahul");
    DebitTransaction debitTransaction = new DebitTransaction(febTwenty,700.50, "Rahul");
    CreditTransaction creditTransaction = new CreditTransaction(febTwentyOne,1500.00, "Vijay");
    Transactions expected = transactions.getTransactionsBefore(febTwentyFive);
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }

  @Test
  public void mustFilterTransactionAfter() {
    transactions.credit(febTwenty,1200.10,"Vijay");
    transactions.credit(febTwentyOne,1500.00,"Vijay");
    transactions.debit(febTwentyFive,700.00,"Rahul");
    CreditTransaction creditTransaction = new CreditTransaction(febTwentyOne,1500.00, "Vijay");
    DebitTransaction debitTransaction = new DebitTransaction(febTwentyFive,700.00, "Rahul");
    Transactions expected = transactions.getTransactionsAfter(febTwenty);
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }

  @Test
  public void mustFilterTransactionBetween() {
    transactions.credit(febTwenty,1200.10,"Vijay");
    transactions.credit(febTwentyOne,1500.00,"Vijay");
    transactions.debit(febTwentyTwo,200.00,"Rahul");
    transactions.debit(febTwentyFive,700.00,"Rahul");
    CreditTransaction creditTransaction = new CreditTransaction(febTwentyOne,1500.00, "Vijay");
    DebitTransaction debitTransaction = new DebitTransaction(febTwentyTwo,200.00, "Rahul");
    Transactions expected = transactions.getTransactionsBetween(febTwenty,febTwentyFive);
    assertThat(expected.getTransactions(),hasItems(creditTransaction,debitTransaction));
  }

  @Test
  public void mustCalculateCurrentBalance() {
    transactions.credit(1200.10,"Vijay");
    transactions.debit(100.00,"Vijay");
    assertThat(transactions.getCurrentBalance(),is(1100.10));
  }

  @Test
  public void writeTransactionInCsvFile() throws IOException {
    ArrayList<String> actual = new ArrayList<>();
    Date date = new Date();
    transactions.credit(date,1000,"Rahul");
    CreditTransaction creditTransaction = new CreditTransaction(date, 1000, "Rahul");
    File file = new File("test.csv");
    FileWriter fileWriter = new FileWriter(file){
      public void write(String str) {
        actual.add(str);
      }
    };
    transactions.printToCsv(fileWriter);
    fileWriter.flush();
    fileWriter.close();
    String expected = creditTransaction.toCsv()+",1000.0\n";
    assertThat(actual,hasItem(expected));
    file.delete();
  }
}
