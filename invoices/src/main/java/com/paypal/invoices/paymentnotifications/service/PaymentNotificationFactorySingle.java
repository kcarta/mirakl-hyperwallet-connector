package com.paypal.invoices.paymentnotifications.service;

import com.paypal.infrastructure.strategy.SingleAbstractStrategyFactory;
import com.paypal.infrastructure.strategy.Strategy;
import com.paypal.invoices.paymentnotifications.model.PaymentNotificationBodyModel;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Payment notification strategy factory
 */
@Service
public class PaymentNotificationFactorySingle
		extends SingleAbstractStrategyFactory<PaymentNotificationBodyModel, Optional<Void>> {

	private final Set<Strategy<PaymentNotificationBodyModel, Optional<Void>>> strategies;

	public PaymentNotificationFactorySingle(
			final Set<Strategy<PaymentNotificationBodyModel, Optional<Void>>> strategies) {
		this.strategies = strategies;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<Strategy<PaymentNotificationBodyModel, Optional<Void>>> getStrategies() {
		return strategies;
	}

}
