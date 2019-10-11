package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;

import java.util.List;

public interface FranchiseMasterAccountEntityRepositoryCustom {

	List<FranchiseMasterAccountEntity> findAllByRetailerId(int retailerId);
}
