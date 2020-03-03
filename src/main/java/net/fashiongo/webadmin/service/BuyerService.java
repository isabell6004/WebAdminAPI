package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetAdminRetailerDetailParameter;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetAdminRetailerInfoParameter;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetAttachedFileParameter;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @author DAHYE
 *
 */
@Service
public class BuyerService extends ApiService {

	@Autowired
	private RetailerCompanyRepository retailerCompanyRepository;

	@Autowired
	private AspnetMembershipRepository aspnetMembershipRepository;

	@Autowired
	private AspnetUsersRepository aspnetUsersRepository;

	@Autowired
	private PaymentCustomerRepository paymentCustomerRepository;

	@Autowired
	private FraudNoticeRepository fraudNoticeRepository;

	@Autowired
	private EntityActionLogRepository entityActionLogRepository;

	@Autowired
	private BuyerStatusChangeLogRepository buyerStatusChangeLogRepository;

	/**
	 * Description Example
	 *
	 * @param companyName
	 * @return
	 * @author Reo
	 * @since 2018. 11. 22.
	 */
	public List<RetailerCompany> GetRetailerListForCompanyName(String companyName) {
		List<RetailerCompany> result = retailerCompanyRepository.findByActiveAndCompanyNameStartingWithOrderByCompanyName("Y", companyName);

		return result;
	}

	/**
	 * SetAdminRetailerReadYN
	 *
	 * @param retailerIDList
	 * @param readYN
	 * @return Integer
	 * @author Dahye
	 * @since 2018. 11. 28.
	 */
	public Integer SetAdminRetailerReadYN(List<Integer> retailerIDList, Boolean readYN) {
		if (retailerIDList.size() < 1) return -1;
		for (Integer id : retailerIDList) {
			RetailerCompany retailer = retailerCompanyRepository.findOneByRetailerID(id);
			if (retailer == null) {
				return -1;
			}
			retailer.setOperatorRead(readYN);
			retailerCompanyRepository.save(retailer);
		}
		return 1;
	}

	@Transactional
	public void setAdminRetailerInfo(List<SetAdminRetailerInfoParameter.RetailerInfo> retailerCompanyList) {
		LocalDateTime now = LocalDateTime.now();

		for (SetAdminRetailerInfoParameter.RetailerInfo retailerInfo : retailerCompanyList) {
			Optional<RetailerCompany> retailerCompanyOptional = retailerCompanyRepository.findById(retailerInfo.getRetailerId());
			retailerCompanyOptional.ifPresent(tblRetailer -> {
				String statusBefore = tblRetailer.getActive().toUpperCase() + tblRetailer.getCurrentStatus();
				String statusAfter = retailerInfo.getActive().toUpperCase() + retailerInfo.getCurrentStatus();

				// save tblRetailer
				tblRetailer.setActive(retailerInfo.getActive());
				tblRetailer.setCurrentStatus(retailerInfo.getCurrentStatus());
				retailerCompanyRepository.save(tblRetailer);

				// save aspnet_membership
				setMembershipStatus(tblRetailer.getRetailerGuid(), retailerInfo.getActive(), now);

				// entity_actionLog
				logEntityAction(tblRetailer.getRetailerID(), now);

				// buyer status change log
				logBuyerStatusChange(tblRetailer.getRetailerID(), statusBefore, statusAfter, now);
			});
		}
	}

