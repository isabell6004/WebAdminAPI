package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DMRequestDetail {

	@JsonProperty("CatalogID")
	private Integer catalogID;
	@JsonProperty("ImageUrlRoot")
	private String imageUrlRoot;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("PictureLogo")
	private String pictureLogo;
	@JsonProperty("ProductID")
	private Integer productID;
	@JsonProperty("PictureGeneral")
	private String pictureGeneral;
	@JsonProperty("ProductName")
	private String productName;
	@JsonProperty("CompanyName")
	private String companyName;

	public DMRequestDetail(Integer catalogID, String imageUrlRoot, String dirName, String pictureLogo, Integer productID, String pictureGeneral, String productName, String companyName) {
		this.catalogID = catalogID;
		this.imageUrlRoot = imageUrlRoot;
		this.dirName = dirName;
		this.pictureLogo = pictureLogo;
		this.productID = productID;
		this.pictureGeneral = pictureGeneral;
		this.productName = productName;
		this.companyName = companyName;
	}
}
