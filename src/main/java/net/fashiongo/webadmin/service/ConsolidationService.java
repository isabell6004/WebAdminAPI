package net.fashiongo.webadmin.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import net.fashiongo.webadmin.data.entity.primary.*;
import net.fashiongo.webadmin.data.repository.primary.NewsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.dao.primary.ConsolidationDropoffRepository;
import net.fashiongo.webadmin.dao.primary.ConsolidationOrdersRepository;
import net.fashiongo.webadmin.dao.primary.ConsolidationRepository;
import net.fashiongo.webadmin.dao.primary.OrderPaymentStatusRepository;
import net.fashiongo.webadmin.dao.primary.OrderRepository;
import net.fashiongo.webadmin.dao.primary.OrderStatusChangeLogRepository;
import net.fashiongo.webadmin.dao.primary.ShipAddressRepository;
import net.fashiongo.webadmin.dao.primary.ShipMethodRepository;
import net.fashiongo.webadmin.model.pojo.consolidation.Consolidation;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationDetail;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationDetailList;
import net.fashiongo.webadmin.model.pojo.consolidation.ConsolidationSummary;
import net.fashiongo.webadmin.model.pojo.consolidation.TotalCount;
import net.fashiongo.webadmin.model.pojo.consolidation.Dto.DropOffConsolidationOrderDto;
import net.fashiongo.webadmin.model.pojo.consolidation.Dto.OrderConsolidationSummaryDto;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.Address;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConslidationDropOffSaveRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationDetailRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationDetailShippingAddressRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationMemoRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationDetailParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationDetailResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetConsolidationSummaryResponse;
import net.fashiongo.webadmin.model.pojo.consolidation.response.ShipMethodResponse;
import net.fashiongo.webadmin.model.pojo.payment.response.PaymentStatusResponse;
import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;
import net.fashiongo.webadmin.model.primary.consolidation.ConsolidatedOrder;
import net.fashiongo.webadmin.model.primary.consolidation.ConsolidationOrders;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
import net.fashiongo.webadmin.utility.Utility;

@Service
public class ConsolidationService extends ApiService {
	@Autowired @Qualifier("serviceJsonClient") private HttpClient httpClient;
	@Autowired private PaymentService paymentService;
	@Autowired private RewardService rewardService;
	@Autowired private ShipMethodRepository shipMethodRepository;
	@Autowired private ConsolidationRepository consolidationRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private OrderStatusChangeLogRepository orderStatusChangeLogRepository;
	@Autowired private OrderPaymentStatusRepository orderPaymentStatusRepository;
	@Autowired private ShipAddressRepository shipAddressRepository;
	@Autowired private ConsolidationDropoffRepository consolidationDropoffRepository;
	@Autowired private ConsolidationOrdersRepository consolidationOrdersRepository;
	@Autowired private NewsEntityRepository newsEntityRepository;
	private BigDecimal waivedFeeUpperBound = BigDecimal.valueOf(0.5); // waive 0 to 49 cents due to Stripe not accepting
	private static final int UPS_GROUND_ID = 3;
	private static final int FEDEX_GROUND_ID = 9;
	private static final int USPS_PRIORITY_ID = 25;
	
	@SuppressWarnings("unchecked")
	public GetConsolidationSummaryResponse getOrderConsolidationListSummary(GetConsolidationSummaryParameter q) {
		String spName = "up_wa_GetConsolidationSummary_v1";
		List<Object> params = new ArrayList<>();
		GetConsolidationSummaryResponse result = new GetConsolidationSummaryResponse();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastDayOfMonth = LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0).minusDays(1).minusSeconds(1);

        params.add(
				lastDayOfMonth
				.minusMonths(q.getPeriodType() == null ? 0 : q.getPeriodType()).toString());
		params.add(lastDayOfMonth.toString());

		List<Object> _result = jdbcHelper.executeSP(spName, params, ConsolidationSummary.class);
		
		result.setOrderConsolidationSummary((List<ConsolidationSummary>) _result.get(0));

