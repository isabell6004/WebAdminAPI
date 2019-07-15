package net.fashiongo.webadmin.data.repository.primary.procedure;

public interface PrimaryProcedureRepository {

	ResultGetUserMappingVendor up_wa_GetUserMappingVendor(Integer userId, String alphabet, String companyType, String categories, String vendorType, String vendorKeyword);

}
