package net.fashiongo.webadmin.dao.primary.custom;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import net.fashiongo.webadmin.model.primary.Vendor;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-02
 */
@NoRepositoryBean
public interface VendorRepositoryCustom {
	List<Vendor> getEditorPickVendors();
	String getCompanyNameByWholeSalerId(Integer wholeSalerId);
}
