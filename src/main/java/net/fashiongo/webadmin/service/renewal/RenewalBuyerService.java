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
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

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

	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

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
					.confirmedBy(retailerEntity.getConfirmedBy())
					.confirmedOn(retailerEntity.getConfirmedOn())
					.currentComment(retailerEntity.getCurrentComment())
					.currentStatus(retailerEntity.getCurrentStatus())
					.email(retailerEntity.getEmail())
					.firstName(retailerEntity.getFirstName())
					.inHouseMemo(retailerEntity.getInHouseMemo())
					.invoiceFileName1(retailerEntity.getInvoiceFileName1())
					.invoiceFileName2(retailerEntity.getInvoiceFileName2())
					.lastLoginDate(retailerEntity.getLastLoginDate())
					.lastModifiedDateTime(retailerEntity.getLastModifiedDateTime())
					.lastName(retailerEntity.getLastName())
					.lastUser(retailerEntity.getLastUser())
					.loginCount(retailerEntity.getLoginCount())
					.memo(retailerEntity.getMemo())
					.mobilePhoneNo(retailerEntity.getMobilePhoneNo())
					.retailerId(retailerEntity.getRetailerID())
					.retailerPermitNo(retailerEntity.getRetailerPermitNo())
					.startingDate(retailerEntity.getStartingDate())
					.sellerPermitFileName(retailerEntity.getSellerPermitFileName())
					.terminatedNote(retailerEntity.getTerminatedNote())
					.userId(retailerEntity.getUserID())
					.webSite(retailerEntity.getWebSite())
					.webSiteYN(StringUtils.isEmpty(retailerEntity.getWebSite()) == false ? "Y" : "N")
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

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer delCartItem(Integer cartId) {
		try {
			Optional<CartItemEntity> cartItemEntityOptional = cartItemEntityRepository.findById(cartId);
			if(cartItemEntityOptional.isPresent() == false) {
				return -1;
			}

			cartItemEntityRepository.deleteById(cartId);
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setBillingInfo(BillingInfo billingInfo,String sessionUserId) {
		try {

			Integer retailerID = billingInfo.getRetailerID();

			Optional<RetailerEntity> retailerEntityOptional = retailerEntityRepository.findById(retailerID);
			if(retailerEntityOptional.isPresent() == false) {
				return -99;
			}

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			RetailerEntity retailerEntity = retailerEntityOptional.get();

			retailerEntity.setBillStreetNo(billingInfo.getBillStreetNo());
			retailerEntity.setBillStreetNo2(billingInfo.getBillStreetNo2());
			retailerEntity.setBillCity(billingInfo.getBillCity());
			retailerEntity.setBillSTATE(billingInfo.getBillSTATE());
			retailerEntity.setBillCountry(billingInfo.getBillCountry());
			retailerEntity.setBillCountryID(billingInfo.getBillCountryID());
			retailerEntity.setBillZipcode(billingInfo.getBillZipcode());
			retailerEntity.setBillPhone(billingInfo.getBillPhone());
			retailerEntity.setBillFax(billingInfo.getBillFax());
			retailerEntity.setLastUser(sessionUserId);
			retailerEntity.setLastModifiedDateTime(NOW);

			retailerEntityRepository.saveAndFlush(retailerEntity);
			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setShippingInfo(ShippingInfo shippingInfo, String sessionUserId) {
		try {

			Integer shipAddID = shippingInfo.getShipAddID();
			Optional<XShipAddressEntity> xShipAddressEntityOptional = xShipAddressEntityRepository.findById(shipAddID);

			if(xShipAddressEntityOptional.isPresent() ==false) {
				return -99;
			}
			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			XShipAddressEntity xShipAddressEntity = xShipAddressEntityOptional.get();

			xShipAddressEntity.setStoreNo(shippingInfo.getStoreNo());
			xShipAddressEntity.setShipAttention(shippingInfo.getShipAttention());
			xShipAddressEntity.setShipAddress2(shippingInfo.getShipAddress2());
			xShipAddressEntity.setShipAddress2B(shippingInfo.getShipAddress2B());
			xShipAddressEntity.setShipCity2(shippingInfo.getShipCity2());
			xShipAddressEntity.setShipState2(shippingInfo.getShipState2());
			xShipAddressEntity.setShipCountry2(shippingInfo.getShipCountry2());
			xShipAddressEntity.setShipCountry2ID(shippingInfo.getShipCountry2ID());
			xShipAddressEntity.setIsCommercialAddress(shippingInfo.isIsCommercialAddress());
			xShipAddressEntity.setShipZip2(shippingInfo.getShipZip2());
			xShipAddressEntity.setShipPhone(shippingInfo.getShipPhone());
			xShipAddressEntity.setShipFax(shippingInfo.getShipFax());
			xShipAddressEntity.setLastUser(sessionUserId);
			xShipAddressEntity.setLastModifiedDateTime(NOW);

			if(xShipAddressEntity.isDefaultYN()) {
				retailerEntityRepository.findById(xShipAddressEntity.getCustID2()).ifPresent(retailerEntity -> {
					retailerEntity.setStreetNo(shippingInfo.getShipAddress2());
					retailerEntity.setCity(shippingInfo.getShipCity2());
					retailerEntity.setState(shippingInfo.getShipState2());
					retailerEntity.setZipcode(shippingInfo.getShipZip2());
					retailerEntity.setCountry(shippingInfo.getShipCountry2());
					retailerEntity.setPhone(shippingInfo.getShipPhone());
					retailerEntity.setFax(shippingInfo.getShipFax());

					retailerEntityRepository.save(retailerEntity);
				});
			}

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setShippingStatus(SetShippingStatusParameter parameter, String sessionUserId) {
		try {

			String type = parameter.getType();
			if(StringUtils.isEmpty(type)) {
				return -1;
			}

			Integer shipaddid = parameter.getShipaddid();
			if(shipaddid == null) {
				return -1;
			}

			Boolean value = parameter.getValue();

			Optional<XShipAddressEntity> xShipAddressEntityOptional = xShipAddressEntityRepository.findById(shipaddid);

			if(xShipAddressEntityOptional.isPresent() ==false) {
				return -99;
			}

			XShipAddressEntity shipAddressEntityForStatusChange = xShipAddressEntityOptional.get();

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			switch (type.toLowerCase()) {
				case "active":
					shipAddressEntityForStatusChange.setActive(value);
					break;
				case "defaultyn":

					List<XShipAddressEntity> xShipAddressEntityList = xShipAddressEntityRepository.findAllByCustID2(shipAddressEntityForStatusChange.getCustID2());

					xShipAddressEntityList.forEach(x -> {
						xShipAddressEntityRepository.findById(x.getShipAddID()).ifPresent(xss -> {
							xss.setDefaultYN(false);
							xShipAddressEntityRepository.save(xss);
						});
					});
					shipAddressEntityForStatusChange.setDefaultYN(value);
					break;
			}

			shipAddressEntityForStatusChange.setLastUser(sessionUserId);
			shipAddressEntityForStatusChange.setLastModifiedDateTime(NOW);
			xShipAddressEntityRepository.save(shipAddressEntityForStatusChange);

			retailerEntityRepository.findById(shipAddressEntityForStatusChange.getCustID2()).ifPresent(retailerEntity -> {
				retailerEntity.setStreetNo(shipAddressEntityForStatusChange.getShipAddress2());
				retailerEntity.setCity(shipAddressEntityForStatusChange.getShipCity2());
				retailerEntity.setState(shipAddressEntityForStatusChange.getShipState2());
				retailerEntity.setZipcode(shipAddressEntityForStatusChange.getShipZip2());
				retailerEntity.setCountry(shipAddressEntityForStatusChange.getShipCountry2());
				retailerEntity.setPhone(shipAddressEntityForStatusChange.getShipPhone());
				retailerEntity.setFax((shipAddressEntityForStatusChange.getShipFax()));
				retailerEntityRepository.save(retailerEntity);
			});

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setCommunicationLog(SetCommunicationLogParameter parameter) {
		try {

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());
			Integer retailerid = parameter.getRetailerid();
			Integer reasonid = parameter.getReasonid();
			Integer communicationid = Optional.ofNullable(parameter.getCommunicationid()).orElse(0);
			Timestamp commnicationedOn = Optional.ofNullable(parameter.getCommunicatedon()).map(s -> new Timestamp(new Date(s).getTime())).orElse(NOW);
			String contactedby = Optional.ofNullable(parameter.getContactedby()).orElse("");
			String companyName = Optional.ofNullable(parameter.getCompanyname()).orElse("");
			String notes = Optional.ofNullable(parameter.getNotes()).orElse("");
			Boolean confirmed1 = Optional.ofNullable(parameter.getConfirmed1()).orElse(false);
			Boolean confirmed2 = Optional.ofNullable(parameter.getConfirmed2()).orElse(false);

			if(reasonid == null || retailerid == null || reasonid < 1) {
				return -1;
			}

			LogCommunicationEntity logCommunicationEntity = new LogCommunicationEntity();

			if(communicationid > 0) {
				Optional<LogCommunicationEntity> logCommunicationEntityOptional = logCommunicationEntityRepository.findById(communicationid);
				if(logCommunicationEntityOptional.isPresent() == false) {
					return -99;
				}
				logCommunicationEntity = logCommunicationEntityOptional.get();
			} else {
				logCommunicationEntity.setRetailerID(retailerid);
				logCommunicationEntity.setCreatedBy(contactedby);
				logCommunicationEntity.setCreatedOn(commnicationedOn);
			}

			logCommunicationEntity.setCompanyName(companyName);
			logCommunicationEntity.setReasonID(reasonid);
			logCommunicationEntity.setCommunicatedOn(commnicationedOn);
			logCommunicationEntity.setContactedBy(contactedby);
			logCommunicationEntity.setModifiedBy(contactedby);
			logCommunicationEntity.setModifiedOn(NOW);
			logCommunicationEntity.setNotes(notes);

			if(confirmed1) {
				logCommunicationEntity.setCheckedBy(contactedby);
				logCommunicationEntity.setCheckedOn(NOW);
			} else {
				logCommunicationEntity.setCheckedBy("");
				logCommunicationEntity.setCheckedOn(null);
			}

			if(confirmed2) {
				logCommunicationEntity.setCheckedBy2(contactedby);
				logCommunicationEntity.setCheckedOn2(NOW);
			} else {
				logCommunicationEntity.setCheckedBy2("");
				logCommunicationEntity.setCheckedOn2(null);
			}

			logCommunicationEntityRepository.save(logCommunicationEntity);

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setAccountLockOut(SetAccountLockOutParameter parameter) {
		try {

			Integer retailerId = Optional.ofNullable(parameter.getRetailerId()).orElse(0);
			if(retailerId == 0) {
				return -1;
			}

			RetailerEntity retailerEntity = retailerEntityRepository.findById(retailerId).get();

			String retailerGUID = retailerEntity.getRetailerGUID();

			AspnetMembershipEntity aspnetMembershipEntity = aspnetMembershipEntityRepository.findById(retailerGUID).orElse(null);

			aspnetMembershipEntity.setLockedOut(false);
			aspnetMembershipEntity.setFailedPasswordAttemptCount(0);

			aspnetMembershipEntityRepository.save(aspnetMembershipEntity);

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setLogEmailSent(SetLogEmailSentParameter parameter,String sessionUserId) {
		try {

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());
			Integer retailerId = Optional.ofNullable(parameter.getRetailerid()).orElse(0);

			LogEmailSentEntity logEmailSentEntity = new LogEmailSentEntity();

			Integer type = parameter.getType();

			if (type == 999) logEmailSentEntity.setEmailContents(parameter.getEmailcontent());
			if (type == 2) {
				logEmailSentEntity.setReferenceID(parameter.getReferenceid());
				logEmailSentEntity.setReferenceText(parameter.getEmailcontent());
			}

			logEmailSentEntity.setSentBy(sessionUserId);
			logEmailSentEntity.setSentOn(NOW);
			logEmailSentEntity.setRetailerID(retailerId);
			logEmailSentEntity.setSentEmailTypeID(type);
			logEmailSentEntity.setReferenceID(parameter.getReferenceid());
			logEmailSentEntity.setCompanyName(parameter.getCompanyname());
			logEmailSentEntityRepository.save(logEmailSentEntity);

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	public JsonResponse sendBuyerEmail(SendBuyerEmailParameter parameter) {
		Map<String,Object> body = new HashMap<>();
		String httpMethod = "GET";
		String type = parameter.getType();
		String username = parameter.getUsername();
		String reason = parameter.getReason();
		String d = parameter.getD();
		String recipient = parameter.getRecipient();
		String title = parameter.getTitle();
		String content = parameter.getContent();
		UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromPath("/email");

		switch (type)
		{
			case "a": // approved

				componentsBuilder.path("/sendRetailerStatusUpdateApproval")
						.path("/")
						.path(username);

				break;
			case "d": // denied
				componentsBuilder.path("/sendRetailerStatusUpdateDenial")
						.path("/")
						.path(username)
						.path("/")
						.path(reason);

				break;
			case "rd": // document request
				List<String> arr = Arrays.asList(d.split(",")).stream()
						.filter(s -> StringUtils.isEmpty(s) == false)
						.collect(Collectors.toList());

				List<String> arr2 = Arrays.asList("sellerpermit", "invoice1", "invoice2", "other");
				List<String> mergeArr = new ArrayList<>();

				for (int i = 0; i < arr.size() ;i++) {

					if(arr.get(i).equals("true")) {
						mergeArr.add(arr2.get(i));
					}
				}

				String dParam = mergeArr.stream()
						.reduce((s, s2) -> s + "," + s2).get();

				componentsBuilder.path("/sendDocumentRequest")
						.path("/")
						.path(username)
						.queryParam("d",dParam);
			break;
			default: // normal
				String contentHtml = "<div style='white-space:pre-line;'>" + content + "</span>";

				body.put("additionalInfo",null);
				body.put("message",contentHtml);
				body.put("recipientEmailAddress",recipient);
				body.put("recipientName","FG Retailer");
				body.put("senderEmailAddress","info@fashiongo.net");
				body.put("senderName","FG Admin");
				body.put("subject",title);

				componentsBuilder.path("/sendInfoEmail");

				httpMethod = "POST";
			break;
		}

		String url = componentsBuilder.build().encode().toUriString();
		if (httpMethod == "GET")
		{
			return httpClient.get(url);
		}
		else // POST
		{
			return httpClient.postObject(url,body);
		}
	}
}
