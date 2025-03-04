package com.paypal.sellers.bankaccountextract.service.impl;

import com.hyperwallet.clientsdk.model.HyperwalletBankAccount;
import com.mirakl.client.core.error.MiraklErrorResponseBean;
import com.mirakl.client.core.exception.MiraklApiException;
import com.mirakl.client.mmp.operator.core.MiraklMarketplacePlatformOperatorApiClient;
import com.mirakl.client.mmp.operator.domain.shop.update.MiraklUpdateShop;
import com.mirakl.client.mmp.operator.request.shop.MiraklUpdateShopsRequest;
import com.mirakl.client.mmp.request.additionalfield.MiraklRequestAdditionalFieldValue;
import com.paypal.infrastructure.mail.MailNotificationUtil;
import com.paypal.sellers.infrastructure.utils.MiraklLoggingErrorsUtil;
import com.paypal.sellers.sellersextract.model.SellerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MiraklBankAccountExtractServiceImplTest {

	private static final String TOKEN_VALUE = "tokenValue";

	private static final String ERROR_MESSAGE_PREFIX = "There was an error, please check the logs for further "
			+ "information:\n";

	private MiraklBankAccountExtractServiceImpl testObj;

	@Mock
	private MiraklMarketplacePlatformOperatorApiClient miraklMarketplacePlatformOperatorApiClientMock;

	@Mock
	private HyperwalletBankAccount hyperwalletBankAccount;

	@Mock
	private SellerModel sellerModelMock;

	@Mock
	private MailNotificationUtil mailNotificationUtilMock;

	@Captor
	private ArgumentCaptor<MiraklUpdateShopsRequest> miraklUpdateShopsRequestCaptor;

	@BeforeEach
	void setUp() {
		testObj = new MiraklBankAccountExtractServiceImpl(miraklMarketplacePlatformOperatorApiClientMock,
				mailNotificationUtilMock);
	}

	@DisplayName("Should Update Value for Custom Field 'hw-bankaccount-token'")
	@Test
	void updateBankAccountToken_shouldUpdateValueForCustomFieldHwBankAccountToken() {
		when(hyperwalletBankAccount.getToken()).thenReturn(TOKEN_VALUE);
		when(sellerModelMock.getClientUserId()).thenReturn("12345");

		testObj.updateBankAccountToken(sellerModelMock, hyperwalletBankAccount);

		verify(miraklMarketplacePlatformOperatorApiClientMock).updateShops(miraklUpdateShopsRequestCaptor.capture());
		final MiraklUpdateShopsRequest miraklUpdateShopsRequest = miraklUpdateShopsRequestCaptor.getValue();
		assertThat(miraklUpdateShopsRequest.getShops()).hasSize(1);
		final MiraklUpdateShop shopToUpdate = miraklUpdateShopsRequest.getShops().get(0);
		assertThat(shopToUpdate).hasFieldOrPropertyWithValue("shopId", 12345L);
		assertThat(shopToUpdate.getAdditionalFieldValues()).hasSize(1);
		final MiraklRequestAdditionalFieldValue additionalFieldValue = shopToUpdate.getAdditionalFieldValues().get(0);
		assertThat(additionalFieldValue)
				.isInstanceOf(MiraklRequestAdditionalFieldValue.MiraklSimpleRequestAdditionalFieldValue.class);
		final var castedAdditionalFieldValue = (MiraklRequestAdditionalFieldValue.MiraklSimpleRequestAdditionalFieldValue) additionalFieldValue;
		assertThat(castedAdditionalFieldValue.getCode()).isEqualTo("hw-bankaccount-token");
		assertThat(castedAdditionalFieldValue.getValue()).isEqualTo(TOKEN_VALUE);

	}

	@Test
	void updateBankAccountToken_shouldSendEmailNotification_whenMiraklExceptionIsThrown() {
		when(hyperwalletBankAccount.getToken()).thenReturn(TOKEN_VALUE);
		when(sellerModelMock.getClientUserId()).thenReturn("12345");

		final var miraklApliException = new MiraklApiException(new MiraklErrorResponseBean(1, "Something went wrong"));
		doThrow(miraklApliException).when(miraklMarketplacePlatformOperatorApiClientMock)
				.updateShops(any(MiraklUpdateShopsRequest.class));

		testObj.updateBankAccountToken(sellerModelMock, hyperwalletBankAccount);

		verify(mailNotificationUtilMock).sendPlainTextEmail(eq("Issue detected updating bank token in Mirakl"),
				eq(String.format(ERROR_MESSAGE_PREFIX + "Something went wrong updating bank token of shop [12345]%n%s",
						MiraklLoggingErrorsUtil.stringify(miraklApliException))));
	}

}
