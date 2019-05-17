package net.fashiongo.webadmin.model.pojo.sitemgmt;

import lombok.Builder;
import lombok.Data;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-09
 */
@Data
@Builder
public class BannerOrMediaFile {
	private Integer id;
	private Integer fileType;
	private String fileName;
}
