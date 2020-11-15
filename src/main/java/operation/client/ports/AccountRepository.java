package operation.client.ports;

import operation.client.model.Account;
import io.vavr.control.Option;

public interface AccountRepository {

    Account createAccount(Account account);

    Option<Account> getAccount(String id);
}
