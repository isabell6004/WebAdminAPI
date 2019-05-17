package net.fashiongo.webadmin.model.pojo.sitemgmt;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import net.fashiongo.webadmin.model.primary.Vendor;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-05-09
 */
@Data
@Builder
public class EditorsPick {
	private Integer id;
	private String title;
	private String description;
	
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	private LocalDateTime createdOn;
	private String createdBy;
	private LocalDateTime modifiedOn;
	private String modifiedBy;
	
	private Boolean status;
	private String statusDescription;
	
	private Integer vendorId;
	private Vendor vendor;
	
	private Integer bannerOrMediaId;
	private Integer bannerOrMediaTypeId; //1=Banner, 2=Media
	private BannerOrMedia bannerOrMedia;
}
