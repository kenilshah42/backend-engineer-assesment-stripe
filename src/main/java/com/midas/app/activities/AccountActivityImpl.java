package com.midas.app.activities;

import com.midas.app.models.Account;
import com.midas.app.providers.payment.PaymentProvider;
import com.midas.app.repositories.AccountRepository;
import com.midas.app.workflows.CreateAccountWorkflow;
import com.midas.app.workflows.UpdateAccountWorkflow;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ActivityImpl(taskQueues = {CreateAccountWorkflow.QUEUE_NAME, UpdateAccountWorkflow.QUEUE_NAME})
@Service
public class AccountActivityImpl implements AccountActivity {

  @Autowired AccountRepository accountRepository;

  @Autowired PaymentProvider stripePaymentProvider;

  @Override
  public Account saveAccount(Account account) {
    return this.accountRepository.save(account);
  }

  @Override
  public Account createPaymentAccount(Account account) {
    return this.stripePaymentProvider.createAccount(account);
  }

  @Override
  public Account updateAccount(Account account) {
    return this.accountRepository.save(account);
  }
}
