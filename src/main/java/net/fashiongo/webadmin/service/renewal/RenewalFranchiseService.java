package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.RetailerEntity;
import net.fashiongo.webadmin.data.model.franchise.AutoCompleteParameter;
import net.fashiongo.webadmin.data.model.franchise.FranchiseBuyer;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseMasterResponse;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseSubResponse;
import net.fashiongo.webadmin.data.repository.primary.FranchiseMasterAccountEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.FranchiseRetailerEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.MapFranchiseSubAccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class RenewalFranchiseService {

	@Autowired
	private FranchiseMasterAccountEntityRepository franchiseMasterAccountEntityRepository;

	@Autowired
	private MapFranchiseSubAccountEntityRepository mapFranchiseSubAccountEntityRepository;

	@Autowired
	private FranchiseRetailerEntityRepository franchiseRetailerEntityRepository;

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

	public FranchiseSubResponse getFranchiseSub(Integer retailerId) {

		return mapFranchiseSubAccountEntityRepository.findAllByRetailerId(retailerId).stream().findFirst()
				.map(mapFranchiseSubAccountEntity -> {
					FranchiseMasterAccountEntity franchiseMasterAccount = mapFranchiseSubAccountEntity.getFranchiseMasterAccount();
					RetailerEntity retailer = franchiseMasterAccount.getRetailer();
					return FranchiseSubResponse.builder()
							.franchiseMasterAccountId(franchiseMasterAccount.getFranchiseMasterAccountId())
							.franchiseSubAccountId(mapFranchiseSubAccountEntity.getFranchiseSubAccountId())
							.companyName(retailer.getCompanyName())
							.firstName(retailer.getFirstName())
							.lastName(retailer.getLastName())
							.retailerId(franchiseMasterAccount.getRetailerId())
							.build();
				}).orElse(null);
	}

	public List<FranchiseBuyer> autoComplete(AutoCompleteParameter parameter) {
		String searchText = parameter.getSearchText();
		if(StringUtils.isEmpty(searchText)) {
			return Collections.EMPTY_LIST;
		}

		return franchiseRetailerEntityRepository.findAllByContainsCompanyName(searchText, 100);
	}
}
