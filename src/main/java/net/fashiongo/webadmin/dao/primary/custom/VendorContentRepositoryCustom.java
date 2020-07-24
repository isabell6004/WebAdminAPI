package net.fashiongo.webadmin.dao.primary.custom;

import java.time.LocalDateTime;

import org.springframework.data.repository.NoRepositoryBean;

import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.primary.VendorContent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-02
 */
@NoRepositoryBean
public interface VendorContentRepositoryCustom {
	PagedResult<VendorContent> getVendorContents(Integer pagenum, Integer pagesize, String company,Integer contentfileid,
												 LocalDateTime datefrom, LocalDateTime dateto, Integer type, Integer filetype, Integer status);
}