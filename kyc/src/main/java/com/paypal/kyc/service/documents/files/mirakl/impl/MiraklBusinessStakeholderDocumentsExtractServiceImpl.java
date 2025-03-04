package com.paypal.kyc.service.documents.files.mirakl.impl;

import com.mirakl.client.core.exception.MiraklException;
import com.mirakl.client.mmp.domain.common.MiraklAdditionalFieldValue;
import com.mirakl.client.mmp.domain.shop.AbstractMiraklShop;
import com.mirakl.client.mmp.domain.shop.MiraklShop;
import com.mirakl.client.mmp.domain.shop.MiraklShops;
import com.mirakl.client.mmp.operator.core.MiraklMarketplacePlatformOperatorApiClient;
import com.mirakl.client.mmp.operator.domain.shop.update.MiraklUpdateShop;
import com.mirakl.client.mmp.operator.domain.shop.update.MiraklUpdatedShopReturn;
import com.mirakl.client.mmp.operator.domain.shop.update.MiraklUpdatedShops;
import com.mirakl.client.mmp.operator.request.shop.MiraklUpdateShopsRequest;
import com.mirakl.client.mmp.request.additionalfield.MiraklRequestAdditionalFieldValue;
import com.mirakl.client.mmp.request.shop.MiraklGetShopsRequest;
import com.paypal.infrastructure.converter.Converter;
import com.paypal.infrastructure.mail.MailNotificationUtil;
import com.paypal.infrastructure.util.LoggingConstantsUtil;
import com.paypal.infrastructure.util.MiraklLoggingErrorsUtil;
import com.paypal.kyc.converter.KYCBusinessStakeHolderConverter;
import com.paypal.kyc.model.KYCDocumentBusinessStakeHolderInfoModel;
import com.paypal.kyc.model.KYCDocumentInfoModel;
import com.paypal.kyc.service.documents.files.mirakl.MiraklBusinessStakeholderDocumentDownloadExtractService;
import com.paypal.kyc.service.documents.files.mirakl.MiraklBusinessStakeholderDocumentsExtractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.paypal.kyc.model.KYCConstants.*;

/**
 * Implementation of {@link MiraklBusinessStakeholderDocumentsExtractService}
 */
