package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfo;

import java.util.List;

public interface GnbService {
	List<GnbVendorGroupInfo> getGnbVendorGroupList(Integer wholeSalerId, String title);

	void activateGnbVendorGroup(int gnbVendorGroupId);
}
