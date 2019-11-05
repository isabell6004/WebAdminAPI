package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.WASavedSearchEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface WASavedSearchEntityRepositoryCustom {

	Page<WASavedSearchEntity> up_wa_GetSavedSearch(
	Integer pageNum
	,Integer pageSize
	,String savedType
	,String type
	,String keyword
	,LocalDateTime startDate
	,LocalDateTime endDate
	,String orderBy);
}
