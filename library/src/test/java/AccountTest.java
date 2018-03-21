import com.example.bank.Account;
import com.example.bank.InvalidAccountNumber;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
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
}