@Slf4j
@Service
public class MiraklBusinessStakeholderDocumentsExtractServiceImpl extends AbstractMiraklDocumentExtractServiceImpl
		implements MiraklBusinessStakeholderDocumentsExtractService {

	private final MiraklBusinessStakeholderDocumentDownloadExtractService miraklBusinessStakeholderDocumentDownloadExtractService;

	private final Converter<Date, MiraklGetShopsRequest> miraklGetShopsRequestConverter;

	private final KYCBusinessStakeHolderConverter kycBusinessStakeHolderConverter;

	private final MiraklMarketplacePlatformOperatorApiClient miraklOperatorClient;

	private final MailNotificationUtil kycMailNotificationUtil;

	public MiraklBusinessStakeholderDocumentsExtractServiceImpl(
			final MiraklBusinessStakeholderDocumentDownloadExtractService miraklBusinessStakeholderDocumentDownloadExtractService,
			final Converter<Date, MiraklGetShopsRequest> miraklGetShopsRequestConverter,
			final KYCBusinessStakeHolderConverter kycBusinessStakeHolderConverter,
			final MiraklMarketplacePlatformOperatorApiClient miraklOperatorClient,
			final MailNotificationUtil kycMailNotificationUtil) {
		super(miraklOperatorClient);
		this.miraklBusinessStakeholderDocumentDownloadExtractService = miraklBusinessStakeholderDocumentDownloadExtractService;
		this.miraklGetShopsRequestConverter = miraklGetShopsRequestConverter;
		this.kycBusinessStakeHolderConverter = kycBusinessStakeHolderConverter;
		this.miraklOperatorClient = miraklOperatorClient;
		this.kycMailNotificationUtil = kycMailNotificationUtil;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KYCDocumentBusinessStakeHolderInfoModel> extractBusinessStakeholderDocuments(final Date delta) {
		final MiraklGetShopsRequest miraklGetShopsRequest = miraklGetShopsRequestConverter.convert(delta);
		log.info("Retrieving modified shops since [{}]", delta);
		final MiraklShops shops = miraklOperatorClient.getShops(miraklGetShopsRequest);

		//@formatter:off
        log.info("Retrieved modified shops since [{}]: [{}]", delta,
                Stream.ofNullable(shops.getShops())
                        .flatMap(Collection::stream)
                        .map(MiraklShop::getId)
                        .collect(Collectors.joining(LoggingConstantsUtil.LIST_LOGGING_SEPARATOR)));
        //@formatter:on

		//@formatter:off
        final List<KYCDocumentBusinessStakeHolderInfoModel> kycBusinessStakeHolderInfoModelList = Stream.ofNullable(shops.getShops())
                .flatMap(Collection::stream)
                .map(this::populateBusinessStakeholderForMiraklShop)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        //@formatter:on

		//@formatter:off
        final Map<String, List<KYCDocumentBusinessStakeHolderInfoModel>> shopsWithBusinessStakeholderVerificationRequired = kycBusinessStakeHolderInfoModelList.stream()
                .filter(kycDocStk -> kycDocStk.isRequiresKYC() || kycDocStk.isRequiresLetterOfAuthorization())
                .collect(Collectors.groupingBy(KYCDocumentInfoModel::getClientUserId));
        //@formatter:on

		if (!CollectionUtils.isEmpty(shopsWithBusinessStakeholderVerificationRequired)) {
			//@formatter:off
            log.info("Shops with KYC business stakeholder verification required: [{}]",
                    String.join(LoggingConstantsUtil.LIST_LOGGING_SEPARATOR, shopsWithBusinessStakeholderVerificationRequired.keySet()));
            //@formatter:on
		}

		skipShopsWithNonBusinessStakeholderSelectedDocuments(shopsWithBusinessStakeholderVerificationRequired);

		final List<KYCDocumentBusinessStakeHolderInfoModel> shopsWithBusinessSelectedVerificationDocuments = shopsWithBusinessStakeholderVerificationRequired
				.values().stream()
				.filter(bstkList -> bstkList.stream().allMatch(
						KYCDocumentBusinessStakeHolderInfoModel::hasSelectedDocumentsControlFieldsInBusinessStakeholder))
				.flatMap(Collection::stream).collect(Collectors.toList());

		//@formatter:off
        return shopsWithBusinessSelectedVerificationDocuments.stream()
                .filter(kycBusinessStakeHolderInfoModel -> !ObjectUtils.isEmpty(kycBusinessStakeHolderInfoModel.getUserToken()))
                .map(miraklBusinessStakeholderDocumentDownloadExtractService::getBusinessStakeholderDocumentsSelectedBySeller)
                .collect(Collectors.toList());
        //@formatter:on

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<MiraklUpdatedShops> setBusinessStakeholderFlagKYCToPushBusinessStakeholderDocumentsToFalse(
			final List<KYCDocumentBusinessStakeHolderInfoModel> successfullyPushedListOfDocuments) {

		if (CollectionUtils.isEmpty(successfullyPushedListOfDocuments)) {
			return Optional.empty();
		}

		final Map<String, List<KYCDocumentBusinessStakeHolderInfoModel>> kycFlagToFalse = successfullyPushedListOfDocuments
				.stream().filter(KYCDocumentBusinessStakeHolderInfoModel::isSentToHyperwallet)
				.collect(Collectors.groupingBy(KYCDocumentBusinessStakeHolderInfoModel::getClientUserId));

		return miraklUpdateKYCShopCall(kycFlagToFalse);
	}

	private List<KYCDocumentBusinessStakeHolderInfoModel> populateBusinessStakeholderForMiraklShop(
			final MiraklShop miraklShop) {
		//@formatter:off
        return IntStream.range(1, 6)
                .mapToObj(i -> kycBusinessStakeHolderConverter.convert(miraklShop, i))
                .filter(Objects::nonNull)
                .filter(Predicate.not(KYCDocumentBusinessStakeHolderInfoModel::isEmpty))
                .collect(Collectors.toCollection(ArrayList::new));
        //@formatter:on
	}

	private Optional<MiraklShop> extractMiraklShop(final String shopId) {
		final MiraklGetShopsRequest miraklGetShopsRequest = new MiraklGetShopsRequest();
		miraklGetShopsRequest.setShopIds(List.of(shopId));
		log.info("Retrieving shopId [{}]", shopId);
		final MiraklShops shops = miraklOperatorClient.getShops(miraklGetShopsRequest);

		return Optional.ofNullable(shops).orElse(new MiraklShops()).getShops().stream()
				.filter(shop -> shopId.equals(shop.getId())).findAny();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getKYCCustomValuesRequiredVerificationBusinessStakeholders(final String shopId,
			final List<String> requiredVerificationBusinessStakeholderTokens) {

		if (Objects.nonNull(shopId) && !CollectionUtils.isEmpty(requiredVerificationBusinessStakeholderTokens)) {
			final Optional<MiraklShop> miraklShop = extractMiraklShop(shopId);
			//@formatter:off
            final List<String> requiredVerificationBusinessStakeHolderCodes = miraklShop
                    .map(AbstractMiraklShop::getAdditionalFieldValues).stream()
                    .filter(Objects::nonNull)
                    .flatMap(Collection::stream)
                    .filter(element -> element.getCode().contains(HYPERWALLET_PREFIX + STAKEHOLDER_PREFIX + STAKEHOLDER_TOKEN_PREFIX))
                    .filter(MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue.class::isInstance)
                    .map(MiraklAdditionalFieldValue.MiraklStringAdditionalFieldValue.class::cast)
                    .filter(element -> requiredVerificationBusinessStakeholderTokens.contains(element.getValue()))
                    .map(MiraklAdditionalFieldValue::getCode)
                    .collect(Collectors.toList());
            //@formatter:on
			return Optional.of(requiredVerificationBusinessStakeHolderCodes).orElse(List.of()).stream()
					.map(requiredVerificationBusinessStakeHolderCode -> HYPERWALLET_PREFIX + STAKEHOLDER_PREFIX
							+ REQUIRED_PROOF_IDENTITY.concat(requiredVerificationBusinessStakeHolderCode
									.substring(requiredVerificationBusinessStakeHolderCode.length() - 1)))
					.collect(Collectors.toList());
		}
		return List.of();
	}

	private void skipShopsWithNonBusinessStakeholderSelectedDocuments(
			final Map<String, List<KYCDocumentBusinessStakeHolderInfoModel>> shopsWithBusinessStakeholderVerificationRequired) {
		final Map<String, String> shopsWithNotAllBstkDocumentsSelected = shopsWithBusinessStakeholderVerificationRequired
				.entrySet().stream()
				.filter(entry -> entry.getValue().stream().anyMatch(Predicate.not(
						KYCDocumentBusinessStakeHolderInfoModel::hasSelectedDocumentsControlFieldsInBusinessStakeholder)))
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().filter(Predicate.not(
						KYCDocumentBusinessStakeHolderInfoModel::hasSelectedDocumentsControlFieldsInBusinessStakeholder))
						.map(KYCDocumentBusinessStakeHolderInfoModel::getBusinessStakeholderMiraklNumber)
						.map(String::valueOf).collect(Collectors.joining(","))));

		if (!CollectionUtils.isEmpty(shopsWithNotAllBstkDocumentsSelected)) {
			//@formatter:off
            shopsWithNotAllBstkDocumentsSelected.forEach((key, value) ->
                    log.warn("Skipping shop with id [{}] because business stakeholders with following numbers has not selected a document for uploading [{}]", key, value));
            //@formatter:on
		}
	}

	private Optional<MiraklUpdatedShops> miraklUpdateKYCShopCall(
			final Map<String, List<KYCDocumentBusinessStakeHolderInfoModel>> shopsToUpdate) {
		if (CollectionUtils.isEmpty(shopsToUpdate)) {
			return Optional.empty();
		}

		final List<MiraklUpdateShop> miraklShopToUpdate = shopsToUpdate.entrySet().stream().map(entry -> {
			final MiraklUpdateShop miraklUpdateShop = new MiraklUpdateShop();
			miraklUpdateShop.setShopId(Long.valueOf(entry.getKey()));
			final List<MiraklRequestAdditionalFieldValue> additionalValues = entry.getValue().stream()
					.filter(KYCDocumentBusinessStakeHolderInfoModel::isRequiresKYC)
					.map(businessStakeholder -> new MiraklRequestAdditionalFieldValue.MiraklSimpleRequestAdditionalFieldValue(
							HYPERWALLET_PREFIX + STAKEHOLDER_PREFIX + REQUIRED_PROOF_IDENTITY
									+ businessStakeholder.getBusinessStakeholderMiraklNumber(),
							Boolean.FALSE.toString().toLowerCase()))
					.collect(Collectors.toList());

			additionalValues.addAll(entry.getValue().stream()
					.filter(KYCDocumentBusinessStakeHolderInfoModel::isRequiresLetterOfAuthorization)
					.map(businessStakeholder -> new MiraklRequestAdditionalFieldValue.MiraklSimpleRequestAdditionalFieldValue(
							HYPERWALLET_KYC_REQUIRED_PROOF_AUTHORIZATION_BUSINESS_FIELD,
							Boolean.FALSE.toString().toLowerCase()))
					.collect(Collectors.toList()));

			miraklUpdateShop.setAdditionalFieldValues(additionalValues);
			return miraklUpdateShop;
		}).collect(Collectors.toList());

		final MiraklUpdateShopsRequest miraklUpdateShopRequest = new MiraklUpdateShopsRequest(miraklShopToUpdate);
		try {
			final MiraklUpdatedShops miraklUpdatedShops = miraklOperatorClient.updateShops(miraklUpdateShopRequest);
			//@formatter:on
			log.info("Setting required KYC and letter of authorisation flag for shops with ids [{}] to false",
					miraklUpdatedShops.getShopReturns().stream().map(MiraklUpdatedShopReturn::getShopUpdated)
							.map(MiraklShop::getId)
							.collect(Collectors.joining(LoggingConstantsUtil.LIST_LOGGING_SEPARATOR)));
			return Optional.of(miraklUpdatedShops);
			//@formatter:off

        } catch (final MiraklException e) {
            log.error("Something went wrong when removing flag to retrieve documents for shops [{}]", String.join(",", shopsToUpdate.keySet()));
            kycMailNotificationUtil.sendPlainTextEmail("Issue setting push document flags to false in Mirakl",
                    String.format("Something went wrong setting push document flag to false in Mirakl for shop Id [%s]%n%s",
                            String.join(",", shopsToUpdate.keySet()),
                            MiraklLoggingErrorsUtil.stringify(e)));
        }

        return Optional.empty();
    }

}
