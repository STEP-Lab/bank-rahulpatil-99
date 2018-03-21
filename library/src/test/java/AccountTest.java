import com.example.bank.Account;
import com.example.bank.InvalidAccountNumber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() throws InvalidAccountNumber {
    account = new Account("1111-1111", 1000.00);
  }

  @Test
  public void checkBalance() {
    assertEquals(account.getBalance(),1000.00);
  }

  @Test(expected = InvalidAccountNumber.class)
  public void checkValidAccountNumber() throws InvalidAccountNumber {
    new Account("111-1234",1000.00);
  }

  @Test
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(),is("1111-1111"));
  }

  @Test
  public void shouldCreditAmountIntoAccountBalance() {
    account.credit(500.50);
    assertThat(account.getBalance(),is(1500.50));
  }
}
