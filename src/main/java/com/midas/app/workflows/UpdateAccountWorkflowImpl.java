package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;

@WorkflowImpl(taskQueues = UpdateAccountWorkflow.QUEUE_NAME)
public class UpdateAccountWorkflowImpl implements UpdateAccountWorkflow {
  private AccountActivity activity =
      Workflow.newActivityStub(
          AccountActivity.class,
          ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(2)).build());

  @Override
  public Account updateAccount(Account updateDetails, Account fetchedAccount) {
    if (updateDetails.getEmail() != null) {
      fetchedAccount.setEmail(updateDetails.getEmail());
    }
    if (updateDetails.getFirstName() != null) {
      fetchedAccount.setFirstName(updateDetails.getFirstName());
    }
    if (updateDetails.getLastName() != null) {
      fetchedAccount.setLastName(updateDetails.getLastName());
    }
    return this.activity.updateAccount(fetchedAccount);
  }
}
