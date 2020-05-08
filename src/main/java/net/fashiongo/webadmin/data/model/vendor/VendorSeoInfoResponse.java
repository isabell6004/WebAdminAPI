package net.fashiongo.webadmin.data.model.vendor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorSeoInfoResponse {

	@JsonProperty(value = "Vendor_Seo_Id")
	private Integer vendorSeoId;
	
	@JsonProperty(value = "Vendor_Id")
	private Integer vendorId;
	
	@JsonProperty(value = "Meta_Keyword")
    private String metaKeyword ;
	
    @JsonProperty(value = "Meta_Description")
    private String metaDescription ;  	

    @JsonProperty(value = "CreatedOn")
    private LocalDateTime createdOn;
    
    @JsonProperty(value = "CreatedBy")
    private String createdBy;
    
    @JsonProperty(value = "ModifiedOn")
    private LocalDateTime modifiedOn;
    
    @JsonProperty(value = "ModifiedBy")
    private String modifiedBy;    

    public VendorSeoInfoResponse (Integer vendorSeoId,Integer vendorId, String metaKeyword, String metaDescription, LocalDateTime createdOn, String createdBy, LocalDateTime modifiedOn, String modifiedBy) {
        this.vendorSeoId = vendorSeoId;
        this.vendorId = vendorId;
    	this.metaKeyword = metaKeyword;
        this.metaDescription = metaDescription;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;        
    }    
}
