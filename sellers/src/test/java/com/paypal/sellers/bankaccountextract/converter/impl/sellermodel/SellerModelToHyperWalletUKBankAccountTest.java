package com.paypal.sellers.bankaccountextract.converter.impl.sellermodel;

import com.hyperwallet.clientsdk.model.HyperwalletBankAccount;
import com.paypal.sellers.bankaccountextract.model.ABABankAccountModel;
import com.paypal.sellers.bankaccountextract.model.UKBankAccountModel;
import com.paypal.sellers.sellersextract.model.SellerModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerModelToHyperWalletUKBankAccountTest {

	private static final String SORT_CODE = "sortCode";

	@Spy
	@InjectMocks
	private SellerModelToHyperWalletUKBankAccount testObj;

	@Mock
	private SellerModel sellerModelMock;

	@Mock
	private UKBankAccountModel ukBankAccountModelMock;

	@Mock
	private ABABankAccountModel abaBankAccountModelMock;

	@Test
	void convert_shouldReturnUKHyperwalletBankAccount() {
		final HyperwalletBankAccount hyperwalletBankAccount = new HyperwalletBankAccount();

		when(sellerModelMock.getBankAccountDetails()).thenReturn(ukBankAccountModelMock);
		when(ukBankAccountModelMock.getBankAccountId()).thenReturn(SORT_CODE);
		doReturn(hyperwalletBankAccount).when(testObj).callSuperConvert(sellerModelMock);

		final var result = testObj.execute(sellerModelMock);

		assertThat(result.getBankId()).isEqualTo(SORT_CODE);
	}

	@Test
	void isApplicable_shouldReturnTrueWhenBankAccountDetailsIsUKBankAccountType() {
		when(sellerModelMock.getBankAccountDetails()).thenReturn(ukBankAccountModelMock);

		final var result = testObj.isApplicable(sellerModelMock);

		assertThat(result).isTrue();
	}

	@Test
	void isApplicable_shouldReturnTrueWhenBankAccountDetailsIsDifferentFromUKBankAccountType() {
		when(sellerModelMock.getBankAccountDetails()).thenReturn(abaBankAccountModelMock);

		final var result = testObj.isApplicable(sellerModelMock);

		assertThat(result).isFalse();
	}

	@Test
	void isApplicable_shouldReturnFalse_whenNullPaymentInformationIsReceived() {
		when(sellerModelMock.getBankAccountDetails()).thenReturn(null);

		final var result = testObj.isApplicable(sellerModelMock);

		assertThat(result).isFalse();
	}

}