	@Transactional
	public int setAdminRetailerDetail(SetAdminRetailerDetailParameter setAdminRetailerDetailParam) {
		LocalDateTime now = LocalDateTime.now();

		SetAdminRetailerDetailParameter.RetailerDetail retailerDetail = setAdminRetailerDetailParam.getRetailerDetail();
		RetailerCompany tblRetailer = retailerCompanyRepository.findById(retailerDetail.getRetailerId()).orElseThrow(() -> new RuntimeException("Retailer not exists."));

		String statusBefore = tblRetailer.getActive().toUpperCase() + tblRetailer.getCurrentStatus();
		String statusAfter = retailerDetail.getActive().toUpperCase() + retailerDetail.getCurrentStatus();

		if (setAdminRetailerDetailParam.isChangeId()) {
			Optional<AspnetUsers> duplicateUserOptional = aspnetUsersRepository.findByUserName(retailerDetail.getUserId());
			if (duplicateUserOptional.isPresent()) {
				return -3;
			}

			Optional<AspnetUsers> aspnetUsersOptional = aspnetUsersRepository.findByUserName(setAdminRetailerDetailParam.getPreUserId());
			aspnetUsersOptional.ifPresent(aspnetUsers -> {

				// save aspnet_Users
				aspnetUsers.setUserName(retailerDetail.getUserId());
				aspnetUsers.setLoweredUserName(retailerDetail.getUserId().toLowerCase());
				aspnetUsersRepository.save(aspnetUsers);

				// save paymentCustomer
				Optional<PaymentCustomer> paymentCustomerOptional = paymentCustomerRepository.findByRetailerId(retailerDetail.getRetailerId());
				paymentCustomerOptional.ifPresent(paymentCustomer -> {
					paymentCustomer.setEmailAddress(retailerDetail.getUserId());
					paymentCustomer.setModifiedBy(Utility.getUsername());
					paymentCustomer.setModifiedOn(now);
					paymentCustomerRepository.save(paymentCustomer);
				});
			});
		}

		if (retailerDetail.getCurrentStatus() == 5) {
			retailerDetail.setActive("N");
		}

		// save tblRetailer
		tblRetailer.setUserID(retailerDetail.getUserId());
		tblRetailer.setCompanyName(retailerDetail.getCompanyName());
		tblRetailer.setRetailerPermitNo(retailerDetail.getRetailerPermitNo());
		tblRetailer.setFirstName(retailerDetail.getFirstName());
		tblRetailer.setLastName(retailerDetail.getLastName());
		tblRetailer.setActive(retailerDetail.getActive());
		tblRetailer.setCurrentStatus(retailerDetail.getCurrentStatus());
		tblRetailer.setInHouseMemo(retailerDetail.getInHouseMemo());
		tblRetailer.setTerminatedNote(retailerDetail.getTerminatedNote());
		tblRetailer.setWebSite(retailerDetail.getWebSite());
		tblRetailer.setEMail(retailerDetail.getUserId());
		tblRetailer.setLastUser(retailerDetail.getLastUser());
		//tblRetailer.setLastModifiedDateTime(retailerDetail.getLastModifiedDateTime());
		tblRetailer.setLastModifiedDateTime(now);
		tblRetailer.setBuyerClass(retailerDetail.getBuyerClass());
		if (retailerDetail.getAmUserID() == 0 ){
			tblRetailer.setAmUserID(null);
		}
		else {
			tblRetailer.setAmUserID(retailerDetail.getAmUserID());
		}

		retailerCompanyRepository.save(tblRetailer);

		// save aspnet_membership
		setMembershipStatus(tblRetailer.getRetailerGuid(), retailerDetail.getActive(), now);

		if (setAdminRetailerDetailParam.isCheckedFraudNotice()) {
			boolean activeFraudExist = false;
			for (SetAdminRetailerDetailParameter.FraudNotice fraudNotice : setAdminRetailerDetailParam.getFraudNoticeList()) {
				if (fraudNotice.isActive()) {
					activeFraudExist = true;
				}
				Optional<FraudNotice> fraudNoticeOptional = fraudNoticeRepository.findById(fraudNotice.getFraudNoticeId());
				fraudNoticeOptional.ifPresent(fNotice -> {
					fNotice.setActive(fraudNotice.isActive());
					fNotice.setModifiedOn(now);
					fNotice.setModifiedBy(Utility.getUsername());
					fraudNoticeRepository.save(fNotice);
				});
			}

			if (activeFraudExist) {
				tblRetailer.setActive("N");
				tblRetailer.setCurrentStatus(5);
			} else {
				tblRetailer.setActive("Y");
				tblRetailer.setCurrentStatus(3); // default current status

				List<FraudNotice> fraudNoticeList = fraudNoticeRepository.findByRetailerIdAndActiveIsFalseOrderByFraudNoticeIdDesc(retailerDetail.getRetailerId());
				for (FraudNotice fraudNotice : fraudNoticeList) {
					if (fraudNotice.getOriginalStatus() != null && fraudNotice.getOriginalStatus() != 5) {
						tblRetailer.setCurrentStatus(fraudNotice.getOriginalStatus());
						break;
					}
				}
			}

			// save aspnet_membership
			setMembershipStatus(tblRetailer.getRetailerGuid(), tblRetailer.getActive(), now);
		}

		// entity_actionLog
		logEntityAction(tblRetailer.getRetailerID(), now);

		// buyer status change log
		logBuyerStatusChange(tblRetailer.getRetailerID(), statusBefore, statusAfter, now);

		return 1;
	}

