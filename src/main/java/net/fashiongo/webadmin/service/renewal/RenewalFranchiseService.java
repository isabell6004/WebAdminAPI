package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseMasterResponse;
import net.fashiongo.webadmin.data.repository.primary.FranchiseMasterAccountEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.MapFranchiseSubAccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenewalFranchiseService {

	@Autowired
	private FranchiseMasterAccountEntityRepository franchiseMasterAccountEntityRepository;

	@Autowired
	private MapFranchiseSubAccountEntityRepository mapFranchiseSubAccountEntityRepository;

	public FranchiseMasterResponse getFranchiseMaster(Integer retailerId) {

		FranchiseMasterAccountEntity franchiseMasterAccountEntity = franchiseMasterAccountEntityRepository.findAllByRetailerId(retailerId)
				.stream()
				.findFirst().orElse(null);

		if(franchiseMasterAccountEntity == null) {
			return null;
		}

		long countSubAccount = mapFranchiseSubAccountEntityRepository.countByFranchiseMasterAccountId(franchiseMasterAccountEntity.getFranchiseMasterAccountId());

		return FranchiseMasterResponse.builder()
				.franchiseMasterAccountId(franchiseMasterAccountEntity.getFranchiseMasterAccountId())
				.retailerId(franchiseMasterAccountEntity.getRetailerId())
				.createdBy(franchiseMasterAccountEntity.getCreatedBy())
				.createdOn(franchiseMasterAccountEntity.getCreatedOn())
				.totalSubAccounts(countSubAccount)
				.build();
	}
}
