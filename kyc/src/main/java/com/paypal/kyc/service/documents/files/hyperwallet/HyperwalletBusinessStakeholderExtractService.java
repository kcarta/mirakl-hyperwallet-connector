package com.paypal.kyc.service.documents.files.hyperwallet;

import com.paypal.kyc.model.KYCDocumentBusinessStakeHolderInfoModel;

import java.util.List;

/**
 * Interface that manages logic of pushing documents to Hyperwallet at business
 * stakeholder level
 */
public interface HyperwalletBusinessStakeholderExtractService {

	/**
	 * Obtains List of required verification business stakeholders based on a user token
	 * and an hyperwalletProgram
	 * @param hyperwalletProgram, userToken
	 * @return List of {@link String} with required verification business stakeholders
	 * tokens
	 */
	List<String> getKYCRequiredVerificationBusinessStakeHolders(String hyperwalletProgram, String userToken);

	/**
	 * Pushes the documents into HW and returns the list of
	 * KYCDocumentBusinessStakeHolderInfoModel that were correctly uploaded to HW
	 * @param kycBusinesStakeHolderInfoModels
	 * @return the correct list of {@link KYCDocumentBusinessStakeHolderInfoModel} pushed
	 * to hyperwallet
	 */
	List<KYCDocumentBusinessStakeHolderInfoModel> pushBusinessStakeholderDocuments(
			List<KYCDocumentBusinessStakeHolderInfoModel> kycBusinesStakeHolderInfoModels);

	List<KYCDocumentBusinessStakeHolderInfoModel> notifyAllDocumentsSentForBstk(
			List<KYCDocumentBusinessStakeHolderInfoModel> documentsTriedToBeSent);

}
