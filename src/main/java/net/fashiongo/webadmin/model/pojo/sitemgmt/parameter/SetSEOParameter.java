package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

import net.fashiongo.webadmin.data.model.sitemgmt.SEO;
@Getter

public class SetSEOParameter {

	//@JsonProperty(value = "obj")
	//private SEO seo;
	
	@JsonProperty("SiteSEOId")
	private Integer siteSEOId;
	
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
