package net.fashiongo.webadmin.dao.primary.custom;

import java.time.LocalDateTime;

import org.springframework.data.repository.NoRepositoryBean;

import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.primary.EditorPickVendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-01
 */
@NoRepositoryBean
public interface EditorPickVendorContentRepositoryCustom {
	PagedResult<EditorPickVendorContent> getEditorPickVendorContents(Integer pagenum, Integer pagesize, String title,
			String vendorName, LocalDateTime startDate, LocalDateTime endDate, String orderBy);
}
