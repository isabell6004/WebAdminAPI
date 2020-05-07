package net.fashiongo.webadmin.data.entity.primary;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vendor_seo")
public class VendorSeoEntity implements Serializable {

	private static final long serialVersionUID = 5974282371889691302L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vendor_Seo_Id")
    private Integer vendorSeoId;
	
    @Column(name = "Vendor_Id")
    private Integer vendorId;
    
    @Column(name = "Meta_Keyword")
    private String metaKeyword;
    
    @Column(name = "Meta_Description")
    private String metaDescription;    
	
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
