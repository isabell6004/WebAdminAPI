package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.message.VendorRating;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface WholeSalerRatingEntityRepositoryCustom {

	Page<VendorRating> up_wa_GetVendorInfoRating(Integer retailerID
			, Integer WholeSalerID
			, int pageNum
			, int pageSize
			, Boolean active
			, String additional
			, LocalDateTime from
			, LocalDateTime to
			, String orderBy);
}
