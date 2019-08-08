package net.fashiongo.webadmin.data.repository.primary.procedure;


import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendor;
import net.fashiongo.webadmin.data.model.admin.UserMappingVendorAssigned;

import java.util.List;

@Getter
@Builder
public class ResultGetUserMappingVendor {

	private List<UserMappingVendor> userMappingVendorList;

	private List<UserMappingVendorAssigned> userMappingVendorAssignedList;
}
