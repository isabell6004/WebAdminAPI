package net.fashiongo.webadmin.service.renewal;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.FranchiseMasterAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.MapFranchiseSubAccountEntity;
import net.fashiongo.webadmin.data.entity.primary.RetailerEntity;
import net.fashiongo.webadmin.data.model.franchise.AutoCompleteParameter;
import net.fashiongo.webadmin.data.model.franchise.FranchiseBuyer;
import net.fashiongo.webadmin.data.model.franchise.FranchiseSubAccount;
import net.fashiongo.webadmin.data.model.franchise.GetFranchiseMasterSubAccountParameter;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseMasterResponse;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseMasterSubAccountsResponse;
import net.fashiongo.webadmin.data.model.franchise.response.FranchiseSubResponse;
import net.fashiongo.webadmin.data.repository.primary.FranchiseMasterAccountEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.FranchiseRetailerEntityRepository;
import net.fashiongo.webadmin.data.repository.primary.MapFranchiseSubAccountEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
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

	@Transactional(transactionManager = "primaryTransactionManager")
	public boolean addSub(int retailerId,int masterAccountId) {

		long count = mapFranchiseSubAccountEntityRepository.countByRetailerId(retailerId);

		if(count == 0) {
			MapFranchiseSubAccountEntity subAccountEntity = new MapFranchiseSubAccountEntity();
			subAccountEntity.setRetailerId(retailerId);
			subAccountEntity.setFranchiseMasterAccountId(masterAccountId);

			mapFranchiseSubAccountEntityRepository.save(subAccountEntity);

			return true;
		}
		return false;
	}

	@Transactional(transactionManager = "primaryTransactionManager")
	public boolean removeSub(List<Integer> retailerIds, Integer masterAccountId) {

		try {
			for (Integer retailerId : retailerIds) {
				mapFranchiseSubAccountEntityRepository.deleteByFranchiseMasterAccountIdAndRetailerId(masterAccountId,retailerId);
			}

			return true;
		} catch (Exception e) {
			log.warn(e.getMessage(),e);
			return false;
		}
	}

	public FranchiseMasterSubAccountsResponse getFranchiseMasterSubAccounts(Integer retailerId, GetFranchiseMasterSubAccountParameter parameter) {

		String searchTxt = Optional.ofNullable(parameter.getSearchTxt()).filter(s -> StringUtils.isEmpty(s) == false).orElse(null);
		String state = Optional.ofNullable(parameter.getState()).filter(s -> StringUtils.isEmpty(s) == false).orElse(null);

		Integer masterAccountId = Optional.ofNullable(parameter.getMasterAccountId()).orElse(0);

		Integer searchTypeId = Optional.ofNullable(parameter.getSearchTypeId()).filter(s -> !StringUtils.isEmpty(s)).map(s -> Integer.valueOf(s)).orElse(null);
		Integer countryCode = Optional.ofNullable(parameter.getCountryCode()).filter(s -> !Objects.equals("ALL",s.toUpperCase())).map(s -> Integer.valueOf(s)).orElse(null);

		Integer pageNumber = Optional.ofNullable(parameter.getPn()).orElse(1);
		Integer pageSize = Optional.ofNullable(parameter.getPs()).orElse(10);
		String orderBy = Optional.ofNullable(parameter.getOrderBy()).filter(s -> StringUtils.isEmpty(s) == false).orElse(null);

		LocalDateTime fromDate = Optional.ofNullable(parameter.getFromDate())
				.filter(s -> StringUtils.isEmpty(s) == false)
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(null);

		LocalDateTime toDate = Optional.ofNullable(parameter.getToDate())
				.filter(s -> StringUtils.isEmpty(s) == false)
				.map(s -> new Date(s).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
				.orElse(null);

		Page<FranchiseSubAccount> subAccountPage = franchiseMasterAccountEntityRepository.findAllSubAccount(retailerId, pageNumber, pageSize, masterAccountId, searchTypeId, searchTxt, countryCode, state, fromDate, toDate, orderBy);

		return FranchiseMasterSubAccountsResponse.builder()
				.totalCount(subAccountPage.getTotalElements())
				.subAccounts(subAccountPage.getContent())
				.build();
	}

}
