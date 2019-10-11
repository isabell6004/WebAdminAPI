package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.GnbVendorGroupMapRepository;
import net.fashiongo.webadmin.dao.primary.GnbVendorGroupRepository;
import net.fashiongo.webadmin.dao.primary.SiteSettingRepository;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupEntity;
import net.fashiongo.webadmin.data.entity.primary.GnbVendorGroupMapEntity;
import net.fashiongo.webadmin.data.entity.primary.SiteSettingEntity;
import net.fashiongo.webadmin.model.pojo.response.GnbVendorGroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
	public List<GnbVendorGroupInfo> getGnbVendorGroupList(Integer wholeSalerId, String title) {
		Integer activeGnbVendorGroupId = siteSettingRepository.findById(1)
				.orElseThrow(RuntimeException::new)
				.getGnbVendorGroupId();

		List<Integer> gnbVendorGroupIdList = gnbVendorGroupRepository.findIdListByWholeSalerIdAndTitle(wholeSalerId, title);

		return gnbVendorGroupRepository.findAllByIdListWithGnbVendorGroupMap(gnbVendorGroupIdList)
				.stream()
				.map(gnbVendorGroupEntity -> GnbVendorGroupInfo.of(gnbVendorGroupEntity, gnbVendorGroupEntity.getVendorGroupId().equals(activeGnbVendorGroupId)))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public void activateGnbVendorGroup(int gnbVendorGroupId) {
		SiteSettingEntity siteSettingEntity = siteSettingRepository.findById(1)
				.orElseThrow(RuntimeException::new);

		siteSettingEntity.setGnbVendorGroupId(gnbVendorGroupId);
		siteSettingRepository.save(siteSettingEntity);
	}

	@Override
	@Transactional(transactionManager = "primaryTransactionManager")
	public void deleteGnbVendorGroup(int gnbVendorGroupId) {
		deleteGnbVendorGroupBatch(Collections.singletonList(gnbVendorGroupId));
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
}
