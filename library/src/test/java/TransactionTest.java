import com.example.bank.Transaction;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
  @Test

  public void shouldCheckTransactionAmount() {
    Transaction transaction = new Transaction(1000.00, true);
    assertThat(transaction.getAmount(),is(1000.00));
  }
}
