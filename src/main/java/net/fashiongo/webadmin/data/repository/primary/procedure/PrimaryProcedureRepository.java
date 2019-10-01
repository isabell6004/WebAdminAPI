package net.fashiongo.webadmin.data.repository.primary.procedure;

import net.fashiongo.webadmin.data.model.admin.SecurityMenus2;
import net.fashiongo.webadmin.data.model.sitemgmt.*;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PrimaryProcedureRepository {

	ResultGetUserMappingVendor up_wa_GetUserMappingVendor(Integer userId, String alphabet, String companyType, String categories, String vendorType, String vendorKeyword);

	List<SecurityMenus2> up_wa_GetSecurityMenus2(String menuName, Integer parentMenuId, Integer applicationId, Integer active);

	ResultGetCollectionCategory up_wa_GetCollectionCategory(Integer categoryID, Integer expandAll);

	List<CategoryList> up_wa_GetCategoryList(Integer categoryID, Integer expandAll);

	Page<TodayDealDetail> up_wa_GetAdminTodayDeal(Integer pageNumber, Integer pageSize, Integer wholeSalerID, String checkedCompanyNo, Integer categoryId, BigDecimal priceFrom, BigDecimal priceTo, Date dateFrom, Date dateTo, Boolean isActive, String orderBy);

	ResultGetVendorList up_GetVendorList();

	ResultGetAdminTodayDealCalendarList up_wa_GetAdminTodayDealCalendarList(Date sDate, Integer wholeSalerID);

	ResultGetCategoryVendorList up_wa_GetCategoryVendorList(Integer categoryID, String vendorName);

	GetAdminTodayDealCalendarResult up_wa_GetAdminTodayDealCalendar(Date dateFrom, Date dateTo);

	List<DMRequest> up_wa_GetFGCatalog(Integer pageNumber
			, Integer pageSize
			, String status
			, Integer vendorStatus
			, Integer wholeSalerID
			, String companyTypeCD
			, Date dateFrom
			, Date dateTo
			, String orderBy);
}
