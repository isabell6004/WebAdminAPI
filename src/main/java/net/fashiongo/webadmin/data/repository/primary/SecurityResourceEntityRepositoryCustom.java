package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.admin.Resource;

import java.util.List;

public interface SecurityResourceEntityRepositoryCustom {

	List<Resource> up_wa_Security_GetResource(String application, String resourceName, String parent, String type);
}
