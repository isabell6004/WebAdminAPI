package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@AllArgsConstructor 
public class SEO {
	
	@JsonProperty("SiteSeoId")
	private Integer siteSeoId;

	@JsonProperty("PageName")
	private String pageName;

	@JsonProperty("Url")
	private String url;

	@JsonProperty("Title")
	private String title;
	
	@JsonProperty("MetaKeyword")
	private String metaKeyword;

	@JsonProperty("MetaDescription")
	private String metaDescription;
	
	@JsonProperty("IsActive")
	private Boolean isActive;

	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;

	@JsonProperty("CreatedBy")
	private String createdBy;

	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;

	@JsonProperty("ModifiedBy")
	private String modifiedBy;
	
	@JsonProperty("row")
	private Integer row;


	public SEO(Integer siteSeoId, String pageName, String url, String title, String metaKeyword, String metaDescription, Boolean isActive, Timestamp createdOn, String createdBy,Timestamp modifiedOn, String modifiedBy, Long row) {
		this.siteSeoId = siteSeoId;
		this.pageName = pageName;
		this.url = url;
		this.title = title;
		this.metaKeyword = metaKeyword;
		this.metaDescription = metaDescription;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.modifiedBy = modifiedBy;
		this.modifiedOn = Optional.ofNullable(modifiedOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.row = Optional.ofNullable(row).map(Long::intValue).orElse(null);
	}
}	

