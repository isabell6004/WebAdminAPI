package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.GnbVendorGroupMapRepository;
import net.fashiongo.webadmin.dao.primary.GnbVendorGroupRepository;
import net.fashiongo.webadmin.dao.primary.SiteSettingRepository;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapId;
import net.fashiongo.webadmin.data.entity.primary.SiteSettingEntity;
import net.fashiongo.webadmin.exception.NotFoundGnbVendorGroup;
import net.fashiongo.webadmin.exception.NotFoundSiteSetting;
import net.fashiongo.webadmin.model.pojo.request.GnbVendorGroupSaveRequest;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupDetailResponse;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfoResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GnbServiceImpl implements GnbService {

	private final GnbVendorGroupRepository gnbVendorGroupRepository;

	private final GnbVendorGroupMapRepository gnbVendorGroupMapRepository;

	private final SiteSettingRepository siteSettingRepository;

	@Autowired
	public GnbServiceImpl(GnbVendorGroupRepository gnbVendorGroupRepository,
						  GnbVendorGroupMapRepository gnbVendorGroupMapRepository,
						  SiteSettingRepository siteSettingRepository) {
		this.gnbVendorGroupRepository = gnbVendorGroupRepository;
		this.gnbVendorGroupMapRepository = gnbVendorGroupMapRepository;
		this.siteSettingRepository = siteSettingRepository;
	}

	@Override
	public List<GnbVendorGroupInfoResponse> getGnbVendorGroupList(Integer wholeSalerId, String title) {
		Integer activeGnbVendorGroupId = siteSettingRepository.findById(1)
				.orElseThrow(NotFoundSiteSetting::new)
				.getGnbVendorGroupId();

		List<Integer> gnbVendorGroupIdList = gnbVendorGroupRepository.findIdListByWholeSalerIdAndTitle(wholeSalerId, title);

		return gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(gnbVendorGroupIdList)
				.stream()
				.map(gnbVendorGroupEntity -> GnbVendorGroupInfoResponse.of(gnbVendorGroupEntity, gnbVendorGroupEntity.getVendorGroupId().equals(activeGnbVendorGroupId)))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public GnbVendorGroupInfoResponse createGnbVendorGroup(GnbVendorGroupSaveRequest request) {
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();

		GnbVendorGroupEntity gnbVendorGroupEntity = createGnbVendorGroup(request, now, username);
		gnbVendorGroupEntity.setVendorGroupMaps(createGnbVendorGroupMap(gnbVendorGroupEntity.getVendorGroupId(), request.getWholeSalerIdList(), now, username));

		return GnbVendorGroupInfoResponse.of(gnbVendorGroupEntity, false);
	}

	private GnbVendorGroupEntity createGnbVendorGroup(GnbVendorGroupSaveRequest request, LocalDateTime now, String username) {
		GnbVendorGroupEntity gnbVendorGroupEntity = new GnbVendorGroupEntity();
		gnbVendorGroupEntity.setVendorGroupTitle(request.getTitle());
		gnbVendorGroupEntity.setAlphabeticalOrder(request.isAlphabeticalOrder());
		gnbVendorGroupEntity.setCreatedBy(username);
		gnbVendorGroupEntity.setCreatedOn(now);
		gnbVendorGroupEntity.setModifiedBy(username);
		gnbVendorGroupEntity.setModifiedOn(now);
		return gnbVendorGroupRepository.save(gnbVendorGroupEntity);
	}

	private List<GnbVendorGroupMapEntity> createGnbVendorGroupMap(int gnbVendorGroupId, List<Integer> vendorIdList, LocalDateTime now, String username) {
		List<GnbVendorGroupMapEntity> gnbVendorGroupMapEntityList = new ArrayList<>(vendorIdList.size());

		for (int i = 0; i < vendorIdList.size(); i++) {
			GnbVendorGroupMapId id = new GnbVendorGroupMapId();
			id.setVendorGroupId(gnbVendorGroupId);
			id.setVendorId(vendorIdList.get(i));

			GnbVendorGroupMapEntity gnbVendorGroupMapEntity = new GnbVendorGroupMapEntity();
			gnbVendorGroupMapEntity.setMapId(id);
			gnbVendorGroupMapEntity.setSortNo(i);
			gnbVendorGroupMapEntity.setCreatedBy(username);
			gnbVendorGroupMapEntity.setCreatedOn(now);
			gnbVendorGroupMapEntity.setModifiedBy(username);
			gnbVendorGroupMapEntity.setModifiedOn(now);
			gnbVendorGroupMapEntityList.add(gnbVendorGroupMapEntity);
		}

		return gnbVendorGroupMapRepository.saveAll(gnbVendorGroupMapEntityList);
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public void deleteGnbVendorGroupBatch(List<Integer> gnbVendorGroupIdList) {
		List<GnbVendorGroupEntity> gnbVendorGroupEntityList = gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(gnbVendorGroupIdList);
		List<GnbVendorGroupMapEntity> gnbVendorGroupMapEntityList = gnbVendorGroupEntityList.stream()
				.map(GnbVendorGroupEntity::getVendorGroupMaps)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		gnbVendorGroupRepository.deleteInBatch(gnbVendorGroupEntityList);
		gnbVendorGroupMapRepository.deleteInBatch(gnbVendorGroupMapEntityList);
	}

	@Override
	public GnbVendorGroupDetailResponse getGnbVendorGroup(int gnbVendorGroupId) {
		return gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(Collections.singletonList(gnbVendorGroupId))
				.stream()
				.findFirst()
				.map(GnbVendorGroupDetailResponse::of)
				.orElseThrow(NotFoundGnbVendorGroup::new);
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public GnbVendorGroupInfoResponse editGnbVendorGroup(int gnbVendorGroupId, GnbVendorGroupSaveRequest request) {
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();

		Integer activeGnbVendorGroupId = siteSettingRepository.findById(1)
				.orElseThrow(NotFoundSiteSetting::new)
				.getGnbVendorGroupId();

		GnbVendorGroupEntity gnbVendorGroupEntity = updateGnbVendorGroup(gnbVendorGroupId, request, now, username);
		gnbVendorGroupEntity.setVendorGroupMaps(updateGnbVendorGroupMap(gnbVendorGroupEntity, request.getWholeSalerIdList(), now, username));

		return GnbVendorGroupInfoResponse.of(gnbVendorGroupEntity, gnbVendorGroupEntity.getVendorGroupId().equals(activeGnbVendorGroupId));
	}

	private GnbVendorGroupEntity updateGnbVendorGroup(int gnbVendorGroupId, GnbVendorGroupSaveRequest request, LocalDateTime now, String username) {
		boolean isUpdated = false;

		GnbVendorGroupEntity gnbVendorGroupEntity = gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(Collections.singletonList(gnbVendorGroupId))
				.stream()
				.findFirst()
				.orElseThrow(NotFoundGnbVendorGroup::new);

		if (!request.getTitle().equals(gnbVendorGroupEntity.getVendorGroupTitle())) {
			isUpdated = true;
			gnbVendorGroupEntity.setVendorGroupTitle(request.getTitle());
		}

		if (request.isAlphabeticalOrder() != gnbVendorGroupEntity.isAlphabeticalOrder()) {
			isUpdated = true;
			gnbVendorGroupEntity.setAlphabeticalOrder(request.isAlphabeticalOrder());
		}

		if (isUpdated) {
			gnbVendorGroupEntity.setModifiedBy(username);
			gnbVendorGroupEntity.setModifiedOn(now);
		}

		return gnbVendorGroupRepository.save(gnbVendorGroupEntity);
	}

	private List<GnbVendorGroupMapEntity> updateGnbVendorGroupMap(GnbVendorGroupEntity gnbVendorGroupEntity, List<Integer> vendorIdList, LocalDateTime now, String username) {
		List<GnbVendorGroupMapEntity> oldMapList = gnbVendorGroupEntity.getVendorGroupMaps();
		List<GnbVendorGroupMapEntity> newMapList = new ArrayList<>(vendorIdList.size());

		for (int i = 0; i < vendorIdList.size(); i++) {
			int wholeSalerId = vendorIdList.get(i);
			GnbVendorGroupMapEntity newMap = null;

			Iterator<GnbVendorGroupMapEntity> iterator = oldMapList.iterator();
			while (iterator.hasNext()) {
				GnbVendorGroupMapEntity oldMap = iterator.next();
				if (wholeSalerId == oldMap.getId().getVendorId()) {
					iterator.remove();
					newMap = oldMap;
				}
			}

			if (newMap != null) {
				if (newMap.getSortNo() != i) {
					newMap.setModifiedBy(username);
					newMap.setModifiedOn(now);
					newMap.setSortNo(i);
				}
			} else {
				GnbVendorGroupMapId id = new GnbVendorGroupMapId();
				id.setVendorGroupId(gnbVendorGroupEntity.getVendorGroupId());
				id.setVendorId(wholeSalerId);

				newMap = new GnbVendorGroupMapEntity();
				newMap.setMapId(id);
				newMap.setSortNo(i);
				newMap.setCreatedBy(username);
				newMap.setCreatedOn(now);
				newMap.setModifiedBy(username);
				newMap.setModifiedOn(now);
			}

			newMapList.add(newMap);
		}

		gnbVendorGroupMapRepository.deleteInBatch(oldMapList);
		return gnbVendorGroupMapRepository.saveAll(newMapList);
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public void deleteGnbVendorGroup(int gnbVendorGroupId) {
		GnbVendorGroupEntity gnbVendorGroupEntity = gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(Collections.singletonList(gnbVendorGroupId))
				.stream()
				.findFirst()
				.orElseThrow(NotFoundGnbVendorGroup::new);

		List<GnbVendorGroupMapEntity> gnbVendorGroupMapEntityList = gnbVendorGroupEntity.getVendorGroupMaps();

		gnbVendorGroupRepository.delete(gnbVendorGroupEntity);
		gnbVendorGroupMapRepository.deleteInBatch(gnbVendorGroupMapEntityList);
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public void activateGnbVendorGroup(int gnbVendorGroupId) {
		SiteSettingEntity siteSettingEntity = siteSettingRepository.findById(1)
				.orElseThrow(NotFoundSiteSetting::new);

		siteSettingEntity.setGnbVendorGroupId(gnbVendorGroupId);
		siteSettingRepository.save(siteSettingEntity);
	}
}
