package net.fashiongo.webadmin.data.entity.primary;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "site_seo")
public class SEOEntity implements Serializable{
	private static final long serialVersionUID = 7996779589067775778L;
	
	@Id
	@Column(name = "site_seo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer siteSeoId;
	
	@Column(name = "page_name")
	private String pageName;	
	
	@Column(name = "url")
	private String url;	
	
	@Column(name = "title")
	private String title;	
	
	@Column(name = "meta_keyword")
	private String metaKeyword;	
	
	@Column(name = "meta_description")
	private String metaDescription;	
	
	@Column(name = "is_active")
	private boolean isActive;	

	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "created_on")
	private Timestamp createdOn;	
	
	@Column(name = "created_by")
	private String createdBy;		
	
	@Column(name = "modified_on")
	private Timestamp modifiedOn;	
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
}
