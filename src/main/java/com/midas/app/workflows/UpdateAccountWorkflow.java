package com.midas.app.workflows;

import com.midas.app.models.Account;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface UpdateAccountWorkflow {
  String QUEUE_NAME = "update-account-workflow";

  /**
   * updateAccount updates an account in the system or provider.
   *
   * @param updateDetails is the details of the account to be created.
   * @param fetchedAccount is account fetched by passing account id.
   * @return Account
   */
  @WorkflowMethod
  Account updateAccount(Account updateDetails, Account fetchedAccount);
}
