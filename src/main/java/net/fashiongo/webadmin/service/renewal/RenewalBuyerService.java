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
import net.fashiongo.webadmin.data.repository.primary.buyer.AdvancedSearchRetailerRepository;
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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
	private WASavedSearchEntityRepository waSavedSearchEntityRepository;

	@Autowired
	private EntityActionLogEntityRepository entityActionLogEntityRepository;

	@Autowired
	private SecurityUserEntityRepository securityUserEntityRepository;
	
	@Autowired
	private BuyerAddressHistoryEntityRepository buyerAddressHistoryEntityRepository;
	
	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;

	@Autowired
	private AdvancedSearchRetailerRepository advancedSearchRetailerRepository;

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
					.billCountryId(Optional.ofNullable(retailerEntity.getBillCountryID()).orElse(229))
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
					.loginCount(Optional.ofNullable(retailerEntity.getLoginCount()).orElse(0))
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
					.buyerClass(retailerEntity.getBuyerClass())
					.amUserId(retailerEntity.getAmUserId())
					.build();

			AspnetMembershipEntity m = aspnetMembershipEntityRepository.findByUserName(retailerEntity.getUserID()).orElse(null);

			if (m != null) {

//				lastLockoutDate: "1753-12-31T16:00:00-08:00"
//				lastLoginDate: "8/10/2018 6:50:40 AM"

				String lastLoginDate = Optional.ofNullable(m.getLastLoginDate())
//						.map(dateTime -> ZonedDateTime.of(dateTime, ZoneOffset.UTC))
//						.map(dateTime -> dateTime.toInstant().atZone(ZoneId.systemDefault()).format(DATETIME_FORMAT))
						.map(dateTime -> dateTime.format(DATETIME_FORMAT))
						.orElse("n/a");

				boolean isLockOut = m.isLockedOut();

				/**
				 * FGM/176 fix
				 * Conversion is not needed since the date time is already in PST.
				 */
				String lastLockoutDateString = m.getLastLockoutDate() == null ? null : m.getLastLockoutDate().toString();
			
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
		String po = Optional.ofNullable(parameter.getPo()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String vendor = Optional.ofNullable(parameter.getVendor()).filter(s -> StringUtils.hasLength(s)).orElse(null);

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

			if(Optional.ofNullable(storeCreditEntity.getIsUsed()).orElse(false)) {
				return -2;
			}

			if(Optional.ofNullable(storeCreditEntity.getIsDeleted()).orElse(false)) {
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
			
			// save buyer billing address change history
			buyerAddressHistoryEntityRepository.save(BuyerAddressHistoryEntity.createForBillingAddress(retailerEntity, sessionUserId));
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
			
			// save buyer shipping address change history
			buyerAddressHistoryEntityRepository.save(BuyerAddressHistoryEntity.createForShippingAddress(xShipAddressEntity, sessionUserId));

			if(xShipAddressEntity.isDefaultYN()) {
				retailerEntityRepository.findById(xShipAddressEntity.getCustID2()).ifPresent(retailerEntity -> {
					retailerEntity.setStreetNo(shippingInfo.getShipAddress2());
					retailerEntity.setCity(shippingInfo.getShipCity2());
					retailerEntity.setState(shippingInfo.getShipState2());
					retailerEntity.setZipcode(shippingInfo.getShipZip2());
					retailerEntity.setCountry(shippingInfo.getShipCountry2());
					retailerEntity.setPhone(shippingInfo.getShipPhone());
					retailerEntity.setFax(shippingInfo.getShipFax());
					retailerEntity.setLastUser(sessionUserId);
                    retailerEntity.setLastModifiedDateTime(NOW);

					retailerEntityRepository.save(retailerEntity);
					
					// save buyer company address change history
					buyerAddressHistoryEntityRepository.save(BuyerAddressHistoryEntity.createForCompanyAddress(retailerEntity, sessionUserId));
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

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setSavedList(SetSavedListParameter parameter,String sessionUserId) {
		try {

			Integer savedid = Optional.ofNullable(parameter.getSavedid()).orElse(0);
			String savedname = Optional.ofNullable(parameter.getSavedname()).filter(s -> !StringUtils.isEmpty(s)).orElse("");
			String savedtype = Optional.ofNullable(parameter.getSavedtype()).filter(s -> !StringUtils.isEmpty(s)).orElse("");
			String display = Optional.ofNullable(parameter.getDisplay()).filter(s -> !StringUtils.isEmpty(s)).orElse("");
			String filter = Optional.ofNullable(parameter.getFilter()).filter(s -> !StringUtils.isEmpty(s)).orElse("");
			String remark = Optional.ofNullable(parameter.getRemark()).filter(s -> !StringUtils.isEmpty(s)).orElse("");

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());
			WASavedSearchEntity waSavedSearchEntity = null;

			if(savedid > 0 ) {
				waSavedSearchEntity = waSavedSearchEntityRepository.findById(savedid).orElseThrow(() -> new RuntimeException("NOT FOUND"));
			} else {
				waSavedSearchEntity = new WASavedSearchEntity();
				waSavedSearchEntity.setCreatedBy(sessionUserId);
				waSavedSearchEntity.setCreatedOn(NOW);
			}

			waSavedSearchEntity.setModifiedBy(sessionUserId);
			waSavedSearchEntity.setModifiedOn(NOW);
			waSavedSearchEntity.setSavedName(savedname);
			waSavedSearchEntity.setSavedType(savedtype);
			waSavedSearchEntity.setDisplayStr(display);
			waSavedSearchEntity.setFilterStr(filter);
			waSavedSearchEntity.setRemark(remark);
			waSavedSearchEntity.setActive(true);

			waSavedSearchEntityRepository.save(waSavedSearchEntity);

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	public SavedListResponse getSavedList(GetSavedListParameter parameter) {

		Integer pagenum = parameter.getPagenum();
		Integer pagesize = parameter.getPagesize();
		String keyword = Optional.ofNullable(parameter.getKeyword()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String orderby = Optional.ofNullable(parameter.getOrderby()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String savedtype = Optional.ofNullable(parameter.getSavedtype()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String type = Optional.ofNullable(parameter.getType()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		LocalDateTime startdate = Optional.ofNullable(parameter.getStartdate()).filter(s -> StringUtils.hasLength(s)).map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime enddate = Optional.ofNullable(parameter.getEnddate()).filter(s -> StringUtils.hasLength(s)).map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);

		Page<WASavedSearchEntity> waSavedSearchEntities = waSavedSearchEntityRepository.up_wa_GetSavedSearch(pagenum, pagesize, savedtype, type, keyword, startdate, enddate, orderby);

		return SavedListResponse.builder()
				.table(Arrays.asList(Total.builder().recCnt((int) waSavedSearchEntities.getTotalElements()).build()))
				.table1(
						waSavedSearchEntities.getContent().stream()
						.map(entity -> SavedSearch.builder()
								.active(entity.isActive())
								.createdBy(entity.getCreatedBy())
								.modifiedBy(entity.getModifiedBy())
								.createdOn(Optional.ofNullable(entity.getCreatedOn()).map(Timestamp::toLocalDateTime).orElse(null))
								.modifiedOn(Optional.ofNullable(entity.getModifiedOn()).map(Timestamp::toLocalDateTime).orElse(null))
								.displayStr(entity.getDisplayStr())
								.filterStr(entity.getFilterStr())
								.savedId(entity.getSavedId())
								.savedName(entity.getSavedName())
								.savedType(entity.getSavedType())
								.remark(entity.getRemark())
								.build())
						.collect(Collectors.toList())
				)
				.build();
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer delSavedList(DelSavedListParameter parameter, String sessionUserId) {
		try {

			Integer savedid = Optional.ofNullable(parameter.getSavedid()).orElse(0);

			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			if(savedid <= 0 ) {
				return -1;
			}

			waSavedSearchEntityRepository.findById(savedid).ifPresent(waSavedSearchEntity -> {
				waSavedSearchEntity.setModifiedBy(sessionUserId);
				waSavedSearchEntity.setModifiedOn(NOW);
				waSavedSearchEntity.setActive(false);

				waSavedSearchEntityRepository.save(waSavedSearchEntity);
			});

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public Integer setFilterList(SetFilterListParameter parameter, String sessionUserId) {
		try {

			Integer savedid = Optional.ofNullable(parameter.getSavedid()).orElse(0);
			String display = Optional.ofNullable(parameter.getDisplay()).orElse("");
			String filter = Optional.ofNullable(parameter.getFilter()).orElse("");
			Timestamp NOW = Timestamp.valueOf(LocalDateTime.now());

			if(savedid <= 0 ) {
				return -1;
			}

			waSavedSearchEntityRepository.findById(savedid).ifPresent(waSavedSearchEntity -> {
				waSavedSearchEntity.setModifiedBy(sessionUserId);
				waSavedSearchEntity.setModifiedOn(NOW);
				waSavedSearchEntity.setDisplayStr(display);
				waSavedSearchEntity.setFilterStr(filter);

				waSavedSearchEntityRepository.save(waSavedSearchEntity);
			});

			return 1;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return -99;
		}
	}

	public JsonResponse getAdminretailer(GetAdminRetailerParameter parameter) {
		Boolean csv = Optional.ofNullable(parameter.getCsv()).orElse(false);
		Integer pagenum = parameter.getPagenum();
		Integer pagesize = parameter.getPagesize();
		String location = Optional.ofNullable(parameter.getLocation()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean active = parameter.getActive();
		String checkoutfrom = Optional.ofNullable(parameter.getCheckoutfrom()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String checkoutto = Optional.ofNullable(parameter.getCheckoutto()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String loginfrom = Optional.ofNullable(parameter.getLoginfrom()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String loginto = Optional.ofNullable(parameter.getLoginto()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String registerfrom = Optional.ofNullable(parameter.getRegisterfrom()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String registerto = Optional.ofNullable(parameter.getRegisterto()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		String country = Optional.ofNullable(parameter.getCountry()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Integer currentstatus = Optional.ofNullable(parameter.getCurrentstatus()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		String documentupload = Optional.ofNullable(parameter.getDocumentupload()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean in1 = parameter.getIn1();
		Boolean in2 = parameter.getIn2();
		Integer logincountfrom = Optional.ofNullable(parameter.getLogincountfrom()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Integer logincountto = Optional.ofNullable(parameter.getLogincountto()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Boolean o = parameter.getO();
		Boolean online = parameter.getOnline();
		String orderby = Optional.ofNullable(parameter.getOrderby()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Integer ordercountfrom = Optional.ofNullable(parameter.getOrdercountfrom()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Integer ordercountto = Optional.ofNullable(parameter.getOrdercountto()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Integer ordervendorcountfrom = Optional.ofNullable(parameter.getOrdervendorcountfrom()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Integer ordervendorcountto = Optional.ofNullable(parameter.getOrdervendorcountto()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		String userid = Optional.ofNullable(parameter.getUserid()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean useridpartialmatch = parameter.getUseridpartialmatch();
		Integer retailerid = parameter.getRetailerid();
		String companyname =Optional.ofNullable( parameter.getCompanyname()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean companynamepartialmatch = parameter.getCompanynamepartialmatch();
		Boolean companynamestartswith = parameter.getCompanynamestartswith();
		String firstname = Optional.ofNullable(parameter.getFirstname()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean firstnamepartialmatch = parameter.getFirstnamepartialmatch();
		String lastname = Optional.ofNullable(parameter.getLastname()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Boolean lastnamepartialmatch = parameter.getLastnamepartialmatch();
		String state = Optional.ofNullable(parameter.getState()).filter(s -> StringUtils.hasLength(s)).orElse(null);
		Integer showid = Optional.ofNullable(parameter.getShowid()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		BigDecimal orderamountfrom = Optional.ofNullable(parameter.getOrderamountfrom()).filter(s -> StringUtils.hasLength(s)).map(s -> new BigDecimal(s)).orElse(null);
		BigDecimal orderamountto = Optional.ofNullable(parameter.getOrderamountto()).filter(s -> StringUtils.hasLength(s)).map(s -> new BigDecimal(s)).orElse(null);
		Integer wholesalerid = Optional.ofNullable(parameter.getWholesalerid()).filter(s -> StringUtils.hasLength(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Integer buyerclass = parameter.getBuyerclass();
		Boolean s = parameter.getS();

		List<Object> param = new ArrayList<>();

		if(csv == false) {
			param.add(pagenum);
			param.add(pagesize);
		}

		param.add(userid);
		param.add(useridpartialmatch);
		param.add(retailerid);
		param.add(currentstatus);
		param.add(companyname);
		param.add(companynamepartialmatch);
		param.add(companynamestartswith);
		param.add(firstname);
		param.add(firstnamepartialmatch);
		param.add(lastname);
		param.add(lastnamepartialmatch);
		param.add(active);
		param.add(online);
		param.add(documentupload);
		param.add(location);
		param.add(state);
		param.add(country);
		param.add(s);
		param.add(in1);
		param.add(in2);
		param.add(o);
		param.add(registerfrom);
		param.add(registerto);
		param.add(logincountfrom);
		param.add(logincountto);
		param.add(loginfrom);
		param.add(loginto);
		param.add(ordercountfrom);
		param.add(ordercountto);
		param.add(orderamountfrom);
		param.add(orderamountto);
		param.add(ordervendorcountfrom);
		param.add(ordervendorcountto);
		param.add(checkoutfrom);
		param.add(checkoutto);
		param.add(wholesalerid);
		param.add(orderby);
		param.add(showid);
		param.add(buyerclass);

		if(csv) {
			JsonResponse<AdminRetailerCSVResponse> response = new JsonResponse();
//			List<Object> up_wa_advancedSearch_retailer = jdbcHelper.executeSP("up_wa_AdvancedSearch_Retailer_CSV", param, AdminRetailerCSV.class);
//			List<AdminRetailerCSV> adminRetailerList = (List<AdminRetailerCSV>) up_wa_advancedSearch_retailer.get(0);

			List<AdminRetailerCSV> adminRetailerList = advancedSearchRetailerRepository.advancedSearchRetailerCSV(userid,
					useridpartialmatch,
					retailerid,
					currentstatus,
					companyname,
					companynamepartialmatch,
					companynamestartswith,
					firstname,
					firstnamepartialmatch,
					lastname,
					lastnamepartialmatch,
					active,
					online,
					documentupload,
					location,
					state,
					country,
					s,
					in1,
					in2,
					o,
					registerfrom,
					registerto,
					logincountfrom,
					logincountto,
					loginfrom,
					loginto,
					ordercountfrom,
					ordercountto,
					orderamountfrom,
					orderamountto,
					ordervendorcountfrom,
					ordervendorcountto,
					checkoutfrom,
					checkoutto,
					wholesalerid,
					orderby,
					showid,
					buyerclass);

			AdminRetailerCSVResponse data = AdminRetailerCSVResponse.builder()
					.table(adminRetailerList)
					.build();

			response.setSuccess(true);
			response.setData(data);

			return response;
		} else {
			List<Object> up_wa_advancedSearch_retailer = jdbcHelper.executeSP("up_wa_AdvancedSearch_Retailer", param, Total.class, AdminRetailer.class);
			List<Total> totalList = (List<Total>) up_wa_advancedSearch_retailer.get(0);
			List<AdminRetailer> adminRetailerList = (List<AdminRetailer>) up_wa_advancedSearch_retailer.get(1);

			JsonResponse<AdminRetailerResponse> response = new JsonResponse();
			AdminRetailerResponse data = AdminRetailerResponse.builder()
					.table(totalList)
					.table1(adminRetailerList)
					.build();

			response.setSuccess(true);
			response.setData(data);
			return response;
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public JsonResponse getModifiedByBuyerRead(GetModifiedByBuyerReadParameter parameter, String sessionUserId) {
		JsonResponse jsonResponse = new JsonResponse();

		try {
			EntityActionLogEntity actionLogEntity = EntityActionLogEntity.create(2, parameter.getRid(), 4005);
			entityActionLogEntityRepository.save(actionLogEntity);

			jsonResponse.setSuccess(true);
			jsonResponse.setCode(1);
			jsonResponse.setMessage("Saved successfully!");
		} catch (Exception e) {
			jsonResponse.setMessage(e.getMessage());
		}

		return jsonResponse;
	}

	public GetModifiedByBuyerResponse getModifiedByBuyer(GetModifiedByBuyerParameter parameter) {
		LocalDateTime fromdate = Optional.ofNullable(parameter.getFromdate())
				.filter(s -> StringUtils.hasLength(s))
				.map(s -> LocalDateTime.parse(s))
				.orElse(LocalDateTime.MIN);
		LocalDateTime todate = LocalDateTime.now().plusDays(1);

		List<ModifiedByBuyer> modifiedByBuyers = entityActionLogEntityRepository.up_wa_GetModifiedByBuyer(fromdate,todate);

		return GetModifiedByBuyerResponse.builder()
				.modifiedByBuyerList(modifiedByBuyers)
				.build();
	}
	
	public List<SecurityUserEntity> getRetailerSecurityUsers() {
		return securityUserEntityRepository.findAllActive();
	}	
}
