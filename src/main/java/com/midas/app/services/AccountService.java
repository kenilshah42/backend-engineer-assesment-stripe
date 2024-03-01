package com.midas.app.services;

import com.midas.app.models.Account;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  Account createAccount(Account details);

  /**
   * getAccounts returns a list of accounts.
   *
   * @return List<Account>
   */
  List<Account> getAccounts();

  /**
   * getAccount returns an account by providing id
   *
   * @param accountId
   * @return Optional<Account>
   */
  Optional<Account> getAccount(UUID accountId);

  /**
   * updateAccount updates an account details in system
   *
   * @param details
   * @return Account
   */
  Account updateAccount(Account details);
}
