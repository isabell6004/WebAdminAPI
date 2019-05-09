package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

/**
 * @author Kenny/Kyungwoo
 * @since 2019-04-29
 */
@Data
@Entity
@Table(name = "editor_pick_vendor_content")
public class EditorPickVendorContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "editor_pick_vendor_content_id")
	private Integer editorPickVendorContentId;
	
	@Column(name = "editor_title")
	private String editorTitle;
	
	@Column(name = "editor_description")
	private String editorDescription;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "vendor_id")
	private Integer vendorId;
	
	@Column(name = "vendor_content_id")
	private Integer vendorContentId;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_on")
	private LocalDateTime modifiedOn;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "ImageRequestID")
	private Integer imageRequestID;
	
    @JoinColumn(name = "vendor_id", insertable = false, updatable = false, nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Vendor vendor;
	
    @JoinColumn(name = "vendor_content_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private VendorContent vendorContent;
    
    @JoinColumn(name = "ImageRequestID", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private VendorImageRequest vendorImageRequest;
    
    public boolean getStatus() {
    	LocalDateTime now = LocalDateTime.now();
    	if(startDate!=null && startDate.isAfter(now)) return false;
    	else if(endDate!=null && endDate.isBefore(now)) return false;
    	else if(!vendor.getActive()) return false;
    	else if(!vendor.getShopActive()) return false;
    	else if(!vendor.getOrderActive()) return false;
    	else if(vendor.getVendorType()!=2) return false;
    	else if(vendorContent==null) return false;
    	else if(vendorContent.getStatusId()!=2) return false;
    	else if(!vendorContent.getIsActive()) return false;
    	else if(vendorContent.getIsDeleted()) return false;
    	else return true;
    }
    
    public String getStatusDescription() {
    	ArrayList<String> descs = new ArrayList<>();
    	
    	LocalDateTime now = LocalDateTime.now();
    	if(startDate!=null && startDate.isAfter(now)) descs.add("period is not started");
    	if(endDate!=null && endDate.isBefore(now)) descs.add("period is ended");
    	if(!vendor.getActive()) descs.add("vendor is not active");
    	if(!vendor.getShopActive()) descs.add("vendor is not shopActive");
    	if(!vendor.getOrderActive()) descs.add("vendor is not orderActive");
    	if(vendor.getVendorType()!=2) descs.add("vendor is not Premium");
    	if(vendorContent==null) descs.add("media does not exist");
    	else {
    		if(vendorContent.getStatusId()!=2) descs.add("media is not approved");
    		if(!vendorContent.getIsActive()) descs.add("media is not active");
    		if(vendorContent.getIsDeleted()) descs.add("media is deleted");
    	}
    	
    	return String.join("<br/>", descs);
    }
}
