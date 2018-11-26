package net.fashiongo.webadmin.model.photostudio;

import javax.persistence.Column;

public class ReportCsvDaily {

	@Column(name = "CheckOutDate")
	private String checkOutDate;
	public String getCheckOutDate() {
		return checkOutDate;
	}

	@Column(name = "WholeSalerID")
	private Integer wholeSalerID;
	public Integer getWholeSalerID() {
		return wholeSalerID;
	}

	@Column(name = "WholeSalerCompanyName")
	private String wholeSalerCompanyName;
	public String getWholeSalerCompanyName() {
		return wholeSalerCompanyName;
	}

	@Column(name = "CategoryID")
	private Integer categoryID;
	public Integer getCategoryID() {
		return categoryID;
	}

	@Column(name = "PackageID")
	private Integer packageID;
	public Integer getPackageID() {
		return packageID;
	}

	@Column(name = "FullStyle")
	private String fullStyle;
	public String getFullStyle() {
		return fullStyle;
	}

	@Column(name = "totalStyles")
	private Integer totalStyles;
	public Integer getTotalStyles() {
		return totalStyles;
	}

	@Column(name = "Total Add'l Color")
	private Integer totalColors;
	public Integer getTotalColors() {
		return totalColors;
	}

	@Column(name = "Total Add'l ColorSet")
	private Integer totalColorSet;
	public Integer getTotalColorSet() {
		return totalColorSet;
	}

	@Column(name = "Total Movie Clip")
	private Integer totalMovie;
	public Integer getTotalMovie() {
		return totalMovie;
	}

	@Column(name = "PickupDate")
	private String pickupDate;
	public String getPickupDate() {
		return pickupDate;
	}

	@Column(name = "BookingID")
	private Integer bookingID;
	public Integer getBookingID() {
		return bookingID;
	}

	@Column(name = "ModelName")
	private String ModelName;
	public String getModelName() {
		return ModelName;
	}

	@Column(name = "Promo")
	private String promo;
	public String getPromo() {
		return promo;
	}
}
