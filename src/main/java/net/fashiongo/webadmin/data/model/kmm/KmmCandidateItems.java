package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KmmCandidateItems {
    @JsonProperty(value = "productId")
    private Integer productID;

    @JsonProperty(value = "productName")
    private String productName;

    @JsonProperty(value = "imageUrl")
    private String imageUrlRoot;

    @JsonProperty(value = "companyName")
    private String companyName;
}
