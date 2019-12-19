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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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

	private final CacheService cacheService;

	private final PlatformTransactionManager transactionManager;

	@Autowired
	public GnbServiceImpl(GnbVendorGroupRepository gnbVendorGroupRepository,
						  GnbVendorGroupMapRepository gnbVendorGroupMapRepository,
						  SiteSettingRepository siteSettingRepository,
						  CacheService cacheService,
						  @Qualifier("primaryTransactionManager") PlatformTransactionManager transactionManager) {
		this.gnbVendorGroupRepository = gnbVendorGroupRepository;
		this.gnbVendorGroupMapRepository = gnbVendorGroupMapRepository;
		this.siteSettingRepository = siteSettingRepository;
		this.cacheService = cacheService;
		this.transactionManager = transactionManager;
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
		gnbVendorGroupEntity.setTargetUrl(request.getTargetUrl());
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
	public GnbVendorGroupInfoResponse editGnbVendorGroup(int gnbVendorGroupId, GnbVendorGroupSaveRequest request) {
		LocalDateTime now = LocalDateTime.now();
		String username = Utility.getUsername();

		GnbVendorGroupEntity gnbVendorGroupEntity = doInTransaction(status -> {
			GnbVendorGroupEntity vendorGroupEntity = updateGnbVendorGroup(gnbVendorGroupId, request, now, username);
			List<GnbVendorGroupMapEntity> vendorGroupMapEntityList = updateGnbVendorGroupMap(vendorGroupEntity, request.getWholeSalerIdList(), now, username);
			vendorGroupEntity = gnbVendorGroupRepository.save(vendorGroupEntity);
			vendorGroupEntity.setVendorGroupMaps(vendorGroupMapEntityList);

			return vendorGroupEntity;
		});

		boolean isActive = gnbVendorGroupEntity.getVendorGroupId().equals(siteSettingRepository.findById(1)
				.orElseThrow(NotFoundSiteSetting::new)
				.getGnbVendorGroupId());

		clearCache(isActive);

		return GnbVendorGroupInfoResponse.of(gnbVendorGroupEntity, isActive);
	}

	private GnbVendorGroupEntity updateGnbVendorGroup(int gnbVendorGroupId, GnbVendorGroupSaveRequest request, LocalDateTime now, String username) {
		GnbVendorGroupEntity gnbVendorGroupEntity = gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(Collections.singletonList(gnbVendorGroupId))
				.stream()
				.findFirst()
				.orElseThrow(NotFoundGnbVendorGroup::new);

		gnbVendorGroupEntity.setVendorGroupTitle(request.getTitle());
		gnbVendorGroupEntity.setTargetUrl(request.getTargetUrl());
		gnbVendorGroupEntity.setAlphabeticalOrder(request.isAlphabeticalOrder());

		return gnbVendorGroupEntity;
	}

	private List<GnbVendorGroupMapEntity> updateGnbVendorGroupMap(GnbVendorGroupEntity gnbVendorGroupEntity, List<Integer> vendorIdList, LocalDateTime now, String username) {
		List<GnbVendorGroupMapEntity> oldMapList = gnbVendorGroupEntity.getVendorGroupMaps();
		List<GnbVendorGroupMapEntity> newMapList = new ArrayList<>(vendorIdList.size());

		int oldMapListSize = oldMapList.size();
		boolean isOrderChanged = false;
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

					isOrderChanged = true;
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

		if (isOrderChanged
				|| !oldMapList.isEmpty() // There is removed vendor.
				|| oldMapListSize != newMapList.size()) { // there is created vendor.
			gnbVendorGroupEntity.setModifiedOn(now);
			gnbVendorGroupEntity.setModifiedBy(username);
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
	public void activateGnbVendorGroup(int gnbVendorGroupId) {
		doInTransaction(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				SiteSettingEntity siteSettingEntity = siteSettingRepository.findById(1)
						.orElseThrow(NotFoundSiteSetting::new);

				siteSettingEntity.setGnbVendorGroupId(gnbVendorGroupId);
				siteSettingRepository.save(siteSettingEntity);
			}
		});

		clearCache(true);
	}

	private <T> T doInTransaction(TransactionCallback<T> action) {
		TransactionTemplate tx = new TransactionTemplate(transactionManager);
		tx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return tx.execute(action);
	}

	private void clearCache(boolean isActive) {
		if (isActive) {
			cacheService.GetRedisCacheEvict("GnbMenuVendorGroup", null);
		}
	}
}
