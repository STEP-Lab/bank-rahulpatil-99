import com.example.bank.Account;
import com.example.bank.InvalidAccountNumber;
import com.example.bank.InvalidAmount;
import com.example.bank.MinimumAccountBalance;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() throws InvalidAccountNumber {
    account = new Account("1111-1111","Rahul", 1000.00);
  }

  @Test
  public void checkBalance() {
    assertEquals(account.getBalance(),1000.00);
  }

  @Test(expected = InvalidAccountNumber.class)
  public void checkValidAccountNumber() throws InvalidAccountNumber {
    new Account("111-1234","Ajay",1000.00);
  }

  @Test
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(),is("1111-1111"));
  }

  @Test
  public void shouldCreditAmountIntoAccountBalance() throws InvalidAmount {
    account.credit(500.50);
    assertThat(account.getBalance(),is(1500.50));
  }

  @Test(expected = InvalidAmount.class )
  public void shouldHandleInvalidCreditAmountRequest() throws InvalidAmount {
    account.credit(-100.50);
  }

  @Test
  public void shouldDebitAmountFromAccountBalance() throws InvalidAmount, MinimumAccountBalance {
    account.debit(200);
    assertThat(account.getBalance(),is(800.00));
  }

  @Test(expected = InvalidAmount.class)
  public void shouldHandleInvalidDebitAmountRequest() throws InvalidAmount, MinimumAccountBalance {
    account.debit(-200.00);
  }

  @Test(expected = MinimumAccountBalance.class)
  public void shouldHandleMinimumAccountBalanceException() throws InvalidAmount, MinimumAccountBalance {
    account.debit(600.0);
  }

  @Test
  public void shouldCheckSummaryOfAnAccount() {
    assertThat(account.getSummary(),is("Your Account Number:- 1111-1111 \nYour name:- Rahul \nYour balance:- 1000.00"));
  }

  @Test
  public void shouldCheckNameOfAnAccount() {
    assertThat(account.getName(),is("Rahul"));
  }
}