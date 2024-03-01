package com.midas.app.providers.external.stripe;

import com.midas.app.models.Account;
import com.midas.app.providers.payment.PaymentProvider;
import com.midas.generated.model.AccountDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class StripePaymentProvider implements PaymentProvider {
  private final Logger logger = LoggerFactory.getLogger(StripePaymentProvider.class);

  private final StripeConfiguration configuration;

  /** providerName is the name of the payment provider */
  @Override
  public String providerName() {
    return "stripe";
  }

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account createAccount(Account account) {
    try {
      Stripe.apiKey = configuration.getApiKey();
      CustomerCreateParams params =
          CustomerCreateParams.builder()
              .setName(account.getFirstName() + " " + account.getLastName())
              .setEmail(account.getEmail())
              .build();
      Customer customer = Customer.create(params);
      account.setProviderId(customer.getId());
      account.setProviderType(AccountDto.ProviderTypeEnum.STRIPE);
    } catch (StripeException stripeException) {
      logger.error("An error occured while setting up stripe payment", stripeException);
    }
    return account;
  }
}
