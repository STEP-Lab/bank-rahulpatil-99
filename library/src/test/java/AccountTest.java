import com.example.bank.Account;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() {
    account = new Account("1111-1111", 1000.00);
  }

  @Test
  public void checkBalance() {
    assertEquals(account.getBalance(),1000.00);
  }
}
