package com.example.bank;

import org.junit.Test;

import static com.example.bank.Money.getMoney;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MoneyTest {
  @Test
  public void shouldAcceptValidAmount() throws InvalidAmount {
    Money amount = getMoney(1000.00);
    assertThat(amount.getAmount(),is(1000.0));
  }

  @Test(expected = InvalidAmount.class)
  public void mustNotAcceptInvalidAmount() throws InvalidAmount {
    getMoney(-100.0);
  }
}
