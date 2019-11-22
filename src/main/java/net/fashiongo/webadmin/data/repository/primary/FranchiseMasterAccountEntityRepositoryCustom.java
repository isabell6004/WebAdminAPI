package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.model.franchise.FranchiseSubAccount;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface FranchiseMasterAccountEntityRepositoryCustom {

	List<FranchiseMasterAccountEntity> findAllByRetailerId(int retailerId);

	Page<FranchiseSubAccount> findAllSubAccount(Integer retailerId,Integer pageNumber, Integer pageSize,  Integer masterAccountId, Integer searchTypeId, String searchTxt, Integer countryCode, String state, LocalDateTime fromDate, LocalDateTime toDate, String orderBy);
}
