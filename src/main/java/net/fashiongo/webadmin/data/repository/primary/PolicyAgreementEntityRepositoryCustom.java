package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.PolicyAgreementEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.PolicyAgreement;
import org.springframework.data.domain.Page;

public interface PolicyAgreementEntityRepositoryCustom {

	Page<PolicyAgreementEntity> findDetail(int policyId, String searchColumn, String searchText, int pageNumber, int pageSize);

	Page<PolicyAgreement> findDetailPolicyAgreement(int policyId, String searchColumn, String searchText, int pageNumber, int pageSize);


}
