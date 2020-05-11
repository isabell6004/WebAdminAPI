package net.fashiongo.webadmin.data.model.vendor;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetVendorSeoParameter {
	
    //@JsonProperty(value = "Vendor_seo_Id")
    private Long vendorseoId;
    
    //@JsonProperty(value = "Meta_Keyword")
    private String metaKeyword;

    //@JsonProperty(value = "Meta_Description")
    private String metaDescription;   
    
    public boolean isNewVendorSeo() {
        return (this.getVendorseoId() == null || this.getVendorseoId() == 0);
    }    
}
