package com.paypal.kyc.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.paypal.kyc.model.KYCConstants.BUSINESS_STAKEHOLDER_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class KYCBusinessStakeholderProofOfIdentityEnumTest {

	private final static int BUSINESS_STAKEHODER_NUMBER = 1;

	private final static String PREFIX_FIELD_NAME = KYCConstants.HYPERWALLET_PREFIX + BUSINESS_STAKEHOLDER_PREFIX
			+ BUSINESS_STAKEHODER_NUMBER + "-" + KYCConstants.PROOF_IDENTITY_PREFIX;

	@Test
	void getMiraklFields_shouldReturnProofOfIdentityFrontAndBackWhenProofOfIdentityIsGovernmentId() {

		final List<String> result = KYCProofOfIdentityEnum.getMiraklFields(KYCProofOfIdentityEnum.GOVERNMENT_ID,
				BUSINESS_STAKEHODER_NUMBER);

		assertThat(result).containsExactlyInAnyOrder(PREFIX_FIELD_NAME + KYCConstants.PROOF_IDENTITY_SIDE_FRONT,
				PREFIX_FIELD_NAME + KYCConstants.PROOF_IDENTITY_SIDE_BACK);
	}

	@Test
	void getMiraklFields_shouldReturnProofOfIdentityFrontAndBackWhenProofOfIdentityIsDriversLicense() {

		final List<String> result = KYCProofOfIdentityEnum.getMiraklFields(KYCProofOfIdentityEnum.DRIVERS_LICENSE,
				BUSINESS_STAKEHODER_NUMBER);

		assertThat(result).containsExactlyInAnyOrder(PREFIX_FIELD_NAME + KYCConstants.PROOF_IDENTITY_SIDE_FRONT,
				PREFIX_FIELD_NAME + KYCConstants.PROOF_IDENTITY_SIDE_BACK);
	}

	@Test
	void getMiraklFields_shouldReturnProofOfIdentityFrontWhenProofOfIdentityIsPassport() {

		final List<String> result = KYCProofOfIdentityEnum.getMiraklFields(KYCProofOfIdentityEnum.PASSPORT,
				BUSINESS_STAKEHODER_NUMBER);

		assertThat(result).containsExactlyInAnyOrder(PREFIX_FIELD_NAME + KYCConstants.PROOF_IDENTITY_SIDE_FRONT);
	}

	@Test
	void getMiraklFields_shouldReturnEmptyListWhenProofOfIdentityIsEmpty() {

		final List<String> result = KYCProofOfIdentityEnum.getMiraklFields(null, BUSINESS_STAKEHODER_NUMBER);

		assertThat(result).isEmpty();
	}

}
