package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.message.GetRetailerRatingParameter;
import net.fashiongo.webadmin.data.model.message.GetVendorRatingParameter;
import net.fashiongo.webadmin.data.model.message.RetailerRating;
import net.fashiongo.webadmin.data.model.message.VendorRating;
import net.fashiongo.webadmin.data.model.message.response.GetRetailerRatingResponse;
import net.fashiongo.webadmin.data.model.message.response.GetVendorRatingResponse;
import net.fashiongo.webadmin.data.repository.primary.RetailerRatingEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.WholeSalerRatingEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class RenewalMessageService {

	@Autowired
	private RetailerRatingEntityRepository retailerRatingEntityRepository;

	@Autowired
	private WholeSalerRatingEntityRepository wholeSalerRatingEntityRepository;
	public GetRetailerRatingResponse getRetailerRating(GetRetailerRatingParameter param) {

		Integer retailerID = param.getRetailerId();
		Integer wholeSalerID = param.getWholesalerId();
		Integer pageNum = param.getPageNum();
		Integer pageSize = param.getPageSize();
		Boolean active = param.getActive();
		LocalDateTime fromDate = Optional.ofNullable(param.getFromDate()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime toDate = Optional.ofNullable(param.getToDate()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);

		String orderby = param.getOrderby();
		Integer score = param.getScore();
		String retailerCompanyName = param.getRetailerCompanyName();
		String wholesalerCompanyName = param.getWholesalerCompanyName();

		Page<RetailerRating> retailerRatings
				= retailerRatingEntityRepository.up_wa_GetRetailerRating(retailerID, wholeSalerID, pageNum, pageSize, active, fromDate, toDate, orderby, score, retailerCompanyName, wholesalerCompanyName);

		return GetRetailerRatingResponse.builder()
				.recCnt(Arrays.asList(Total.builder().recCnt((int) retailerRatings.getTotalElements()).build()))
				.retailerRatingList(retailerRatings.getContent())
				.build();
	}

	public GetVendorRatingResponse getVendorRating(GetVendorRatingParameter param) {
		Integer retailerId = param.getRetailerId();
		Integer wholesalerId = param.getWholesalerId();
		Integer pageNum = param.getPageNum();
		Integer pageSize = param.getPageSize();
		Boolean active = param.getActive();
		LocalDateTime fromDate = Optional.ofNullable(param.getFromDate()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime toDate = Optional.ofNullable(param.getToDate()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		String orderby = param.getOrderby();
		Integer score = param.getScore();
		String retailerCompanyName = param.getRetailerCompanyName();
		String wholesalerCompanyName = param.getWholesalerCompanyName();

		Page<VendorRating> vendorRatings
				= wholeSalerRatingEntityRepository.up_wa_GetVendorInfoRating(retailerId, wholesalerId, pageNum, pageSize, active, fromDate, toDate, orderby, score, retailerCompanyName, wholesalerCompanyName);
		return GetVendorRatingResponse.builder()
				.recCnt(Arrays.asList(Total.builder().recCnt((int) vendorRatings.getTotalElements()).build()))
				.vendorRatingList(vendorRatings.getContent())
				.build();
	}
}
