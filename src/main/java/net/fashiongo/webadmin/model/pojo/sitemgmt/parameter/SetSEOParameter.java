package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter

public class SetSEOParameter {

	@JsonProperty("SiteSeoId")
	private Integer siteSeoId;
	
	@JsonProperty(value = "PageName")
	private String pageName;	
	
	@JsonProperty(value = "Url")
	private String url;	
	
	@JsonProperty(value = "Title")
	private String title;	
	
	@JsonProperty(value = "MetaKeyword")
	private String metaKeyword;	
	
	@JsonProperty(value = "MetaDescription")
	private String metaDescription;


}
