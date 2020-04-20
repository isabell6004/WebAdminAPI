package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.message.RetailerRating;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface RetailerRatingEntityRepositoryCustom {

	Page<RetailerRating> up_wa_GetRetailerRating(Integer retailerID
			,Integer WholeSalerID
			,int pageNum
			,int pageSize
			,Boolean active
			,LocalDateTime from
			,LocalDateTime to
			,String orderBy
			,Integer score, String retailerCompanyName, String wholesalerCompanyName);
}