    @Transactional
    public int setAttachedFile(SetAttachedFileParameter setAttachedFileParameter) {
        LocalDateTime now = LocalDateTime.now();

        RetailerCompany tblRetailer = retailerCompanyRepository.findById(setAttachedFileParameter.getRetailerId()).orElseThrow(() -> new RuntimeException("Retailer not exists."));

        boolean sellerPermitUploaded = false;
        switch (setAttachedFileParameter.getFileType().toLowerCase()) {
            case "s":
                tblRetailer.setSellerPermitFileName(setAttachedFileParameter.getFileName());
				sellerPermitUploaded = true;
                break;
            case "in1":
                tblRetailer.setInvoiceFileName1(setAttachedFileParameter.getFileName());
                break;
            case "in2":
                tblRetailer.setInvoiceFileName2(setAttachedFileParameter.getFileName());
                break;
            case "o":
                tblRetailer.setAdditionalDocumentFileName(setAttachedFileParameter.getFileName());
                break;
        }

        // save tblRetailer
        retailerCompanyRepository.save(tblRetailer);

        // entity_actionLog
        logEntityAction(tblRetailer.getRetailerID(), now);

        // buyer status change log
		if (sellerPermitUploaded) {
			logBuyerStatusChange(tblRetailer.getRetailerID(), "DocumentUploaded", now);
		}

	    return 1;
    }

	private void setMembershipStatus(String guid, String active, LocalDateTime now) {
		Optional<AspnetMembership> aspnetMembershipOptional = aspnetMembershipRepository.findById(guid);
		aspnetMembershipOptional.ifPresent(aspnetMembership -> {
			aspnetMembership.setIsApproved("Y".equalsIgnoreCase(active));
			aspnetMembership.setIsLockedOut("N".equalsIgnoreCase(active));
			if ("N".equalsIgnoreCase(active)) {
				aspnetMembership.setLastLockoutDate(now);
			}
			aspnetMembershipRepository.save(aspnetMembership);
		});
	}

	private void logEntityAction(int retailerId, LocalDateTime now) {
		EntityActionLog entityActionLog = new EntityActionLog();
		entityActionLog.setEntityTypeID(2);
		entityActionLog.setActionID(4002);
		entityActionLog.setEntityID(retailerId);
		entityActionLog.setActedOn(now);
		entityActionLog.setActedBy(Utility.getUsername());
		entityActionLogRepository.save(entityActionLog);
	}

    private void logBuyerStatusChange(int retailerId, String changeType, LocalDateTime now) {
		BuyerStatusChangeLog buyerStatusChangeLog = new BuyerStatusChangeLog();
		buyerStatusChangeLog.setBuyerId(retailerId);
		buyerStatusChangeLog.setBuyerStatusChangeTypeCode(changeType);
		buyerStatusChangeLog.setCreatedOn(now);
		buyerStatusChangeLog.setCreatedBy(Utility.getUsername());
		buyerStatusChangeLogRepository.save(buyerStatusChangeLog);
    }

	private void logBuyerStatusChange(int retailerId, String statusBefore, String statusAfter, LocalDateTime now) {
		if (statusBefore.equals(statusAfter)) return;
		
        String changeType = statusBefore + " > " + statusAfter;
		if (statusBefore.startsWith("N") && statusAfter.startsWith("Y")) {
			changeType = "Revive";
		} else if ("Y1".equals(statusAfter)) {
			changeType = "Approved";
		} else if (Arrays.asList("N1", "N2", "N3", "N4").contains(statusAfter)) {
			changeType = "Terminated";
		} else if ("N5".equals(statusAfter)) {
			changeType = "Fraud";
		}

		logBuyerStatusChange(retailerId, changeType, now);
	}
}

