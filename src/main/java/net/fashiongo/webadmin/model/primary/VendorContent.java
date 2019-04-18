package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.dal.IPersistent;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-15
 */
@Entity
@Table(name = "Vendor_Content")
@Getter
@Setter
@Where(clause="Is_Deleted=0")
public class VendorContent implements IPersistent, Serializable {
	private static final long serialVersionUID = -213129874484795682L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VendorContentID")
	private Integer vendorContentId;
	
	@Column(name = "WholeSalerID")
	private Integer wholeSalerId;
	
	@Column(name = "Target_TypeID")
	private Integer targetTypeId;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "StatusID")
	private Integer statusId;
	
	@Column(name = "RequestedOn")
	private LocalDateTime requestedOn;
	
	@Column(name = "RequestedBy")
	private String requestedBy;
	
	@Column(name = "ApprovedOn")
	private LocalDateTime approvedOn;
	
	@Column(name = "ApprovedBy")
	private String approvedBy;
	
	@Column(name = "RejectedOn")
	private LocalDateTime rejectedOn;
	
	@Column(name = "RejectedBy")
	private String rejectedBy;
	
	@Column(name = "RejectedReason")
	private String rejectedReason;
	
	@Column(name = "DeletedOn")
	private LocalDateTime deletedOn;
	
	@Column(name = "DeletedBy")
	private String deletedBy;
	
	@Column(name = "Is_Deleted")
	private Boolean isDeleted;
	
	@Column(name = "Is_Active")
	private Boolean isActive;
	
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
    @JoinColumn(name = "WholeSalerID", insertable = false, updatable = false)
    @ManyToOne
    private Vendor vendor;
	
    @JoinColumn(name = "VendorContentID")
    @OneToMany
    private List<VendorContentFile> vendorContentFiles = new ArrayList<>();
	
    public String getBaseImageUrl() {
		return new StringBuilder("https://s3-us-west-2.amazonaws.com/fg-business-data/vendor/")
				.append(this.wholeSalerId + "/premium/")
				.append(this.vendorContentId + "/")
				.toString();
	}
	
	public String getBaseVideoUrl() {
		return new StringBuilder("https://vimeo.com/").toString();
	}
}