		return result;
	}

	@SuppressWarnings("unchecked")
	public GetConsolidationResponse getConsolidationList(GetConsolidationParameter q) {
		String spName = "up_wa_GetConsolidation_v2";
		List<Object> params = new ArrayList<>();
		GetConsolidationResponse result = new GetConsolidationResponse();

		int iShipped = q.getBshipped() == null ? 2 : q.getBshipped();
        Boolean bShipped;

        switch (iShipped)
        {
            case 1:
                bShipped = true;
                break;
            case 0:
                bShipped = false;
                break;
            default:
                bShipped = null;
                break;
        }

		params.add(q.getPageNum());
		params.add(q.getPageSize());
		params.add(q.getDtFrom() != null ? q.getDtFrom().toString() : null);
		params.add(q.getDtTo() != null ? q.getDtTo().plusDays(1).toString() : null);
		params.add(q.getDateColumn());
		params.add(bShipped);
		params.add(q.getPaymentStatus());
		params.add(StringUtils.isEmpty(q.getWn()) ? null : q.getWn());
		params.add(StringUtils.isEmpty(q.getRn()) ? null : q.getRn());
		params.add(StringUtils.isEmpty(q.getPn()) ? null : q.getPn());
		params.add(StringUtils.isEmpty(q.getCn()) ? null : q.getCn());
		params.add(StringUtils.isEmpty(q.getTn()) ? null : q.getTn());
		params.add(q.getOrderBy());

		List<Object> _result = jdbcHelper.executeSP(spName, params, TotalCount.class, Consolidation.class);

		result.setTotalCount((List<TotalCount>) _result.get(0));
		result.setConsolidation((List<Consolidation>) _result.get(1));

		return result;
	}

	@SuppressWarnings("unchecked")
	public GetConsolidationDetailResponse getConsolidationDetailList(GetConsolidationDetailParameter q) {
		String spName = "up_wa_GetConsolidationDetail";
		List<Object> params = new ArrayList<>();
		GetConsolidationDetailResponse result = new GetConsolidationDetailResponse();

		params.add(q.getConsolidationId());

		List<Object> _result = jdbcHelper.executeSP(spName, params, ConsolidationDetail.class, ConsolidationDetailList.class);

		result.setConsolidationDetail((List<ConsolidationDetail>) _result.get(0));
		result.setConsolidationDetailList((List<ConsolidationDetailList>) _result.get(1));

		return result;
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
	public List<ShipMethodResponse> getConsolidationShipMethod() {
		// Get shipMethods
		List<ShipMethod> shipMethods =
				shipMethodRepository.findByActiveAndIdIn(
						true,
						Arrays.asList(
								UPS_GROUND_ID, // UPS
								FEDEX_GROUND_ID, // Fedex
								USPS_PRIORITY_ID
						)
				);

		// Parse to shipMethodResponses
		return CollectionUtils.isEmpty(shipMethods) ? new ArrayList<>() :
				shipMethods
					.stream()
					.map(shipMethod -> ShipMethodResponse.builder()
							.shipMethodId(shipMethod.getId())
							.shipMethodName(shipMethod.getShipMethodName())
							.build())
					.collect(Collectors.toList());
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	public void setConsolidationMemo(ConsolidationMemoRequest memo, String userName) throws Exception {
		if (memo == null) throw new Exception("No memo is requested");

		Optional<ConsolidationEntity> cOptional = consolidationRepository.findById(memo.getId());
		if (!cOptional.isPresent()) throw new Exception("No consolidation exists");

		ConsolidationEntity c = cOptional.get();
		c.setInhouseMemo(memo.getMemo());
		c.setModifiedBy(userName);
		c.setModifiedOn(LocalDateTime.now());
		consolidationRepository.save(c);
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	public void setConsolidationDetailShippingAddress(ConsolidationDetailShippingAddressRequest address, String userName) throws Exception {
		if (address == null) throw new Exception("No shipping address is requested");

		Optional<ConsolidationEntity> cOptional = consolidationRepository.findById(address.getId());
		if (!cOptional.isPresent()) throw new Exception("No consolidation exists");

		ConsolidationEntity c = cOptional.get();
		c.setStreetNo(address.getStreetNo());
		c.setCity(address.getCity());
		c.setState(address.getState());
		c.setZipcode(address.getZipCode());
		c.setCountry(address.getCountry());
		c.setCountryId(address.getCountryId());
		c.setIsCommercialAddress(address.isCommercialAddress());
		setConsolidationSum(c);
		c.setModifiedBy(userName);
		c.setModifiedOn(LocalDateTime.now());
		consolidationRepository.save(c);
	}

	private void setConsolidationSum(ConsolidationEntity c) {
		OrderConsolidationSummaryDto summaryDto = orderRepository.getOrderConsolidationSummary(c.getId());
		c.setOrderCount(summaryDto.getCount().intValue());
		c.setTotalAmount(summaryDto.getTotalAmount() == null ? BigDecimal.ZERO : summaryDto.getTotalAmount());
		c.setTotalQty(summaryDto.getTotalQty() == null ? 0 : summaryDto.getTotalQty());
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	public void checkConsolidationPaymentStatus(Integer consolidationId) {
		LocalDateTime modifiedOn = LocalDateTime.now();

		if (consolidationId == null || consolidationId == 0){
            return;
        }

        OrderPaymentStatus orderPaymentStatus = orderPaymentStatusRepository.findOneByReferenceIDAndIsOrder(consolidationId, 0);

        if(orderPaymentStatus.getOrderPaymentStatusID() == null || orderPaymentStatus.getOrderPaymentStatusID() == 0){
            return;
        }

        if(isAllConsolidationsInvalid(consolidationId)){
            //Update the order payment status id to 999
            orderPaymentStatus.setPrePaymentStatusID(orderPaymentStatus.getPaymentStatusID());
            orderPaymentStatus.setPaymentStatusID(999);
            // orderPaymentStatus.setModifiedOn(modifiedOn);
            orderPaymentStatus.setModifiedBy(Utility.getUsername());
            orderPaymentStatusRepository.save(orderPaymentStatus);
        }

	}

	private boolean isAllConsolidationsInvalid(Integer consolidationId) {
		int totalConsolidatedOrders = orderRepository.countByConsolidationId(consolidationId);
		int totalInvalidOrders = orderRepository.getInvalidConsolidationOrderCount(consolidationId);
		return totalConsolidatedOrders == totalInvalidOrders;
	}

	public Boolean checkAddressCommercial(ConsolidationDetailShippingAddressRequest addressRequest) throws Exception {
		// Check
		JsonResponse<?> result = httpClient.post(
				"/ups/checkAddressCommercial",
				new ObjectMapper().writeValueAsString(
						Address.builder()
								.street(addressRequest.getStreetNo())
								.city(addressRequest.getCity())
								.state(addressRequest.getState())
								.zipcode(addressRequest.getZipCode())
								.isCommercial(addressRequest.isCommercialAddress())
								.build()));
		if (result == null) throw new Exception("Cannot connect to check a commercial address");
		if (!result.isSuccess()) throw new Exception("Failed to check a commercial address");

		// Update verifiedOn
		Boolean isValid = (Boolean) result.getData();
		Optional<ShipAddressEntity> shipAddress = shipAddressRepository.findById(addressRequest.getId());
		if (shipAddress.isPresent()) {
			shipAddress.get().setVerifiedOn(
					(isValid != null && isValid) ? LocalDateTime.now() : null);
			shipAddressRepository.save(shipAddress.get());
		}

		return isValid;
	}

	public Boolean sendConsolidationEmail(Integer consolidationId) throws Exception {
		if (consolidationId == null) throw new Exception("No consolidation is requested");
		JsonResponse<?> result = httpClient.get("/email/sendConsolidationShipment/" + consolidationId);
		if (result == null) throw new Exception("Cannot send a consolidation email");
		return result.isSuccess();
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	public void setConsolidationDetail(ConsolidationDetailRequest request, String userName, String ipAddress) throws Exception {
		// Get a consolidation
		Integer consolidationId = request.getConsolidationId();
		if (consolidationId == null) throw new Exception("No consolidation is selected");
		ConsolidationEntity c = consolidationRepository.findOneById(consolidationId);
		if (c == null) throw new Exception("No consolidation exists");

		// Prepare to save
		setConsolidationShipMethod(c, request.getShipMethodId(), request.getIsShipped(), request.getShippedOn());
		setConsolidationAmount(c, request.getShippingCharge(), request.getActualShippingCharge(), request.getWaivedAmountByFg());
		c.setTrackingNumber(request.getTrackingNumber());
		c.setInhouseMemo(request.getInHouseMemo());
		c.setModifiedBy(userName);
		c.setModifiedOn(LocalDateTime.now());

		// Update orders
		setOrderCharges(c, request.getTrackingNumber(), userName, ipAddress);

		// Save a consolidation
		setConsolidationSum(c);
		consolidationRepository.save(c);

		// Update orderPaymentStatus
		setOrderPaymentStatus(c, userName);
	}

	public void setFullyShipped(Integer consolidationId, String userName, String ipAddress) throws Exception {
		// Update orders
		List<Order> ordersToShippingConfirmReward = setOrderStatuses(consolidationId, userName, ipAddress);

		if (CollectionUtils.isEmpty(ordersToShippingConfirmReward)) return;
		for (Order o : ordersToShippingConfirmReward) {
			rewardService.shippingConfirmReward(userName, o.getBuyerId(), o.getVendorId(), o.getId());
		}
	}

	private void setConsolidationShipMethod(ConsolidationEntity c, Integer shipMethodId, Boolean isShipped, String shippedOn) {
		Optional<ShipMethod> shipMethodOptional = shipMethodRepository.findById(shipMethodId);
		String shipMethodName =
				shipMethodOptional.isPresent() && shipMethodOptional.get().getShipMethodName() != null ?
						shipMethodOptional.get().getShipMethodName() :
						"Select Ship Method";
		c.setShipMethodId(shipMethodId);
		c.setShipMethodName(shipMethodName);
		c.setShippedOn(isShipped ? LocalDateTime.now() : LocalDate.parse(shippedOn, DateTimeFormatter.ofPattern("M/d/yyyy")).atStartOfDay());
	}

	private void setConsolidationAmount(
			ConsolidationEntity c,
			BigDecimal originalShippingCharge,
			BigDecimal actualShippingCharge,
			BigDecimal waivedAmountByFg) {
		BigDecimal totalShippingAmount = originalShippingCharge;
		BigDecimal appliedCouponAmount = null;
		BigDecimal waivedAmount = BigDecimal.ZERO;
		BigDecimal couponAmount = nullToZero(c.getCouponAmount());
		BigDecimal totalDiscounts = couponAmount.add(nullToZero(waivedAmountByFg));
		//Coupon amount should take precedence over waivedAmountByFG
		if (greaterThanZero(couponAmount)) {
			BigDecimal shippingChargeDiff = originalShippingCharge.subtract(couponAmount);
			appliedCouponAmount = lessThanZero(shippingChargeDiff) ? originalShippingCharge : c.getCouponAmount();
		}
		if (greaterThanZero(totalDiscounts)) {
			BigDecimal shippingChargeDiff = originalShippingCharge.subtract(totalDiscounts);
			// waivedAmount is dedicated to the amount waived due to Stripe not accepting charges ranging from 0 - .49 cents.
			if (greaterThanZero(shippingChargeDiff) && shippingChargeDiff.compareTo(waivedFeeUpperBound) < 0) {
				waivedAmount = shippingChargeDiff;
				shippingChargeDiff = shippingChargeDiff.subtract(waivedAmount);
			}
			totalShippingAmount = lessThanZero(shippingChargeDiff) ? BigDecimal.ZERO : shippingChargeDiff;
		}
		c.setShippingCharge(totalShippingAmount);
		c.setActualShippingCharge(actualShippingCharge);
		c.setAppliedCouponAmount(appliedCouponAmount);
		c.setOriginalShippingCharge(originalShippingCharge);
		c.setAppliedCouponAmount(appliedCouponAmount);
		c.setWavedAmount(waivedAmount);
		c.setWaivedAmountByFg(waivedAmountByFg);
	}

	private void setOrderCharges(ConsolidationEntity c, String trackingNumber, String userName, String ipAddress) {
		List<Order> orders = orderRepository.findByConsolidationIdAndIsConsolidatedAndOrderStatusIdNotIn(
				c.getId(), true, Arrays.asList(5/* Cancelled */, 7/* Backordered */));
		if (CollectionUtils.isEmpty(orders)) return;

		for (Order o : orders) {
			Integer orderStatusId = o.getOrderStatusId();

			// 5. Cancelled, 6. Returned
			if (orderStatusId == 5 ||
					orderStatusId == 51 ||
					orderStatusId == 52 ||
					orderStatusId == 53 ||
					orderStatusId == 6) {
				continue;
			}

			o.setConsolidationShipCharge(c.getShippingCharge());
			o.setShipTrackNo(trackingNumber);
			o.setModifiedBy(userName);
			o.setModifiedOn(LocalDateTime.now());
			orderRepository.save(o);

			// Save a log
			orderStatusChangeLogRepository.save(
					OrderStatusChangeLogEntity.builder()
							.orderId(o.getId())
							.isAdmin(false)
							.wholesalerId(0)
							.userName(userName)
							.accessIp(ipAddress)
							.orderStatusId(o.getOrderStatusId())
							.createdOn(LocalDateTime.now())
							.happenedAt(4)
							.referenceTypeId(1)
							.build());
		}
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	private List<Order> setOrderStatuses(Integer consolidationId, String userName, String ipAddress) throws Exception {
		// Get a consolidation
		if (consolidationId == null) throw new Exception("No consolidation is selected");
		ConsolidationEntity c = consolidationRepository.findOneById(consolidationId);
		if (c == null) throw new Exception("No consolidation exists");

		// Get isCardValid
		PaymentStatusResponse payment = paymentService.getCreditCardStatus(c.getCreditCardId(), c.getId());
		boolean isCardCompleted = payment != null && payment.getCardStatusId() == 1;

		// Update orders
		List<Order> orders = orderRepository.findByConsolidationIdAndIsConsolidatedAndOrderStatusIdNotIn(
				c.getId(), true, Arrays.asList(5/* Cancelled */, 7/* Backordered */));
		if (CollectionUtils.isEmpty(orders)) return null;

		List<Order> ordersToShippingConfirmReward = new ArrayList<>();
		for (Order o : orders) {
			Integer orderStatusId = o.getOrderStatusId();

			// 5. Cancelled, 6. Returned
			if (orderStatusId == 5 ||
					orderStatusId == 51 ||
					orderStatusId == 52 ||
					orderStatusId == 53 ||
					orderStatusId == 6) {
				continue;
			}

			// 1. Newly Placed, 2. Confirmed
			if (orderStatusId <= 2 && isCardCompleted) {
				o.setOrderStatusId(4); // Fully shipped
				o.setShipDate(LocalDateTime.now());

				o.setModifiedBy(userName);
				o.setModifiedOn(LocalDateTime.now());
				orderRepository.save(o);

				// Save a log
				orderStatusChangeLogRepository.save(
						OrderStatusChangeLogEntity.builder()
								.orderId(o.getId())
								.isAdmin(false)
								.wholesalerId(0)
								.userName(userName)
								.accessIp(ipAddress)
								.orderStatusId(o.getOrderStatusId())
								.createdOn(LocalDateTime.now())
								.happenedAt(4)
								.referenceTypeId(1)
								.build());

				// Shipping confirm reward
				ordersToShippingConfirmReward.add(o);
			}
		}
		return ordersToShippingConfirmReward;
	}

	private void setOrderPaymentStatus(ConsolidationEntity c, String userName) {
		OrderPaymentStatus ops =
				orderPaymentStatusRepository.findOneByReferenceIDAndIsOrder(c.getId(), 0);

		if (((isZero(c.getShippingCharge()) && isZero(c.getActualShippingCharge())) // When the shipping charge and actual shipping charge are zero,
				|| (c.getShippingCharge().compareTo(waivedFeeUpperBound) < 0 && c.getCouponAmount() != null)) // Or virtually zero,
			&& ops != null && ops.getPaymentStatusID() != 999 && ops.getOrderPaymentStatusID() != 0) {
			// Set the payment status to cancelled.
			ops.setPaymentStatusID(999);
			ops.setModifiedOn(LocalDateTime.now());
			ops.setModifiedBy(userName);
			orderPaymentStatusRepository.save(ops);
		} else if ((((greaterThanZero(c.getShippingCharge()) || greaterThanZero(c.getActualShippingCharge())) && c.getCouponAmount() == null) // When shipping charge or actual shipping charge are not zero,
				|| (c.getShippingCharge().compareTo(waivedFeeUpperBound) >= 0 && c.getCouponAmount() != null)) // Or virtually zero,
			&& ops != null && ops.getPaymentStatusID() == 999 && isAllConsolidationsInvalid(c.getId())) { // And not set to cancelled due to all the consolidation orders either being cancelled or removed
			// Set the paymentStatusId back to its previous paymentStatusId
			ops.setPaymentStatusID(ops.getPrePaymentStatusID() == null ? 1 : ops.getPrePaymentStatusID());
			ops.setPrePaymentStatusID(ops.getPaymentStatusID());
			ops.setModifiedOn(LocalDateTime.now());
			ops.setModifiedBy(userName);
			orderPaymentStatusRepository.save(ops);
		}
	}

	private boolean lessThanZero(BigDecimal me) {
		return me.compareTo(BigDecimal.ZERO) < 0;
	}

	private boolean isZero(BigDecimal me) {
		return me.compareTo(BigDecimal.ZERO) == 0;
	}

	private boolean greaterThanZero(BigDecimal me) {
		return me.compareTo(BigDecimal.ZERO) > 0;
	}

	private BigDecimal nullToZero(BigDecimal me) {
		return me == null ? BigDecimal.ZERO : me;
	}
	
	public DropOffConsolidationOrderDto getDropOffConsolidationOrder(String poNumber) throws Exception{
		List<ConsolidatedOrder> consolidationOrders = consolidationDropoffRepository.getDropOffConsolidationOrder(poNumber);
		List<DropOffConsolidationOrderDto> orders = DropOffConsolidationOrderDto.build(consolidationOrders);
		if(CollectionUtils.isEmpty(orders)) {
			throw new Exception("Order does not exist. Please try again.");
		}
		DropOffConsolidationOrderDto result = orders.get(0);
		if(result.getDropOffBy() != null || result.getDropOffTime() != null) {
			throw new Exception("This order has already been dropped off.");
		}
		if(result.getOrderStatusId().equals(5)) {
			throw new Exception("This consolidation order has been cancelled.");
		}
		return result;
	}
	
	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	public Boolean setDropOffConsolidationOrder(ConslidationDropOffSaveRequest dropoffSaveRequest) throws Exception {
		boolean result = false;
		Integer orderId = dropoffSaveRequest.getOrderId();
		ConsolidationOrders order = consolidationOrdersRepository.findOneByOrderId(orderId);
		if(order != null && order.getDroppedBy() != null && order.getReceivedOn() != null) {
			throw new Exception("This order has already been dropped off.");
		}
		if(order == null) {
			order = new ConsolidationOrders();
			order.setOrderId(orderId);
			order.setConsolidationId(dropoffSaveRequest.getConsolidationId());
		}
		order.setDroppedBy(dropoffSaveRequest.getDropOffBy());
		order.setReceivedBy(dropoffSaveRequest.getReceivedBy());
		order.setReceivedOn(dropoffSaveRequest.getDropOffTime());
		order.setItemQty(dropoffSaveRequest.getItemQty());
		order.setBoxQty(dropoffSaveRequest.getBoxQty());
		order.setBagQty(dropoffSaveRequest.getBagQty());
		order.setMemo(dropoffSaveRequest.getMemo());
		consolidationOrdersRepository.save(order);
		inactivateDropOffNotification(dropoffSaveRequest.getConsolidationId(), dropoffSaveRequest.getOrderId());
		return true;
	}

	private void inactivateDropOffNotification(Integer consolidationId, Integer orderId) {
		NewsEntity news = newsEntityRepository.getActiveNews(consolidationId, orderId);
		if (news == null) return;
		news.setActive(false);
		news.setShowBanner(false);
		news.setLastModifiedDateTime(LocalDateTime.now());
		news.setLastUser(Utility.getUsername());
		newsEntityRepository.save(news);
	}
	
	public JsonResponse<?> getConsolidationDetail(Integer consolidationId) {
		String url = "/pdf/consolidation/detail/" + consolidationId;
		return (JsonResponse<?>) httpClient.get(url);
	}
	public JsonResponse<?> getV2ConsolidationDetail(Integer consolidationId) {
		String url = "/v2/pdf/consolidation/detail/" + consolidationId;
		String finalUrl = url;
		url = Optional.ofNullable(Utility.getUsername())
				.map(s -> finalUrl.concat("?requestor=").concat(s))
				.orElse(url);
		return (JsonResponse<?>) httpClient.get(url);
	}

	public DropOffConsolidationOrderDto getConsolidationReceipt(Integer orderId) throws Exception {
		List<ConsolidatedOrder> consolidationOrders = consolidationDropoffRepository.getDropOffConsolidationReceipt(orderId);
		List<DropOffConsolidationOrderDto> orders = DropOffConsolidationOrderDto.build(consolidationOrders);
		if(CollectionUtils.isEmpty(orders)) {
			throw new Exception("Order does not exist. Please try again.");
		}
		DropOffConsolidationOrderDto result = orders.get(0);
		if(result.getDropOffBy() == null || result.getDropOffTime() == null) {
			throw new Exception("Did not received this order yet.");
		}
		if(result.getOrderStatusId().equals(5)) {
			throw new Exception("This order has been cancelled.");
		}
		return result;
	}
}
