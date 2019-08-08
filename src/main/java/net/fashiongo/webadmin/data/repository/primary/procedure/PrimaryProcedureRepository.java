package net.fashiongo.webadmin.data.repository.primary.procedure;

import net.fashiongo.webadmin.data.model.admin.SecurityMenus2;

import java.util.List;

public interface PrimaryProcedureRepository {

	ResultGetUserMappingVendor up_wa_GetUserMappingVendor(Integer userId, String alphabet, String companyType, String categories, String vendorType, String vendorKeyword);

	List<SecurityMenus2> up_wa_GetSecurityMenus2(String menuName, Integer parentMenuId, Integer applicationId, Integer active);
}
