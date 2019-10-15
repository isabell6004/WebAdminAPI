package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.common.dal.JdbcHelper;
import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.model.ListAccountDeactivationReason;
import net.fashiongo.webadmin.data.model.LogSentEmail;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.buyer.*;
import net.fashiongo.webadmin.data.model.buyer.response.*;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.procedure.PrimaryProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

	@Autowired
	private XShipAddressEntityRepository xShipAddressEntityRepository;

	@Autowired
	private FraudNoticeEntityRepository fraudNoticeEntityRepository;

	@Autowired
	private ListCommunicationReasonEntityRepository listCommunicationReasonEntityRepository;

	@Autowired
	private LogCommunicationEntityRepository logCommunicationEntityRepository;

	@Autowired
	private JdbcHelper jdbcHelper;

	@Autowired
	private PrimaryProcedureRepository primaryProcedureRepository;

	@Autowired
	private WholeRetailerBlockEntityRepository wholeRetailerBlockEntityRepository;

	@Autowired
	private CartItemEntityRepository cartItemEntityRepository;

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

	public List<ShippingAddressResponse> getShippingAddress(GetShippingAddressParameter parameter) {
		List<XShipAddressEntity> xShipAddressEntityList = xShipAddressEntityRepository.findAllByCustID2(parameter.getRetailerId());

		return xShipAddressEntityList.stream()
				.map(entity -> ShippingAddressResponse.builder()
						.active(entity.isActive())
						.custID2(entity.getCustID2())
						.defaultYN(entity.isDefaultYN())
						.isCommercialAddress(entity.getIsCommercialAddress())
						.shipAddID(entity.getShipAddID())
						.shipAddress2(entity.getShipAddress2())
						.shipAddress2B(entity.getShipAddress2B())
						.shipAttention(entity.getShipAttention())
						.shipCity2(entity.getShipCity2())
						.shipCountry2(entity.getShipCountry2())
						.shipCountry2ID(entity.getShipCountry2ID() == null ? 229 : entity.getShipCountry2ID())
						.shipFax(entity.getShipFax())
						.shipPhone(entity.getShipPhone())
						.shipState2(entity.getShipState2())
						.shipZip2(entity.getShipZip2())
						.storeNo(entity.getStoreNo())
						.build())
				.collect(Collectors.toList());
	}

	public List<FraudNoticeResponse> getFraudNotice(GetFraudNoticeParameter parameter) {
		List<FraudNoticeEntity> fraudNoticeEntityList = fraudNoticeEntityRepository.findAllByRetailerIdOrderByCreatedOnDesc(parameter.getRetailerId());

		return fraudNoticeEntityList.stream()
				.map(entity -> {

					SimpleWholeSalerEntity wholeSaler = entity.getWholeSaler();

					return FraudNoticeResponse.builder()
							.active(entity.isActive())
							.commentByFG(entity.getCommentByFG())
							.commentByWholeSaler(entity.getCommentByWholeSaler())
							.wholeSalerCompanyName(wholeSaler != null ? wholeSaler.getCompanyName() : null)
							.fraudDetail(entity.getFraudDetail())
							.fraudNoticeID(entity.getFraudNoticeID())
							.createdOn(entity.getCreatedOn())
							.build();
				})
				.collect(Collectors.toList());
	}

	public List<ListCommunicationReasonResponse> getCommunicationReason() {

		List<ListCommunicationReasonEntity> listCommunicationReasonEntityList = listCommunicationReasonEntityRepository.findAllByActive(true);

		return listCommunicationReasonEntityList.stream()
				.map(entity -> ListCommunicationReasonResponse.builder()
						.reason(entity.getReason())
						.active(entity.isActive())
						.parentID(entity.getParentID())
						.reasonID(entity.getReasonID())
						.build()
				).collect(Collectors.toList());
	}

	public List<CommunicationLogResponse> getCommunicationLog(GetCommunicationLogParameter parameter) {
		List<LogCommunicationEntity> logCommunicationEntityList = logCommunicationEntityRepository.findAllByRetailerIdOrderByModifiedOnDesc(parameter.getRetailerId());
		return logCommunicationEntityList.stream()
				.map(entity -> CommunicationLogResponse.builder()
						.checkedBy(entity.getCheckedBy())
						.checkedBy2(entity.getCheckedBy2())
						.checkedOn(entity.getCheckedOn())
						.checkedOn2(entity.getCheckedOn2())
						.communicatedOn(entity.getCommunicatedOn())
						.communicationID(entity.getCommunicationID())
						.confirmed1(StringUtils.isEmpty(entity.getCheckedBy()) ? false : true)
						.confirmed2(StringUtils.isEmpty(entity.getCheckedBy2()) ? false : true)
						.contactedBy(entity.getContactedBy())
						.notes(entity.getNotes())
						.reasonID(entity.getReasonID())
						.reason(entity.getListCommunicationReason().getReason())
						.build()
				).collect(Collectors.toList());
	}

	public LogSentEmailResponse getAdminLogEmailSent(GetAdminLogEmailSentParameter parameter) {
		return LogSentEmailResponse.builder()
				.logEmailSent(getAdminLogEmailSent(parameter.getRetailerId()))
				.build();
	}

	public GetOrderHistoryStatisticsResponse getOrderHistoryStatistics(GetOrderHistoryStatisticsParameter parameter) {

		List<Object> param = new ArrayList<>();
		param.add(parameter.getRetailerId());

//		List<Object> _result = jdbcHelper.executeSP("up_wa_RetailerInfo_OrderSummary", param, OrderHistoryStatistics.class);
//		List<OrderHistoryStatistics> statistics = ((List<OrderHistoryStatistics>) _result.get(0));

		OrderHistoryStatistics orderHistoryStatistics = primaryProcedureRepository.up_wa_RetailerInfo_OrderSummary(parameter.getRetailerId());

		return GetOrderHistoryStatisticsResponse.builder()
				.orderHistoryStatistics(Arrays.asList(orderHistoryStatistics))
				.build();
	}

	public GetInaccessibleVendorsResponse getInaccessibleVendors(GetInaccessibleVendorsParameter parameter) {

		Page<InaccessibleVendor> wholeRetailerBlockEntities = wholeRetailerBlockEntityRepository.findAllByRetailerIdOrderByStartingDateDesc(parameter.getPagenum(), parameter.getPagesize(), parameter.getRetailerid());

		return GetInaccessibleVendorsResponse.builder()
				.table(Arrays.asList(Total.builder().recCnt((int) wholeRetailerBlockEntities.getTotalElements()).build()))
				.table1(
						wholeRetailerBlockEntities.getContent()
				)
				.build();
	}

	public GetShoppingBagResponse getShoppingBag(GetShoppingBagParameter parameter) {

		Integer retailerId = parameter.getRetailerId();
		List<WholeSalerId> wholeSalerIds = cartItemEntityRepository.findAllShoppingBagGroupByWholesalerId(retailerId).stream()
				.map(integer -> WholeSalerId.builder().wholeSalerID(integer).build())
				.collect(Collectors.toList());

		List<ShoppingBag> shoppingBagList = cartItemEntityRepository.findAllShoppingBag(retailerId);

		return GetShoppingBagResponse.builder()
				.table(wholeSalerIds)
				.table1(shoppingBagList)
				.build();
	}
}
