package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.PolicyAgreementEntity;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.sitemgmt.PolicyAgreement;
import net.fashiongo.webadmin.data.model.sitemgmt.response.GetPolicyDetailResponse;
import net.fashiongo.webadmin.data.repository.primary.PolicyAgreementEntityRepository;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetPolicyDetailParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;


@Service
public class RenewalSitemgmtService {

	private final PolicyAgreementEntityRepository policyAgreementEntityRepository;

	@Autowired
	public RenewalSitemgmtService(PolicyAgreementEntityRepository policyAgreementEntityRepository) {
		this.policyAgreementEntityRepository = policyAgreementEntityRepository;
	}

	public GetPolicyDetailResponse getPolicyDetail (GetPolicyDetailParameter parameters) {
		Page<PolicyAgreement> detailPolicyAgreement = policyAgreementEntityRepository.findDetailPolicyAgreement(parameters.getPolicyID(), parameters.getSearchItem(), parameters.getSearchTxt(), parameters.getPageNum(), parameters.getPageSize());
		int totalCnt = Long.valueOf(detailPolicyAgreement.getTotalElements()).intValue();
		return GetPolicyDetailResponse.builder()
				.total(Arrays.asList(new Total(totalCnt)))
				.policyDetail(
						detailPolicyAgreement.getContent()
//						policyAgreementEntities.stream()
//							.map(policyAgreementEntity -> PolicyAgreement.builder()
//									.policyAgreementID(policyAgreementEntity.getPolicyAgreementID())
//									.policyID(policyAgreementEntity.getPolicyID())
//									.wholeSalerID(policyAgreementEntity.getWholeSalerID())
//									.companyName(policyAgreementEntity.getCompanyName())
//									.retailerID(policyAgreementEntity.getRetailerID())
//									.agreedOn(policyAgreementEntity.getAgreedOn())
//									.agreedByName(policyAgreementEntity.getAgreedByName())
//									.agreedByID(policyAgreementEntity.getAgreedByID())
//									.iPAddress(policyAgreementEntity.getIpAddress())
//									.agreed(policyAgreementEntity.isAgreed())
//								.build()
//							).collect(Collectors.toList())
				)
				.build();
	}
}
