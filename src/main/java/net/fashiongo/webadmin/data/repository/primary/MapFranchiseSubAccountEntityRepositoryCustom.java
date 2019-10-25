package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.MapFranchiseSubAccountEntity;

import java.util.List;

public interface MapFranchiseSubAccountEntityRepositoryCustom {

	long countByFranchiseMasterAccountId(Integer franchiseMasterAccountId);

	List<MapFranchiseSubAccountEntity> findAllByRetailerId(int retailerId);

	long countByRetailerId(int retailerId);

	long deleteByFranchiseMasterAccountIdAndRetailerId(int franchiseMasterAccountId, int retailerId);
}
