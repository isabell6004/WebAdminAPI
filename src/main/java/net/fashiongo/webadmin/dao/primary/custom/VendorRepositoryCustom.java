package net.fashiongo.webadmin.dao.primary.custom;

import java.util.List;

import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-02
 */
@NoRepositoryBean
public interface VendorRepositoryCustom {
	List<VendorEntity> getEditorPickVendors();
	String getCompanyNameByWholeSalerId(Integer wholeSalerId);
}
