package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.message.GetRetailerRatingParameter;
import net.fashiongo.webadmin.data.model.message.RetailerRating;
import net.fashiongo.webadmin.data.model.message.response.GetRetailerRatingResponse;
import net.fashiongo.webadmin.data.repository.primary.RetailerRatingEntityRepository;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorRatingParameter;
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

	public GetRetailerRatingResponse getRetailerRating(GetRetailerRatingParameter param) {

		Integer retailerID = param.getRetailerID();
		Integer wholeSalerID = param.getWholeSalerID();
		Integer pageNum = param.getPageNum();
		Integer pageSize = param.getPageSize();
		Boolean active = param.getActive();
		LocalDateTime fromDate = Optional.ofNullable(param.getFromDate()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		LocalDateTime toDate = Optional.ofNullable(param.getToDate()).map(s -> new Date(s)).map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).orElse(null);
		String additional = param.getAdditional();
		String orderby = param.getOrderby();

		Page<RetailerRating> retailerRatings = retailerRatingEntityRepository.up_wa_GetRetailerRating(retailerID, wholeSalerID, pageNum, pageSize, active, additional, fromDate, toDate, orderby);

		return GetRetailerRatingResponse.builder()
				.recCnt(Arrays.asList(Total.builder().recCnt((int) retailerRatings.getTotalElements()).build()))
				.retailerRatingList(retailerRatings.getContent())
				.build();
	}
}
