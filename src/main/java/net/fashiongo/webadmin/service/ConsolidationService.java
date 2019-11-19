package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.fashiongo.webadmin.dao.primary.ConsolidationRepository;
import net.fashiongo.webadmin.dao.primary.OrderPaymentStatusRepository;
import net.fashiongo.webadmin.dao.primary.OrderRepository;
import net.fashiongo.webadmin.dao.primary.ShipMethodRepository;
import net.fashiongo.webadmin.data.entity.primary.ConsolidationEntity;
import net.fashiongo.webadmin.data.entity.primary.ShipMethod;
import net.fashiongo.webadmin.model.pojo.consolidation.OrderConsolidationSummary;
import net.fashiongo.webadmin.dao.primary.ShipAddressRepository;
import net.fashiongo.webadmin.data.entity.primary.ShipAddressEntity;
import net.fashiongo.webadmin.model.pojo.consolidation.Dto.OrderConsolidationSummaryDto;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationDetailShippingAddressRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.ConsolidationMemoRequest;
import net.fashiongo.webadmin.model.pojo.consolidation.parameter.Address;
import net.fashiongo.webadmin.model.pojo.consolidation.response.ShipMethodResponse;
import net.fashiongo.webadmin.utility.JsonResponse;

import net.fashiongo.webadmin.model.pojo.consolidation.parameter.GetOrderConsolidationSummaryParameter;
import net.fashiongo.webadmin.model.pojo.consolidation.response.GetOrderConsolidationSummaryResponse;
import net.fashiongo.webadmin.model.primary.OrderPaymentStatus;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.Utility;

@Service
public class ConsolidationService extends ApiService {
	@Autowired @Qualifier("serviceJsonClient") private HttpClient httpClient;
	@Autowired private ShipMethodRepository shipMethodRepository;
	@Autowired private ConsolidationRepository consolidationRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private OrderPaymentStatusRepository orderPaymentStatusRepository;
	@Autowired private ShipAddressRepository shipAddressRepository;

	@SuppressWarnings("unchecked")
	public GetOrderConsolidationSummaryResponse getOrderConsolidationSummary(GetOrderConsolidationSummaryParameter q) {
		String spName = "up_wa_GetConsolidationSummary";
		List<Object> params = new ArrayList<Object>();
		GetOrderConsolidationSummaryResponse result = new GetOrderConsolidationSummaryResponse();
		params.add(q.getFromDate());
		params.add(q.getToDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, OrderConsolidationSummary.class);
		
		result.setOrderConsolidationSummary((List<OrderConsolidationSummary>) _result.get(0));
		return result;
	}

	/*
	@SuppressWarnings("unchecked")
	public GetConsolidationResponse getConsolidation(getConsolidationParameter q) {
		String spName = "up_wa_GetConsolidation";
		List<Object> params = new ArrayList<Object>();
		GetConsolidationResponse result = new GetConsolidationResponse();
		params.add(q.getFromDate());
		params.add(q.getToDate());

		List<Object> _result = jdbcHelper.executeSP(spName, params, Consolidation.class);
		
		result.setConsolidation((List<Consolidation>) _result.get(0));
		return result;
	}
	*/

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.READ_UNCOMMITTED)
	public List<ShipMethodResponse> getConsolidationShipMethod() {
		// Get shipMethods
		List<ShipMethod> shipMethods =
				shipMethodRepository.findByActiveAndIdIn(
						true,
						Arrays.asList(
								3, // UPS
								9 // Fedex
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
	public void setConsolidationMemo(ConsolidationMemoRequest memoRequest, String userName) throws Exception {
		if (memoRequest == null) throw new Exception("No memo is requested");

		Optional<ConsolidationEntity> cOptional = consolidationRepository.findById(memoRequest.getId());
		if (!cOptional.isPresent()) throw new Exception("No consolidation exists");

		ConsolidationEntity c = cOptional.get();
		c.setInhouseMemo(memoRequest.getMemo());
		c.setModifiedBy(userName);
		c.setModifiedOn(LocalDateTime.now());
		consolidationRepository.save(c);
	}

	@Transactional(transactionManager = "primaryTransactionManager", isolation = Isolation.SERIALIZABLE)
	public void setConsolidationDetailShippingAddress(ConsolidationDetailShippingAddressRequest addressRequest, String userName) throws Exception {
		if (addressRequest == null) throw new Exception("No shipping address is requested");

		Optional<ConsolidationEntity> cOptional = consolidationRepository.findById(addressRequest.getId());
		if (!cOptional.isPresent()) throw new Exception("No consolidation exists");

		ConsolidationEntity c = cOptional.get();
		c.setStreetNo(addressRequest.getStreetNo());
		c.setCity(addressRequest.getCity());
		c.setState(addressRequest.getState());
		c.setZipcode(addressRequest.getZipCode());
		c.setCountry(addressRequest.getCountry());
		c.setCountryId(addressRequest.getCountryId());
		c.setIsCommercialAddress(addressRequest.isCommercialAddress());
		setConsolidationSum(c);
		c.setModifiedBy(userName);
		c.setModifiedOn(LocalDateTime.now());
		consolidationRepository.save(c);
	}

	private void setConsolidationSum(ConsolidationEntity c) {
		OrderConsolidationSummaryDto summaryDto = orderRepository.getOrderConsolidationSummary(c.getId());
		c.setOrderCount(summaryDto.getCount().intValue());
		c.setTotalAmount(summaryDto.getTotalAmount());
		c.setTotalQty(summaryDto.getTotalQty());
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

        int totalConsolidatedOrders = orderRepository.countByConsolidationId(consolidationId);

        //The x.IsConsolidated == false is for the case where the consolidated order was removed.
        int totalInvalidOrders = orderRepository.getInvalidConsolidationOrderCount(consolidationId);

        if(totalConsolidatedOrders == totalInvalidOrders){
            //Update the order payment status id to 999
            orderPaymentStatus.setPrePaymentStatusID(orderPaymentStatus.getPaymentStatusID());
            orderPaymentStatus.setPaymentStatusID(999);
            // orderPaymentStatus.setModifiedOn(modifiedOn);
            orderPaymentStatus.setModifiedBy(Utility.getUsername());
            orderPaymentStatusRepository.save(orderPaymentStatus);
        }

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
}
