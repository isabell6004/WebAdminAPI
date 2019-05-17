package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-09
 */
@Data
@Builder
public class BannerOrMedia {
	private Integer typeId; //1=Banner, 2=Media
	private Integer id;
	private String title;
	@Singular private List<BannerOrMediaFile> files;
}
