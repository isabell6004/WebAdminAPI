package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.model.pojo.request.GnbVendorGroupSaveRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfoResponse;

import java.util.List;

public interface GnbService {
	List<GnbVendorGroupInfoResponse> getGnbVendorGroupList(Integer wholeSalerId, String title);

	GnbVendorGroupInfoResponse createGnbVendorGroup(GnbVendorGroupSaveRequest request);

	void deleteGnbVendorGroupBatch(List<Integer> gnbVendorGroupIdList);

	GnbVendorGroupDetailResponse getGnbVendorGroup(int gnbVendorGroupId);

	GnbVendorGroupInfoResponse editGnbVendorGroup(int gnbVendorGroupId, GnbVendorGroupSaveRequest request);

	void deleteGnbVendorGroup(int gnbVendorGroupId);

	void activateGnbVendorGroup(int gnbVendorGroupId);
}
