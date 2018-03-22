package com.example.bank;

import com.example.bank.AccountNumber;
import com.example.bank.InvalidAccountNumber;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestAccountNumber {
  @Test
  public void mustAcceptValidAccountNumber() throws InvalidAccountNumber {
    new AccountNumber("1111-1234");
  }

  @Test(expected = InvalidAccountNumber.class)
  public void mustNotAcceptInvalidAccountNumber() throws InvalidAccountNumber {
    new AccountNumber("11-223");
  }

  @Test(expected = InvalidAccountNumber.class)
  public void mustNotAcceptAccountNumberHavingAlphabets() throws InvalidAccountNumber {
    new AccountNumber("123a-b123");
  }

  @Test
  public void mustGetProperAccountNumber() throws InvalidAccountNumber {
    assertThat(new AccountNumber("1111-1111").getNumber(),is("1111-1111"));
  }
}
