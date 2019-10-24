package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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

	@Autowired
	private CreditCardEntityRepository creditCardEntityRepository;

	@Autowired
	private StoreCreditEntityRepository storeCreditEntityRepository;

	@Autowired
	private OrdersEntityRepository ordersEntityRepository;

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

	public List<CreditCard> getCreditCard(GetCreditCardParameter parameter) {
		Integer retailerId = parameter.getRetailerId();
		if(retailerId == null) {
			retailerId = 0;
		}

		return creditCardEntityRepository.findAllByRetailerIdWithCodeCreditCardType(retailerId).stream()
				.map(entity -> CreditCard.builder()
						.creditCardID(entity.getCreditCardID())
						.creditCardType(entity.getCodeCreditCardType().getCreditCardType())
						.isDefaultCard(entity.isDefaultCard())
						.last4Digit(entity.getLast4Digit())
						.creditCardTypeID(entity.getCardTypeID())
						.build()
				).collect(Collectors.toList());
	}

	public GetStoreCreditResponse getStoreCredit(GetStoreCreditParameter parameter) {

		Integer retailerId = parameter.getRetailerId();
		String po = parameter.getPo();
		String vendor = parameter.getVendor();

		LocalDateTime fromDateTime = Optional.ofNullable(parameter.getFrom()).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime toDateTime = Optional.ofNullable(parameter.getTo()).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);

		List<StoreCardSummary> storeCardSummaries = storeCreditEntityRepository.findAllStoreCardSummary(retailerId, vendor, po, fromDateTime, toDateTime);

		List<StoreCardDetail> storeCardDetail = storeCreditEntityRepository.findAllStoreCardDetail(retailerId, vendor, po, fromDateTime, toDateTime);
		return GetStoreCreditResponse.builder()
				.table(storeCardSummaries)
				.table1(storeCardDetail)
				.build();
	}

	public OrderHistoryResponse getOrderHistory(GetOrderHistoryParameter parameter) {
		Integer retailerID = parameter.getRetailerID();
		Integer orderStatusID = parameter.getOrderStatusID();
		Integer paymentStatusID = parameter.getPaymentStatusID();
		Integer pageNum = parameter.getPageNum();
		Integer pageSize = parameter.getPageSize();
		String wholeCompanyName = parameter.getWholeCompanyName();
		String poNumber = parameter.getPoNumber();
		String productName = parameter.getProductName();
		LocalDateTime fromDate = Optional.ofNullable(parameter.getDateFrom()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime toDate = Optional.ofNullable(parameter.getDateTo()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		String orderby = parameter.getOrderBy();

		Page<OrderHistory> orderHistories = ordersEntityRepository.up_wa_GetOrderHistoryMaster(pageNum, pageSize, retailerID, wholeCompanyName, paymentStatusID, orderStatusID, fromDate, toDate, poNumber, productName, orderby);

		return OrderHistoryResponse.builder()
				.table(Arrays.asList(Total.builder().recCnt((int) orderHistories.getTotalElements()).build()))
				.table1(orderHistories.getContent())
				.build();
	}

	public Integer delInaccessibleVendors(DelInaccessibleVendorsParameter parameter) {

		Integer wholeretailerblockid = parameter.getWholeretailerblockid();
		if(wholeretailerblockid == null) {
			wholeretailerblockid = 0;
		}

		try {
			wholeRetailerBlockEntityRepository.deleteByWholeRetailerBlockID(wholeretailerblockid);
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	public Integer setInaccessibleVendors(SetInaccessibleVendorsParameter parameter,String sessionUserId) {
		Integer retailerid = parameter.getRetailerid();
		Integer wholesalerid = parameter.getWholesalerid();

		if(retailerid == null) {
			retailerid = 0;
		}

		if(wholesalerid == null) {
			wholesalerid = 0;
		}

		if(wholeRetailerBlockEntityRepository.existByWholesalerIdAndRetailerId(wholesalerid,retailerid)) {
			return -1;
		}

		try
		{
			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			WholeRetailerBlockEntity wholeRetailerBlock = new WholeRetailerBlockEntity();
			wholeRetailerBlock.setRetailerID(retailerid);
			wholeRetailerBlock.setWholeSalerID(wholesalerid);
			wholeRetailerBlock.setLastModifiedDateTime(NOW);
			wholeRetailerBlock.setStartingDate(NOW);
			wholeRetailerBlock.setLastUser(sessionUserId);

			wholeRetailerBlockEntityRepository.save(wholeRetailerBlock);
			return 1;
		}
		catch (Exception e)
		{
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer delStoreCredit(DelStoreCreditParameter parameter,String sessionUserId) {
		Integer creditid = parameter.getCreditid();
		Integer retailerid = parameter.getRetailerid();

		if(creditid == null) {
			creditid = 0;
		}

		if(retailerid == null) {
			retailerid = 0;
		}

		try {

			Optional<StoreCreditEntity> storeCreditEntityOptional = storeCreditEntityRepository.findByRetailerIdAndCreditId(retailerid, creditid);

			if(storeCreditEntityOptional.isPresent() == false) {
				return -1;
			}
			StoreCreditEntity storeCreditEntity = storeCreditEntityOptional.get();

			if(storeCreditEntity.getIsUsed()) {
				return -2;
			}

			if(storeCreditEntity.getIsDeleted()) {
				return -3;
			}

			if(storeCreditEntity.getCreditedAmount().longValue() < 1) {
				return -4;
			}

			storeCreditEntity.setIsDeleted(true);
			storeCreditEntity.setModifiedBy(sessionUserId);

			storeCreditEntityRepository.save(storeCreditEntity);

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setCartItem(SetCartItem parameter) {
		Integer cartID = parameter.getCartID();

		try {
			Optional<CartItemEntity> cartItemEntityOptional = cartItemEntityRepository.findById(cartID);
			if(cartItemEntityOptional.isPresent() == false) {
				return -1;
			}

			CartItemEntity cartItemEntity = cartItemEntityOptional.get();

			cartItemEntity.setBQ1(parameter.getBQ1().orElse(0));
			cartItemEntity.setBQ2(parameter.getBQ2().orElse(0));
			cartItemEntity.setBQ3(parameter.getBQ3().orElse(0));
			cartItemEntity.setBQ4(parameter.getBQ4().orElse(0));
			cartItemEntity.setBQ5(parameter.getBQ5().orElse(0));
			cartItemEntity.setBQ6(parameter.getBQ6().orElse(0));
			cartItemEntity.setBQ7(parameter.getBQ7().orElse(0));
			cartItemEntity.setBQ8(parameter.getBQ8().orElse(0));
			cartItemEntity.setBQ9(parameter.getBQ9().orElse(0));
			cartItemEntity.setBQ10(parameter.getBQ10().orElse(0));
			cartItemEntity.setBQ11(parameter.getBQ11().orElse(0));
			cartItemEntity.setNoOfPack(parameter.getNoOfPack().orElse(0));
			cartItemEntity.setTotalQty(parameter.getTotalQty().orElse(0));

			cartItemEntityRepository.saveAndFlush(cartItemEntity);
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}
}
