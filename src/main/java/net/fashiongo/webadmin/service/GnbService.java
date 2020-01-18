package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.model.pojo.request.GnbVendorGroupSaveRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfoResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbCollectionInfoResponse;
import net.fashiongo.webadmin.model.pojo.request.GnbCollectionSaveRequest;
import net.fashiongo.webadmin.data.entity.primary.GnbMenuCollectionEntity;

import java.util.List;

public interface GnbService {
	List<GnbVendorGroupInfoResponse> getGnbVendorGroupList(Integer wholeSalerId, String title);

	GnbVendorGroupInfoResponse createGnbVendorGroup(GnbVendorGroupSaveRequest request);

	void deleteGnbVendorGroupBatch(List<Integer> gnbVendorGroupIdList);

	GnbVendorGroupDetailResponse getGnbVendorGroup(int gnbVendorGroupId);

	GnbVendorGroupInfoResponse editGnbVendorGroup(int gnbVendorGroupId, GnbVendorGroupSaveRequest request);

	void deleteGnbVendorGroup(int gnbVendorGroupId);

	void activateGnbVendorGroup(int gnbVendorGroupId);
	
	
	
	List<GnbCollectionInfoResponse> getGnbCollectionList();
	
	GnbCollectionInfoResponse createGnbCollection(GnbCollectionSaveRequest request);
	
	GnbCollectionInfoResponse getGnbCollection(int gnbMenuCollectionId);

	GnbCollectionInfoResponse editGnbCollection(int gnbMenuCollectionId, GnbCollectionSaveRequest request);

	void deleteGnbCollection(int gnbMenuCollectionId);
	
	void activateGnbCollection(int gnbMenuCollectionId);
}
