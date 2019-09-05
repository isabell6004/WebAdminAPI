package net.fashiongo.webadmin.data.entity.primary.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class VendorProductRow {
	@JsonProperty("ProductID")
	private Integer productID;

	@JsonProperty("ProductName")
	private String productName;

	@JsonProperty("UnitPrice")
	private BigDecimal unitPrice;

	@JsonProperty("DirName")
	private String dirName;

	@JsonProperty("UrlPath")
	private String urlPath;

	@JsonProperty("ProductImageID")
	private Integer productImageID;

	@JsonProperty("ImageName")
	private String imageName;
}
