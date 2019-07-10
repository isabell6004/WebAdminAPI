package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.admin.SecurityGroupPermissions;

import java.util.List;

public interface SecurityGroupProcedureRepository {

	List<SecurityGroupPermissions> up_wa_Security_GetPermissionGroup(int applicationId, int groupId);

	List<SecurityGroupPermissions> up_wa_Security_GetPermission(int applicationID, int userID, int groupID);
}
