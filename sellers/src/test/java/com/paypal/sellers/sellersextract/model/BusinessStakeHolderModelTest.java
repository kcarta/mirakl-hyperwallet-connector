package com.paypal.sellers.sellersextract.model;

import com.callibrity.logging.test.LogTracker;
import com.callibrity.logging.test.LogTrackerStub;
import com.mirakl.client.mmp.domain.common.MiraklAdditionalFieldValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.paypal.sellers.sellersextract.model.SellerModelConstants.HYPERWALLET_USER_TOKEN;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BusinessStakeHolderModelTest {

	private static final String USER_TOKEN = "userToken";

	private static final String HYPERWALLET_PROGRAM = "hyperwalletProgram";

	@RegisterExtension
	LogTrackerStub logTrackerStub = LogTrackerStub.create().recordForLevel(LogTracker.LogLevel.WARN)
			.recordForType(BusinessStakeHolderModel.class);

	private static final Integer BUSINESS_STAKE_HOLDER_NUMBER = 1;

	private static final Integer INVALID_BUSINESS_STAKE_HOLDER_NUMBER = 0;

	private static final String DATE_OF_BIRTH = "1970-10-10T10:00:00Z";

	private static final String TOKEN = "hw-stakeholder-token-1";

	private static final String BUSINESS = "hw-stakeholder-business-contact-1";

	private static final String DIRECTOR = "hw-stakeholder-director-1";

	private static final String UBO = "hw-stakeholder-ubo-1";

	private static final String SMO = "hw-stakeholder-smo-1";

	private static final String FIRST_NAME = "hw-stakeholder-first-name-1";

	private static final String MIDDLE_NAME = "hw-stakeholder-middle-name-1";

	private static final String LAST_NAME = "hw-stakeholder-last-name-1";

	private static final String DOB = "hw-stakeholder-dob-1";

	private static final String COUNTRY_OF_BIRTH = "hw-stakeholder-country-of-birth-1";

	private static final String NATIONALITY = "hw-stakeholder-nationality-1";

	private static final String GENDER = "hw-stakeholder-gender-1";

	private static final String PHONE_NUMBER = "hw-stakeholder-phone-number-1";

	private static final String MOBILE_NUMBER = "hw-stakeholder-mobile-number-1";

	private static final String EMAIL = "hw-stakeholder-email-1";

	private static final String ADDRESS_LINE_1 = "hw-stakeholder-address-line1-1";

	private static final String ADDRESS_LINE_2 = "hw-stakeholder-address-line2-1";

	private static final String CITY = "hw-stakeholder-city-1";

	private static final String STATE = "hw-stakeholder-state-1";

	private static final String POST_CODE = "hw-stakeholder-post-code-1";

	private static final String COUNTRY = "hw-stakeholder-country-1";

	private static final String GOVERNMENT_ID_TYPE = "hw-stakeholder-government-id-type-1";

	private static final String GOVERNMENT_ID_NUM = "hw-stakeholder-government-id-num-1";

	private static final String DRIVERS_LICENSE_NUM = "hw-stakeholder-drivers-license-num-1";

	private static final String US = "US";

	private static final int STK_ID = 1;

	@Test
	void token_whenMiraklTokenBusinessStakeHolderFieldValueHasAValue_shouldSetToken() {
		final var tokenBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		tokenBusinessStakeHolderField.setCode(TOKEN);
		tokenBusinessStakeHolderField.setValue("token");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.token(List.of(tokenBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getToken()).isEqualTo("token");
	}

	@Test
	void token_whenMiraklTokenBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetToken() {
		final var tokenBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		tokenBusinessStakeHolderField.setCode(TOKEN);
		tokenBusinessStakeHolderField.setValue("token");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.token(List.of(tokenBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void businessContact_whenMiraklBusinessContactBusinessStakeHolderFieldValueHasAValue_shouldSetBusinessContact() {
		final var businessContactBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		businessContactBusinessStakeHolderField.setCode(BUSINESS);
		businessContactBusinessStakeHolderField.setValue("true");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.businessContact(List.of(businessContactBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getBusinessContact()).isTrue();
	}

	@Test
	void businessContact_whenMiraklBusinessContactBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetBusinessContact() {
		final var businessContactBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		businessContactBusinessStakeHolderField.setCode(BUSINESS);
		businessContactBusinessStakeHolderField.setValue("true");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.businessContact(List.of(businessContactBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void director_whenMiraklDirectorBusinessStakeHolderFieldValueHasAValue_shouldSetDirector() {
		final var directorBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		directorBusinessStakeHolderField.setCode(DIRECTOR);
		directorBusinessStakeHolderField.setValue("true");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.director(List.of(directorBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getDirector()).isTrue();
	}

	@Test
	void director_whenMiraklDirectorBusinessStakeHolderFieldValueHasAValueHasAValueButHolderNumberIsNotValid_shouldNotSetDirector() {
		final var directorBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		directorBusinessStakeHolderField.setCode(DIRECTOR);
		directorBusinessStakeHolderField.setValue("true");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.director(List.of(directorBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void ubo_whenMiraklUboBusinessStakeHolderFieldValueHasAValue_shouldSetUbo() {
		final var uboBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		uboBusinessStakeHolderField.setCode(UBO);
		uboBusinessStakeHolderField.setValue("true");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.ubo(List.of(uboBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getUbo()).isTrue();
	}

	@Test
	void ubo_whenMiraklUboBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetUbo() {
		final var uboBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		uboBusinessStakeHolderField.setCode(UBO);
		uboBusinessStakeHolderField.setValue("true");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.ubo(List.of(uboBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void smo_whenMiraklSmoBusinessStakeHolderFieldValueHasAValue_shouldSetSmo() {
		final var smoBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		smoBusinessStakeHolderField.setCode(SMO);
		smoBusinessStakeHolderField.setValue("true");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.smo(List.of(smoBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getSmo()).isTrue();
	}

	@Test
	void smo_whenMiraklSmoBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetSmo() {
		final var smoBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		smoBusinessStakeHolderField.setCode(SMO);
		smoBusinessStakeHolderField.setValue("true");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.smo(List.of(smoBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void firstName_whenMiraklFirstNameBusinessStakeHolderFieldValueHasAValue_shouldSetFirstName() {
		final var firstNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		firstNameBusinessStakeHolderField.setCode(FIRST_NAME);
		firstNameBusinessStakeHolderField.setValue("firstName");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.firstName(List.of(firstNameBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getFirstName()).isEqualTo("firstName");
	}

	@Test
	void firstName_whenMiraklFirstNameBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetFirstName() {
		final var firstNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		firstNameBusinessStakeHolderField.setCode(FIRST_NAME);
		firstNameBusinessStakeHolderField.setValue("firstName");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.firstName(List.of(firstNameBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void middleName_whenMiraklMiddleNameBusinessStakeHolderFieldValueHasAValue_shouldSetMiddleName() {
		final var middleNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		middleNameBusinessStakeHolderField.setCode(MIDDLE_NAME);
		middleNameBusinessStakeHolderField.setValue("middleName");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.middleName(List.of(middleNameBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getMiddleName()).isEqualTo("middleName");
	}

	@Test
	void middleName_whenMiraklMiddleNameBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetMiddleName() {
		final var middleNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		middleNameBusinessStakeHolderField.setCode(MIDDLE_NAME);
		middleNameBusinessStakeHolderField.setValue("middleName");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.middleName(List.of(middleNameBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void lastName_whenMiraklLastNameBusinessStakeHolderFieldValueHasAValue_shouldSetLastName() {
		final var lastNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		lastNameBusinessStakeHolderField.setCode(LAST_NAME);
		lastNameBusinessStakeHolderField.setValue("lastName");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.lastName(List.of(lastNameBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getLastName()).isEqualTo("lastName");
	}

	@Test
	void lastName_whenMiraklLastNameBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetLastName() {
		final var lastNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		lastNameBusinessStakeHolderField.setCode(LAST_NAME);
		lastNameBusinessStakeHolderField.setValue("lastName");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.lastName(List.of(lastNameBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void dateOfBirth_whenMiraklDateOfBirthBusinessStakeHolderFieldValueHasAValue_shouldSetDateOfBirth() {
		final var dateOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklDateAdditionalFieldValue();
		dateOfBirthBusinessStakeHolderField.setCode(DOB);
		dateOfBirthBusinessStakeHolderField.setValue(DATE_OF_BIRTH);
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.dateOfBirth(List.of(dateOfBirthBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getDateOfBirth()).hasYear(1970).hasMonth(10).hasDayOfMonth(10);
	}

	@Test
	void dateOfBirth_whenMiraklDateOfBirthBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetDateOfBirth() {
		final var dateOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklDateAdditionalFieldValue();
		dateOfBirthBusinessStakeHolderField.setCode(DOB);
		dateOfBirthBusinessStakeHolderField.setValue(DATE_OF_BIRTH);
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.dateOfBirth(List.of(dateOfBirthBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void countryOfBirth_whenMiraklCountryOfBirthBusinessStakeHolderFieldValueHasAValue_shouldSetCountryOfBirth() {
		final var countryOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfBirthBusinessStakeHolderField.setCode(COUNTRY_OF_BIRTH);
		countryOfBirthBusinessStakeHolderField.setValue(US);
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.countryOfBirth(List.of(countryOfBirthBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getCountryOfBirth()).isEqualTo("US");
	}

	@Test
	void countryOfBirth_whenMiraklCountryOfBirthBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetCountryOfBirth() {
		final var countryOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfBirthBusinessStakeHolderField.setCode(COUNTRY_OF_BIRTH);
		countryOfBirthBusinessStakeHolderField.setValue(US);
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.countryOfBirth(List.of(countryOfBirthBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void countryOfNacionality_whenMiraklCountryOfNationalityBusinessStakeHolderFieldValueHasAValue_shouldSetCountryOfNationality() {
		final var countryOfNacionalityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfNacionalityBusinessStakeHolderField.setCode(NATIONALITY);
		countryOfNacionalityBusinessStakeHolderField.setValue(US);
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.countryOfNationality(List.of(countryOfNacionalityBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getCountryOfNationality()).isEqualTo("US");
	}

	@Test
	void countryOfNacionality_whenMiraklCountryOfNationalityBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetCountryOfNationality() {
		final var countryOfNacionalityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfNacionalityBusinessStakeHolderField.setCode(NATIONALITY);
		countryOfNacionalityBusinessStakeHolderField.setValue(US);
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.countryOfNationality(List.of(countryOfNacionalityBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void gender_whenMiraklGenderBusinessStakeHolderFieldValueHasAValue_shouldSetGender() {
		final var genderBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		genderBusinessStakeHolderField.setCode(GENDER);
		genderBusinessStakeHolderField.setValue("MALE");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.gender(List.of(genderBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getGender()).isEqualTo(SellerGender.MALE);
	}

	@Test
	void gender_whenMiraklGenderBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetGender() {
		final var genderBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		genderBusinessStakeHolderField.setCode(GENDER);
		genderBusinessStakeHolderField.setValue("MALE");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.gender(List.of(genderBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void phoneNumber_whenMiraklPhoneNumberBusinessStakeHolderFieldValueHasAValue_shouldSetPhoneNumber() {
		final var phoneNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		phoneNumberBusinessStakeHolderField.setCode(PHONE_NUMBER);
		phoneNumberBusinessStakeHolderField.setValue("phoneNumber");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.phoneNumber(List.of(phoneNumberBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getPhoneNumber()).isEqualTo("phoneNumber");
	}

	@Test
	void phoneNumber_whenMiraklPhoneNumberBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetPhoneNumber() {
		final var phoneNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		phoneNumberBusinessStakeHolderField.setCode(PHONE_NUMBER);
		phoneNumberBusinessStakeHolderField.setValue("phoneNumber");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.phoneNumber(List.of(phoneNumberBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void mobileNumber_whenMiraklMobileNumberBusinessStakeHolderFieldValueHasAValue_shouldSetMobileNumber() {
		final var mobileNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		mobileNumberBusinessStakeHolderField.setCode(MOBILE_NUMBER);
		mobileNumberBusinessStakeHolderField.setValue("mobileNumber");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.mobileNumber(List.of(mobileNumberBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getMobileNumber()).isEqualTo("mobileNumber");
	}

	@Test
	void mobileNumber_whenMiraklMobileNumberBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetMobileNumber() {
		final var mobileNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		mobileNumberBusinessStakeHolderField.setCode(MOBILE_NUMBER);
		mobileNumberBusinessStakeHolderField.setValue("mobileNumber");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.mobileNumber(List.of(mobileNumberBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void email_whenMiraklEmailBusinessStakeHolderFieldValueHasAValue_shouldSetEmail() {
		final var emailBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		emailBusinessStakeHolderField.setCode(EMAIL);
		emailBusinessStakeHolderField.setValue("email");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.email(List.of(emailBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getEmail()).isEqualTo("email");
	}

	@Test
	void email_whenMiraklEmailBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetEmail() {
		final var emailBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		emailBusinessStakeHolderField.setCode(EMAIL);
		emailBusinessStakeHolderField.setValue("email");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.email(List.of(emailBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void governmentId_whenMiraklGovernmentIdBusinessStakeHolderFieldValueHasAValue_shouldSetGovernmentId() {
		final var governmentIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		governmentIdBusinessStakeHolderField.setCode(GOVERNMENT_ID_NUM);
		governmentIdBusinessStakeHolderField.setValue("governmentId");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.governmentId(List.of(governmentIdBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void governmentIdType_whenMiraklGovernmentIdTypeBusinessStakeHolderFieldValueHasAValue_shouldSetGovernmentIdType() {
		final var governmentIdTypeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		governmentIdTypeBusinessStakeHolderField.setCode(GOVERNMENT_ID_TYPE);
		governmentIdTypeBusinessStakeHolderField.setValue("PASSPORT");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.governmentIdType(List.of(governmentIdTypeBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getGovernmentIdType()).isEqualTo(SellerGovernmentIdType.PASSPORT);
	}

	@Test
	void governmentIdType_whenMiraklGovernmentIdTypeBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetGovernmentIdType() {
		final var governmentIdTypeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		governmentIdTypeBusinessStakeHolderField.setCode(GOVERNMENT_ID_TYPE);
		governmentIdTypeBusinessStakeHolderField.setValue("PASSPORT");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.governmentIdType(List.of(governmentIdTypeBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void driversLicenseId_whenMiraklDriversLicenseIdBusinessStakeHolderFieldValueHasAValue_shouldSetDriversLicenseId() {
		final var driversLicenseIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		driversLicenseIdBusinessStakeHolderField.setCode(DRIVERS_LICENSE_NUM);
		driversLicenseIdBusinessStakeHolderField.setValue("driversLicenseId");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.driversLicenseId(List.of(driversLicenseIdBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getDriversLicenseId()).isEqualTo("driversLicenseId");
	}

	@Test
	void driversLicenseId_whenMiraklDriversLicenseIdBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetDriversLicenseId() {
		final var driversLicenseIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		driversLicenseIdBusinessStakeHolderField.setCode(DRIVERS_LICENSE_NUM);
		driversLicenseIdBusinessStakeHolderField.setValue("driversLicenseId");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.driversLicenseId(List.of(driversLicenseIdBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void addressLine1_whenMiraklAddressLine1BusinessStakeHolderFieldValueHasAValue_shouldSetAddressLine1() {
		final var addressLine1BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine1BusinessStakeHolderField.setCode(ADDRESS_LINE_1);
		addressLine1BusinessStakeHolderField.setValue("addressLine1");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.addressLine1(List.of(addressLine1BusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getAddressLine1()).isEqualTo("addressLine1");
	}

	@Test
	void addressLine1_whenMiraklAddressLine1BusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetAddressLine1() {
		final var addressLine1BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine1BusinessStakeHolderField.setCode(ADDRESS_LINE_1);
		addressLine1BusinessStakeHolderField.setValue("addressLine1");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.addressLine1(List.of(addressLine1BusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void addressLine2_whenMiraklAddressLine2BusinessStakeHolderFieldValueHasAValue_shouldSetAddressLine2() {
		final var addressLine2BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine2BusinessStakeHolderField.setCode(ADDRESS_LINE_2);
		addressLine2BusinessStakeHolderField.setValue("addressLine2");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.addressLine2(List.of(addressLine2BusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getAddressLine2()).isEqualTo("addressLine2");
	}

	@Test
	void addressLine2_whenMiraklAddressLine2BusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetAddressLine2() {
		final var addressLine2BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine2BusinessStakeHolderField.setCode(ADDRESS_LINE_2);
		addressLine2BusinessStakeHolderField.setValue("addressLine2");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.addressLine2(List.of(addressLine2BusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void city_whenMiraklCityBusinessStakeHolderFieldValueHasAValue_shouldSetCity() {
		final var cityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		cityBusinessStakeHolderField.setCode(CITY);
		cityBusinessStakeHolderField.setValue("city");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.city(List.of(cityBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getCity()).isEqualTo("city");
	}

	@Test
	void city_whenMiraklCityBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetCity() {
		final var cityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		cityBusinessStakeHolderField.setCode(CITY);
		cityBusinessStakeHolderField.setValue("city");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.city(List.of(cityBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void stateProvince_whenMiraklStateProvinceBusinessStakeHolderFieldValueHasAValue_shouldStateProvince() {
		final var stateProvinceBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		stateProvinceBusinessStakeHolderField.setCode(STATE);
		stateProvinceBusinessStakeHolderField.setValue("stateProvince");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.stateProvince(List.of(stateProvinceBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getStateProvince()).isEqualTo("stateProvince");
	}

	@Test
	void stateProvince_whenMiraklStateProvinceBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetStateProvince() {
		final var stateProvinceBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		stateProvinceBusinessStakeHolderField.setCode(STATE);
		stateProvinceBusinessStakeHolderField.setValue("stateProvince");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.stateProvince(List.of(stateProvinceBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void country_whenMiraklCountryBusinessStakeHolderFieldValueHasAValue_shouldSetCountry() {
		final var countryBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryBusinessStakeHolderField.setCode(COUNTRY);
		countryBusinessStakeHolderField.setValue(US);
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.country(List.of(countryBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getCountry()).isEqualTo("US");
	}

	@Test
	void country_whenMiraklCountryBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetCountry() {
		final var countryBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryBusinessStakeHolderField.setCode(COUNTRY);
		countryBusinessStakeHolderField.setValue(US);
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.country(List.of(countryBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void postalCode_whenMiraklPostalCodeBusinessStakeHolderFieldValueHasAValue_shouldSetPostalCode() {
		final var postalCodeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		postalCodeBusinessStakeHolderField.setCode(POST_CODE);
		postalCodeBusinessStakeHolderField.setValue("postalCode");
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.postalCode(List.of(postalCodeBusinessStakeHolderField), BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(result.getPostalCode()).isEqualTo("postalCode");
	}

	@Test
	void postalCode_whenMiraklPostalCodeBusinessStakeHolderFieldValueHasAValueButHolderNumberIsNotValid_shouldNotSetPostalCode() {
		final var postalCodeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		postalCodeBusinessStakeHolderField.setCode(POST_CODE);
		postalCodeBusinessStakeHolderField.setValue("postalCode");
		//@formatter:off
		BusinessStakeHolderModel.builder()
				.postalCode(List.of(postalCodeBusinessStakeHolderField), INVALID_BUSINESS_STAKE_HOLDER_NUMBER)
				.build();
		//@formatter:on

		assertThat(logTrackerStub.contains(
				"Business Stake Holder number 0 incorrect. System only allows Business stake holder attributes from 1 to 5"))
						.isTrue();
	}

	@Test
	void toBuilder() {

		final var tokenBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		tokenBusinessStakeHolderField.setCode(TOKEN);
		tokenBusinessStakeHolderField.setValue("token");

		final var businessContactBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		businessContactBusinessStakeHolderField.setCode(BUSINESS);
		businessContactBusinessStakeHolderField.setValue("true");

		final var directorBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		directorBusinessStakeHolderField.setCode(DIRECTOR);
		directorBusinessStakeHolderField.setValue("true");

		final var uboBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		uboBusinessStakeHolderField.setCode(UBO);
		uboBusinessStakeHolderField.setValue("true");

		final var smoBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		smoBusinessStakeHolderField.setCode(SMO);
		smoBusinessStakeHolderField.setValue("true");

		final var firstNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		firstNameBusinessStakeHolderField.setCode(FIRST_NAME);
		firstNameBusinessStakeHolderField.setValue("firstName");

		final var middleNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		middleNameBusinessStakeHolderField.setCode(MIDDLE_NAME);
		middleNameBusinessStakeHolderField.setValue("middleName");

		final var lastNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		lastNameBusinessStakeHolderField.setCode(LAST_NAME);
		lastNameBusinessStakeHolderField.setValue("lastName");

		final var dateOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklDateAdditionalFieldValue();
		dateOfBirthBusinessStakeHolderField.setCode(DOB);
		dateOfBirthBusinessStakeHolderField.setValue(DATE_OF_BIRTH);

		final var countryOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfBirthBusinessStakeHolderField.setCode(COUNTRY_OF_BIRTH);
		countryOfBirthBusinessStakeHolderField.setValue(US);

		final var countryOfNacionalityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfNacionalityBusinessStakeHolderField.setCode(NATIONALITY);
		countryOfNacionalityBusinessStakeHolderField.setValue(US);

		final var genderBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		genderBusinessStakeHolderField.setCode(GENDER);
		genderBusinessStakeHolderField.setValue("MALE");

		final var phoneNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		phoneNumberBusinessStakeHolderField.setCode(PHONE_NUMBER);
		phoneNumberBusinessStakeHolderField.setValue("phoneNumber");

		final var mobileNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		mobileNumberBusinessStakeHolderField.setCode(MOBILE_NUMBER);
		mobileNumberBusinessStakeHolderField.setValue("mobileNumber");

		final var emailBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		emailBusinessStakeHolderField.setCode(EMAIL);
		emailBusinessStakeHolderField.setValue("email");

		final var governmentIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		governmentIdBusinessStakeHolderField.setCode(GOVERNMENT_ID_NUM);
		governmentIdBusinessStakeHolderField.setValue("governmentId");

		final var governmentIdTypeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		governmentIdTypeBusinessStakeHolderField.setCode(GOVERNMENT_ID_TYPE);
		governmentIdTypeBusinessStakeHolderField.setValue("PASSPORT");

		final var driversLicenseIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		driversLicenseIdBusinessStakeHolderField.setCode(DRIVERS_LICENSE_NUM);
		driversLicenseIdBusinessStakeHolderField.setValue("driversLicenseId");

		final var addressLine1BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine1BusinessStakeHolderField.setCode(ADDRESS_LINE_1);
		addressLine1BusinessStakeHolderField.setValue("addressLine1");

		final var addressLine2BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine2BusinessStakeHolderField.setCode(ADDRESS_LINE_2);
		addressLine2BusinessStakeHolderField.setValue("addressLine2");

		final var cityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		cityBusinessStakeHolderField.setCode(CITY);
		cityBusinessStakeHolderField.setValue("city");

		final var stateProvinceBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		stateProvinceBusinessStakeHolderField.setCode(STATE);
		stateProvinceBusinessStakeHolderField.setValue("stateProvince");

		final var countryBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryBusinessStakeHolderField.setCode(COUNTRY);
		countryBusinessStakeHolderField.setValue(US);

		final var postalCodeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		postalCodeBusinessStakeHolderField.setCode(POST_CODE);
		postalCodeBusinessStakeHolderField.setValue("postalCode");

		final BusinessStakeHolderModel testObj = BusinessStakeHolderModel.builder().build();

		//@formatter:off
		final BusinessStakeHolderModel result = testObj.toBuilder()
				.stkId(STK_ID)
				.token(List.of(tokenBusinessStakeHolderField), STK_ID)
				.businessContact(List.of(businessContactBusinessStakeHolderField), STK_ID)
				.director(List.of(directorBusinessStakeHolderField), STK_ID)
				.ubo(List.of(uboBusinessStakeHolderField), STK_ID)
				.smo(List.of(smoBusinessStakeHolderField), STK_ID)
				.firstName(List.of(firstNameBusinessStakeHolderField), STK_ID)
				.middleName(List.of(middleNameBusinessStakeHolderField), STK_ID)
				.lastName(List.of(lastNameBusinessStakeHolderField), STK_ID)
				.dateOfBirth(List.of(dateOfBirthBusinessStakeHolderField), STK_ID)
				.countryOfBirth(List.of(countryOfBirthBusinessStakeHolderField), STK_ID)
				.countryOfNationality(List.of(countryOfNacionalityBusinessStakeHolderField), STK_ID)
				.gender(List.of(genderBusinessStakeHolderField), STK_ID)
				.phoneNumber(List.of(phoneNumberBusinessStakeHolderField), STK_ID)
				.mobileNumber(List.of(mobileNumberBusinessStakeHolderField), STK_ID)
				.email(List.of(emailBusinessStakeHolderField), STK_ID)
				.governmentId(List.of(governmentIdBusinessStakeHolderField), STK_ID)
				.governmentIdType(List.of(governmentIdTypeBusinessStakeHolderField), STK_ID)
				.driversLicenseId(List.of(driversLicenseIdBusinessStakeHolderField), STK_ID)
				.addressLine1(List.of(addressLine1BusinessStakeHolderField), STK_ID)
				.addressLine2(List.of(addressLine2BusinessStakeHolderField), STK_ID)
				.city(List.of(cityBusinessStakeHolderField), STK_ID)
				.stateProvince(List.of(stateProvinceBusinessStakeHolderField), STK_ID)
				.country(List.of(countryBusinessStakeHolderField), STK_ID)
				.postalCode(List.of(postalCodeBusinessStakeHolderField), STK_ID)
				.build();

		assertThat(result.getDateOfBirth()).hasYear(1970).hasMonth(10).hasDayOfMonth(10);
		assertThat(result).hasFieldOrPropertyWithValue("stkId", STK_ID)
				.hasFieldOrPropertyWithValue("token", "token")
				.hasFieldOrPropertyWithValue("businessContact", Boolean.TRUE)
				.hasFieldOrPropertyWithValue("director", Boolean.TRUE)
				.hasFieldOrPropertyWithValue("ubo", Boolean.TRUE)
				.hasFieldOrPropertyWithValue("smo", Boolean.TRUE)
				.hasFieldOrPropertyWithValue("firstName", "firstName")
				.hasFieldOrPropertyWithValue("middleName", "middleName")
				.hasFieldOrPropertyWithValue("lastName", "lastName")
				.hasFieldOrPropertyWithValue("countryOfBirth", US)
				.hasFieldOrPropertyWithValue("countryOfNationality", US)
				.hasFieldOrPropertyWithValue("gender", SellerGender.MALE)
				.hasFieldOrPropertyWithValue("phoneNumber", "phoneNumber")
				.hasFieldOrPropertyWithValue("mobileNumber", "mobileNumber")
				.hasFieldOrPropertyWithValue("email", "email")
				.hasFieldOrPropertyWithValue("governmentId", "governmentId")
				.hasFieldOrPropertyWithValue("governmentIdType", SellerGovernmentIdType.PASSPORT)
				.hasFieldOrPropertyWithValue("driversLicenseId", "driversLicenseId")
				.hasFieldOrPropertyWithValue("addressLine1", "addressLine1")
				.hasFieldOrPropertyWithValue("addressLine2", "addressLine2")
				.hasFieldOrPropertyWithValue("city", "city")
				.hasFieldOrPropertyWithValue("stateProvince", "stateProvince")
				.hasFieldOrPropertyWithValue("country", US)
				.hasFieldOrPropertyWithValue("postalCode", "postalCode");
		//@formatter:on
	}

	@Test
	void isEmpty_shouldReturnFalseIfAnyAttributeIsNotNullExceptStkId() {
		final BusinessStakeHolderModel businessStakeHolderModel = BusinessStakeHolderModel.builder().stkId(1)
				.token("token").build();

		final boolean result = businessStakeHolderModel.isEmpty();

		assertThat(result).isFalse();
	}

	@Test
	void isEmpty_shouldReturnTrueIfAnyAttributeIsNotNullExceptStkId() {
		final BusinessStakeHolderModel businessStakeHolderModel = BusinessStakeHolderModel.builder().stkId(1).build();

		final boolean result = businessStakeHolderModel.isEmpty();

		assertThat(result).isTrue();
	}

	@Test
	void isEmpty_shouldReturnFalseWhenAllAttributesAreFilledIn() {

		final var tokenBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		tokenBusinessStakeHolderField.setCode(TOKEN);
		tokenBusinessStakeHolderField.setValue("token");

		final var businessContactBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		businessContactBusinessStakeHolderField.setCode(BUSINESS);
		businessContactBusinessStakeHolderField.setValue("true");

		final var directorBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		directorBusinessStakeHolderField.setCode(DIRECTOR);
		directorBusinessStakeHolderField.setValue("true");

		final var uboBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		uboBusinessStakeHolderField.setCode(UBO);
		uboBusinessStakeHolderField.setValue("true");

		final var smoBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		smoBusinessStakeHolderField.setCode(SMO);
		smoBusinessStakeHolderField.setValue("true");

		final var firstNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		firstNameBusinessStakeHolderField.setCode(FIRST_NAME);
		firstNameBusinessStakeHolderField.setValue("firstName");

		final var middleNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		middleNameBusinessStakeHolderField.setCode(MIDDLE_NAME);
		middleNameBusinessStakeHolderField.setValue("middleName");

		final var lastNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		lastNameBusinessStakeHolderField.setCode(LAST_NAME);
		lastNameBusinessStakeHolderField.setValue("lastName");

		final var dateOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklDateAdditionalFieldValue();
		dateOfBirthBusinessStakeHolderField.setCode(DOB);
		dateOfBirthBusinessStakeHolderField.setValue(DATE_OF_BIRTH);

		final var countryOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfBirthBusinessStakeHolderField.setCode(COUNTRY_OF_BIRTH);
		countryOfBirthBusinessStakeHolderField.setValue(US);

		final var countryOfNacionalityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfNacionalityBusinessStakeHolderField.setCode(NATIONALITY);
		countryOfNacionalityBusinessStakeHolderField.setValue(US);

		final var genderBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		genderBusinessStakeHolderField.setCode(GENDER);
		genderBusinessStakeHolderField.setValue("MALE");

		final var phoneNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		phoneNumberBusinessStakeHolderField.setCode(PHONE_NUMBER);
		phoneNumberBusinessStakeHolderField.setValue("phoneNumber");

		final var mobileNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		mobileNumberBusinessStakeHolderField.setCode(MOBILE_NUMBER);
		mobileNumberBusinessStakeHolderField.setValue("mobileNumber");

		final var emailBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		emailBusinessStakeHolderField.setCode(EMAIL);
		emailBusinessStakeHolderField.setValue("email");

		final var governmentIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		governmentIdBusinessStakeHolderField.setCode(GOVERNMENT_ID_NUM);
		governmentIdBusinessStakeHolderField.setValue("governmentId");

		final var governmentIdTypeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		governmentIdTypeBusinessStakeHolderField.setCode(GOVERNMENT_ID_TYPE);
		governmentIdTypeBusinessStakeHolderField.setValue("PASSPORT");

		final var driversLicenseIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		driversLicenseIdBusinessStakeHolderField.setCode(DRIVERS_LICENSE_NUM);
		driversLicenseIdBusinessStakeHolderField.setValue("driversLicenseId");

		final var addressLine1BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine1BusinessStakeHolderField.setCode(ADDRESS_LINE_1);
		addressLine1BusinessStakeHolderField.setValue("addressLine1");

		final var addressLine2BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine2BusinessStakeHolderField.setCode(ADDRESS_LINE_2);
		addressLine2BusinessStakeHolderField.setValue("addressLine2");

		final var cityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		cityBusinessStakeHolderField.setCode(CITY);
		cityBusinessStakeHolderField.setValue("city");

		final var stateProvinceBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		stateProvinceBusinessStakeHolderField.setCode(STATE);
		stateProvinceBusinessStakeHolderField.setValue("stateProvince");

		final var countryBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryBusinessStakeHolderField.setCode(COUNTRY);
		countryBusinessStakeHolderField.setValue(US);

		final var postalCodeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		postalCodeBusinessStakeHolderField.setCode(POST_CODE);
		postalCodeBusinessStakeHolderField.setValue("postalCode");

		final BusinessStakeHolderModel testObj = BusinessStakeHolderModel.builder().build();

		//@formatter:off
		final BusinessStakeHolderModel businessStakeHolderStub = testObj.toBuilder()
				.stkId(STK_ID)
				.token(List.of(tokenBusinessStakeHolderField), STK_ID)
				.businessContact(List.of(businessContactBusinessStakeHolderField), STK_ID)
				.director(List.of(directorBusinessStakeHolderField), STK_ID)
				.ubo(List.of(uboBusinessStakeHolderField), STK_ID)
				.smo(List.of(smoBusinessStakeHolderField), STK_ID)
				.firstName(List.of(firstNameBusinessStakeHolderField), STK_ID)
				.middleName(List.of(middleNameBusinessStakeHolderField), STK_ID)
				.lastName(List.of(lastNameBusinessStakeHolderField), STK_ID)
				.dateOfBirth(List.of(dateOfBirthBusinessStakeHolderField), STK_ID)
				.countryOfBirth(List.of(countryOfBirthBusinessStakeHolderField), STK_ID)
				.countryOfNationality(List.of(countryOfNacionalityBusinessStakeHolderField), STK_ID)
				.gender(List.of(genderBusinessStakeHolderField), STK_ID)
				.phoneNumber(List.of(phoneNumberBusinessStakeHolderField), STK_ID)
				.mobileNumber(List.of(mobileNumberBusinessStakeHolderField), STK_ID)
				.email(List.of(emailBusinessStakeHolderField), STK_ID)
				.governmentId(List.of(governmentIdBusinessStakeHolderField), STK_ID)
				.governmentIdType(List.of(governmentIdTypeBusinessStakeHolderField), STK_ID)
				.driversLicenseId(List.of(driversLicenseIdBusinessStakeHolderField), STK_ID)
				.addressLine1(List.of(addressLine1BusinessStakeHolderField), STK_ID)
				.addressLine2(List.of(addressLine2BusinessStakeHolderField), STK_ID)
				.city(List.of(cityBusinessStakeHolderField), STK_ID)
				.stateProvince(List.of(stateProvinceBusinessStakeHolderField), STK_ID)
				.country(List.of(countryBusinessStakeHolderField), STK_ID)
				.postalCode(List.of(postalCodeBusinessStakeHolderField), STK_ID)
				.build();

		final boolean result = businessStakeHolderStub.isEmpty();

		assertThat(result).isFalse();
	}

	@Test
	void isJustCreated_shouldReturnTrueWhenSetToFalse() {

		final BusinessStakeHolderModel businessStakeHolderModelStub = BusinessStakeHolderModel.builder().justCreated(false).build();

		final boolean result = businessStakeHolderModelStub.isJustCreated();

		assertThat(result).isFalse();
	}

	@Test
	void isJustCreated_shouldReturnTrueWhenSetToTrue() {

		final BusinessStakeHolderModel businessStakeHolderModelStub = BusinessStakeHolderModel.builder().justCreated(true).build();

		final boolean result = businessStakeHolderModelStub.isJustCreated();

		assertThat(result).isTrue();
	}

	@Test
	void equals_shouldReturnTrueWhenBothAreEquals() {

		final BusinessStakeHolderModel businessStakeHolderModelOne = createBusinessStakeHolderModelObject();
		final BusinessStakeHolderModel businessStakeHolderModelTwo = businessStakeHolderModelOne.toBuilder().build();

		final boolean result = businessStakeHolderModelOne.equals(businessStakeHolderModelTwo);

		assertThat(result).isTrue();
	}

	@Test
	void equals_shouldReturnFalseWhenBothAreNotEquals() {

		final BusinessStakeHolderModel businessStakeHolderModelOne = createBusinessStakeHolderModelObject();
		final BusinessStakeHolderModel businessStakeHolderModelTwo = businessStakeHolderModelOne.toBuilder().token("newToken").build();

		final boolean result = businessStakeHolderModelOne.equals(businessStakeHolderModelTwo);

		assertThat(result).isFalse();
	}

	@Test
	void equals_shouldReturnTrueWhenSameObjectIsCompared() {

		final BusinessStakeHolderModel businessStakeHolderModelOne = createBusinessStakeHolderModelObject();

		final boolean result = businessStakeHolderModelOne.equals(businessStakeHolderModelOne);

		assertThat(result).isTrue();
	}

	@Test
	void equals_shouldReturnFalseWhenComparedWithAnotherInstanceObject() {

		final BusinessStakeHolderModel businessStakeHolderModelOne = createBusinessStakeHolderModelObject();

		final Object o = new Object();

		final boolean result = businessStakeHolderModelOne.equals(o);

		assertThat(result).isFalse();
	}

	@Test
	void userToken_whenMiraklUserTokenBusinessStakeHolderFieldValueHasAValue_shouldSetUserToken() {
		final var userTokenBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		userTokenBusinessStakeHolderField.setCode(HYPERWALLET_USER_TOKEN);
		userTokenBusinessStakeHolderField.setValue(USER_TOKEN);
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.userToken(List.of(userTokenBusinessStakeHolderField))
				.build();
		//@formatter:on

		assertThat(result.getUserToken()).isEqualTo(USER_TOKEN);
	}

	@Test
	void hyperwalletProgram_whenMiraklHyperwalletProgramBusinessStakeHolderFieldValueHasAValue_shouldSetHyperwalletProgram() {
		final var hyperwalletProgramBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		hyperwalletProgramBusinessStakeHolderField.setCode(SellerModelConstants.HYPERWALLET_PROGRAM);
		hyperwalletProgramBusinessStakeHolderField.setValue(HYPERWALLET_PROGRAM);
		//@formatter:off
		final var result = BusinessStakeHolderModel.builder()
				.hyperwalletProgram(List.of(hyperwalletProgramBusinessStakeHolderField))
				.build();
		//@formatter:on

		assertThat(result.getHyperwalletProgram()).isEqualTo(HYPERWALLET_PROGRAM);
	}

	private BusinessStakeHolderModel createBusinessStakeHolderModelObject() {
		final var tokenBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		tokenBusinessStakeHolderField.setCode(TOKEN);
		tokenBusinessStakeHolderField.setValue("token");

		final var businessContactBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		businessContactBusinessStakeHolderField.setCode(BUSINESS);
		businessContactBusinessStakeHolderField.setValue("true");

		final var directorBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		directorBusinessStakeHolderField.setCode(DIRECTOR);
		directorBusinessStakeHolderField.setValue("true");

		final var uboBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		uboBusinessStakeHolderField.setCode(UBO);
		uboBusinessStakeHolderField.setValue("true");

		final var smoBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklBooleanAdditionalFieldValue();
		smoBusinessStakeHolderField.setCode(SMO);
		smoBusinessStakeHolderField.setValue("true");

		final var firstNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		firstNameBusinessStakeHolderField.setCode(FIRST_NAME);
		firstNameBusinessStakeHolderField.setValue("firstName");

		final var middleNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		middleNameBusinessStakeHolderField.setCode(MIDDLE_NAME);
		middleNameBusinessStakeHolderField.setValue("middleName");

		final var lastNameBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		lastNameBusinessStakeHolderField.setCode(LAST_NAME);
		lastNameBusinessStakeHolderField.setValue("lastName");

		final var dateOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklDateAdditionalFieldValue();
		dateOfBirthBusinessStakeHolderField.setCode(DOB);
		dateOfBirthBusinessStakeHolderField.setValue(DATE_OF_BIRTH);

		final var countryOfBirthBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfBirthBusinessStakeHolderField.setCode(COUNTRY_OF_BIRTH);
		countryOfBirthBusinessStakeHolderField.setValue(US);

		final var countryOfNacionalityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryOfNacionalityBusinessStakeHolderField.setCode(NATIONALITY);
		countryOfNacionalityBusinessStakeHolderField.setValue(US);

		final var genderBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		genderBusinessStakeHolderField.setCode(GENDER);
		genderBusinessStakeHolderField.setValue("MALE");

		final var phoneNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		phoneNumberBusinessStakeHolderField.setCode(PHONE_NUMBER);
		phoneNumberBusinessStakeHolderField.setValue("phoneNumber");

		final var mobileNumberBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		mobileNumberBusinessStakeHolderField.setCode(MOBILE_NUMBER);
		mobileNumberBusinessStakeHolderField.setValue("mobileNumber");

		final var emailBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		emailBusinessStakeHolderField.setCode(EMAIL);
		emailBusinessStakeHolderField.setValue("email");

		final var governmentIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		governmentIdBusinessStakeHolderField.setCode(GOVERNMENT_ID_NUM);
		governmentIdBusinessStakeHolderField.setValue("governmentId");

		final var governmentIdTypeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		governmentIdTypeBusinessStakeHolderField.setCode(GOVERNMENT_ID_TYPE);
		governmentIdTypeBusinessStakeHolderField.setValue("PASSPORT");

		final var driversLicenseIdBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		driversLicenseIdBusinessStakeHolderField.setCode(DRIVERS_LICENSE_NUM);
		driversLicenseIdBusinessStakeHolderField.setValue("driversLicenseId");

		final var addressLine1BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine1BusinessStakeHolderField.setCode(ADDRESS_LINE_1);
		addressLine1BusinessStakeHolderField.setValue("addressLine1");

		final var addressLine2BusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		addressLine2BusinessStakeHolderField.setCode(ADDRESS_LINE_2);
		addressLine2BusinessStakeHolderField.setValue("addressLine2");

		final var cityBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		cityBusinessStakeHolderField.setCode(CITY);
		cityBusinessStakeHolderField.setValue("city");

		final var stateProvinceBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		stateProvinceBusinessStakeHolderField.setCode(STATE);
		stateProvinceBusinessStakeHolderField.setValue("stateProvince");

		final var countryBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		countryBusinessStakeHolderField.setCode(COUNTRY);
		countryBusinessStakeHolderField.setValue(US);

		final var postalCodeBusinessStakeHolderField = new MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue();
		postalCodeBusinessStakeHolderField.setCode(POST_CODE);
		postalCodeBusinessStakeHolderField.setValue("postalCode");

		final var hyperwalletProgramBusinessStakeHolderFiled = new MiraklAdditionalFieldValue.MiraklValueListAdditionalFieldValue();
		hyperwalletProgramBusinessStakeHolderFiled.setCode(SellerModelConstants.HYPERWALLET_PROGRAM);
		hyperwalletProgramBusinessStakeHolderFiled.setValue(HYPERWALLET_PROGRAM);

		//@formatter:off
		return BusinessStakeHolderModel.builder()
				.stkId(STK_ID)
				.token(List.of(tokenBusinessStakeHolderField), STK_ID)
				.businessContact(List.of(businessContactBusinessStakeHolderField), STK_ID)
				.director(List.of(directorBusinessStakeHolderField), STK_ID)
				.ubo(List.of(uboBusinessStakeHolderField), STK_ID)
				.smo(List.of(smoBusinessStakeHolderField), STK_ID)
				.firstName(List.of(firstNameBusinessStakeHolderField), STK_ID)
				.middleName(List.of(middleNameBusinessStakeHolderField), STK_ID)
				.lastName(List.of(lastNameBusinessStakeHolderField), STK_ID)
				.dateOfBirth(List.of(dateOfBirthBusinessStakeHolderField), STK_ID)
				.countryOfBirth(List.of(countryOfBirthBusinessStakeHolderField), STK_ID)
				.countryOfNationality(List.of(countryOfNacionalityBusinessStakeHolderField), STK_ID)
				.gender(List.of(genderBusinessStakeHolderField), STK_ID)
				.phoneNumber(List.of(phoneNumberBusinessStakeHolderField), STK_ID)
				.mobileNumber(List.of(mobileNumberBusinessStakeHolderField), STK_ID)
				.email(List.of(emailBusinessStakeHolderField), STK_ID)
				.governmentId(List.of(governmentIdBusinessStakeHolderField), STK_ID)
				.governmentIdType(List.of(governmentIdTypeBusinessStakeHolderField), STK_ID)
				.driversLicenseId(List.of(driversLicenseIdBusinessStakeHolderField), STK_ID)
				.addressLine1(List.of(addressLine1BusinessStakeHolderField), STK_ID)
				.addressLine2(List.of(addressLine2BusinessStakeHolderField), STK_ID)
				.city(List.of(cityBusinessStakeHolderField), STK_ID)
				.stateProvince(List.of(stateProvinceBusinessStakeHolderField), STK_ID)
				.country(List.of(countryBusinessStakeHolderField), STK_ID)
				.postalCode(List.of(postalCodeBusinessStakeHolderField), STK_ID)
				.hyperwalletProgram(List.of(hyperwalletProgramBusinessStakeHolderFiled))
				.build();
	}
}
