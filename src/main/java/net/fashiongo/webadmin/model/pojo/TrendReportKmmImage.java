package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrendReportKmmImage {
	@JsonProperty("SquareImage")
	private String squareImage;
	@JsonProperty("Image")
	private String image;
	@JsonProperty("MiniImage")
	private String miniImage;
	@JsonProperty("KMMImage1")
	private String kMMImage1;
	@JsonProperty("KMMImage2")
	private String kMMImage2;
	
	public TrendReportKmmImage() {}
	public TrendReportKmmImage(String squareImage, String image, String miniImage, String kMMImage1, String kMMImage2) {
		this.squareImage = squareImage;
		this.image = image;
		this.miniImage = miniImage;
		this.kMMImage1 = kMMImage1;
		this.kMMImage2 = kMMImage2;
	}
	
	public String getSquareImage() {
		return squareImage;
	}
	public void setSquareImage(String squareImage) {
		this.squareImage = squareImage;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMiniImage() {
		return miniImage;
	}
	public void setMiniImage(String miniImage) {
		this.miniImage = miniImage;
	}
	public String getkMMImage1() {
		return kMMImage1;
	}
	public void setkMMImage1(String kMMImage1) {
		this.kMMImage1 = kMMImage1;
	}
	public String getkMMImage2() {
		return kMMImage2;
	}
	public void setkMMImage2(String kMMImage2) {
		this.kMMImage2 = kMMImage2;
	}
}
