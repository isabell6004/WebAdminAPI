package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.RetailerEntity;
import net.fashiongo.webadmin.data.entity.primary.franchise.FranchiseRetailerEntity;
import net.fashiongo.webadmin.data.model.franchise.FranchiseBuyer;

import java.util.List;

public interface FranchiseRetailerEntityRepositoryCustom {

	List<FranchiseBuyer> findAllByContainsCompanyName(String companyName);
}
