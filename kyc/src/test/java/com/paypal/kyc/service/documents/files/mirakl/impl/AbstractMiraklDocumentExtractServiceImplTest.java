package com.paypal.kyc.service.documents.files.mirakl.impl;

import com.mirakl.client.mmp.domain.shop.document.MiraklShopDocument;
import com.mirakl.client.mmp.operator.core.MiraklMarketplacePlatformOperatorApiClient;
import com.mirakl.client.mmp.request.shop.document.MiraklDeleteShopDocumentRequest;
import com.paypal.kyc.model.KYCDocumentInfoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractMiraklDocumentExtractServiceImplTest {

	private static final String MIRAKL_SHOP_DOCUMENT_ID1 = "2001";

	private static final String MIRAKL_SHOP_DOCUMENT_ID2 = "2002";

	@InjectMocks
	private MyAbstractMiraklDocumentExtractServiceImpl testObj;

	@Mock
	private MiraklMarketplacePlatformOperatorApiClient miraklOperatorClientMock;

	@Mock
	private KYCDocumentInfoModel kycDocumentInfoModelOneMock, kycDocumentInfoModelTwoMock;

	@Mock
	private MiraklShopDocument miraklShopDocumentOneMock, miraklShopDocumentTwoMock;

	@Test
	void deleteAllDocumentsFromSeller_shouldCallToMiraklDeleteDocuments() {
		when(kycDocumentInfoModelOneMock.getMiraklShopDocuments())
				.thenReturn(List.of(miraklShopDocumentOneMock, miraklShopDocumentTwoMock));
		when(miraklShopDocumentOneMock.getId()).thenReturn(MIRAKL_SHOP_DOCUMENT_ID1);
		when(miraklShopDocumentTwoMock.getId()).thenReturn(MIRAKL_SHOP_DOCUMENT_ID2);

		testObj.deleteAllDocumentsFromSeller(List.of(kycDocumentInfoModelOneMock, kycDocumentInfoModelTwoMock));

		verify(miraklOperatorClientMock, times(2))
				.deleteShopDocument(Mockito.any(MiraklDeleteShopDocumentRequest.class));
	}

	@Test
	void deleteDocuments_whenDocumentListIsNull_shouldNotCallMirakl() {
		testObj.deleteDocuments(null);

		verify(miraklOperatorClientMock, never()).deleteShopDocument(isA(MiraklDeleteShopDocumentRequest.class));
	}

	@Test
	void deleteDocuments_whenDocumentListIsEmpty_shouldNotCallMirakl() {
		testObj.deleteDocuments(List.of());

		verify(miraklOperatorClientMock, never()).deleteShopDocument(isA(MiraklDeleteShopDocumentRequest.class));
	}

	@Test
	void deleteDocuments_whenThereAreDocuments_shouldCallMirakl() {
		when(miraklShopDocumentOneMock.getId()).thenReturn(MIRAKL_SHOP_DOCUMENT_ID1);
		when(miraklShopDocumentTwoMock.getId()).thenReturn(MIRAKL_SHOP_DOCUMENT_ID2);
		testObj.deleteDocuments(List.of(miraklShopDocumentOneMock, miraklShopDocumentTwoMock));

		verify(miraklOperatorClientMock, times(2)).deleteShopDocument(isA(MiraklDeleteShopDocumentRequest.class));
	}

	public static class MyAbstractMiraklDocumentExtractServiceImpl extends AbstractMiraklDocumentExtractServiceImpl {

		protected MyAbstractMiraklDocumentExtractServiceImpl(
				final MiraklMarketplacePlatformOperatorApiClient miraklOperatorClient) {
			super(miraklOperatorClient);
		}

	}

}
