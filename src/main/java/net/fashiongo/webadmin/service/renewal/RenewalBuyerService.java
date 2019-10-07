package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.AspnetMembershipEntity;
import net.fashiongo.webadmin.data.entity.primary.LogEmailSentEntity;
import net.fashiongo.webadmin.data.model.ListAccountDeactivationReason;
import net.fashiongo.webadmin.data.model.LogSentEmail;
import net.fashiongo.webadmin.data.model.buyer.Retailer;
import net.fashiongo.webadmin.data.model.buyer.RetailerDetail;
import net.fashiongo.webadmin.data.model.buyer.response.RetailerDetailResponse;
import net.fashiongo.webadmin.data.repository.primary.AspnetMembershipEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.ListAccountDeactivationReasonEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.LogEmailSentEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.RetailerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RenewalBuyerService {

	@Autowired
	private RetailerEntityRepository retailerEntityRepository;

	@Autowired
	private AspnetMembershipEntityRepository aspnetMembershipEntityRepository;

	@Autowired
	private ListAccountDeactivationReasonEntityRepository listAccountDeactivationReasonEntityRepository;

	@Autowired
	private LogEmailSentEntityRepository logEmailSentEntityRepository;

	private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	private static final DateTimeFormatter ZONED_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssx");

	public RetailerDetailResponse getAdminRetailerDetail(Integer retailerId) {

		Retailer adminRetailerInfo = getAdminRetailerInfo(retailerId);
		List<ListAccountDeactivationReason> listAccountDeactivationReasons = listAccountDeactivationReasonEntityRepository.findAll().stream()
				.map(listAccountDeactivationReasonEntity -> ListAccountDeactivationReason.builder()
						.reason(listAccountDeactivationReasonEntity.getReason())
						.reasonID(listAccountDeactivationReasonEntity.getReasonID())
						.build())
				.collect(Collectors.toList());

		List<LogSentEmail> adminLogEmailSent = getAdminLogEmailSent(retailerId);

		return RetailerDetailResponse.builder()
				.retailer(adminRetailerInfo)
				.deactivationReason(listAccountDeactivationReasons)
				.logEmailSent(adminLogEmailSent)
				.build();
	}

	private List<LogSentEmail> getAdminLogEmailSent(int RetailerID) {
		List<LogEmailSentEntity> logEmailSentEntityList = logEmailSentEntityRepository.findAllWithListEmailTypeByRetailerId(RetailerID);

		return logEmailSentEntityList.stream()
				.map(logEmailSentEntity ->
						LogSentEmail.builder()
								.emailContents(logEmailSentEntity.getEmailContents())
								.referenceText(logEmailSentEntity.getReferenceText())
								.sentEmailTypeID(logEmailSentEntity.getSentEmailTypeID())
								.sentBy(logEmailSentEntity.getSentBy())
								.sentOn(
										Optional.ofNullable(logEmailSentEntity.getSentOn()).map(Timestamp::toLocalDateTime).orElse(null)
								)
								.emailType(logEmailSentEntity.getListEmailTypeEntity().getEmailType()).build()
				).collect(Collectors.toList());
	}

	private Retailer getAdminRetailerInfo(Integer retailerId) {
		return retailerEntityRepository.findById(retailerId).map(retailerEntity -> {
			RetailerDetail retailerDetail = RetailerDetail.builder()
					.active(retailerEntity.getActive())
					.additionalDocumentFileName(retailerEntity.getAdditionalDocumentFileName())
					.billCity(retailerEntity.getBillCity())
					.billCountry(retailerEntity.getBillCountry())
					.billCountryId(retailerEntity.getBillCountryID())
					.billFax(retailerEntity.getBillFax())
					.billPhone(retailerEntity.getBillPhone())
					.billState(retailerEntity.getBillSTATE())
					.billStreetNo(retailerEntity.getBillStreetNo())
					.billStreetNo2(retailerEntity.getBillStreetNo2())
					.billZipCode(retailerEntity.getBillZipcode())
					.companyName(retailerEntity.getCompanyName())
					.firstName(retailerEntity.getFirstName())
					.lastLoginDate(retailerEntity.getLastLoginDate())
					.confirmedBy(retailerEntity.getConfirmedBy())
					.confirmedOn(retailerEntity.getConfirmedOn())
					.currentComment(retailerEntity.getCurrentComment())
					.currentStatus(retailerEntity.getCurrentStatus())
					.inHouseMemo(retailerEntity.getInHouseMemo())
					.retailerPermitNo(retailerEntity.getRetailerPermitNo())
					.email(retailerEntity.getEmail())
					.invoiceFileName1(retailerEntity.getInvoiceFileName1())
					.invoiceFileName2(retailerEntity.getInvoiceFileName2())
					.lastName(retailerEntity.getLastName())
					.userId(retailerEntity.getUserID())
					.mobilePhoneNo(retailerEntity.getMobilePhoneNo())
					.webSite(retailerEntity.getWebSite())
					.lastModifiedDateTime(retailerEntity.getLastModifiedDateTime())
					.lastUser(retailerEntity.getLastUser())
					.memo(retailerEntity.getMemo())
					.webSite(retailerEntity.getWebSite())
					.webSiteYN(StringUtils.isEmpty(retailerEntity.getWebSite()) == false ? "Y" : "N")
					.retailerId(retailerEntity.getRetailerID())
					.retailerPermitNo(retailerEntity.getRetailerPermitNo())
					.startingDate(retailerEntity.getStartingDate())
					.terminatedNote(retailerEntity.getTerminatedNote())
					.build();

			AspnetMembershipEntity m = aspnetMembershipEntityRepository.findByUserName(retailerEntity.getUserID()).orElse(null);

			if (m != null) {

//				lastLockoutDate: "1753-12-31T16:00:00-08:00"
//				lastLoginDate: "8/10/2018 6:50:40 AM"

				String lastLoginDate = Optional.ofNullable(m.getLastLoginDate())
						.map(dateTime -> ZonedDateTime.of(dateTime, ZoneOffset.UTC))
						.map(dateTime -> dateTime.toInstant().atZone(ZoneId.systemDefault()).format(DATETIME_FORMAT))
						.orElse("n/a");

				boolean isLockOut = m.isLockedOut();


				String lastLockoutDateString = Optional.ofNullable(m.getLastLockoutDate())
						.map(dateTime -> ZonedDateTime.of(dateTime, ZoneOffset.UTC))
						.map(dateTime -> dateTime.toInstant().atZone(ZoneId.systemDefault()).format(ZONED_DATETIME_FORMAT))
						.orElse("");

				return Retailer.builder()
						.isLockOut(isLockOut)
						.lastLockoutDate(lastLockoutDateString)
						.lastLoginDate(lastLoginDate)
						.retailerDetail(retailerDetail)
						.build();
			}
			return null;
		}).orElse(null);
	}
}
