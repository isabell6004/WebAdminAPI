package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonReportsVo {

	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	@JsonIgnore
	@Column(name = "CheckOutDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _CheckOutDate;
	public LocalDateTime get_CheckOutDate() {
		return _CheckOutDate;
	}

	public void set_CheckOutDate(LocalDateTime _CheckOutDate) {
		this._CheckOutDate = _CheckOutDate;
	}

	@Transient
	private String checkOutDate;
	public String getCheckOutDate() {
		return _CheckOutDate != null ? _CheckOutDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	@JsonIgnore
	@Column(name = "photoshootDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _photoshootDate;
	public LocalDateTime get_photoshootDate() {
		return _photoshootDate;
	}

	public void set_photoshootDate(LocalDateTime _photoshootDate) {
		this._photoshootDate = _photoshootDate;
	}

	@Transient
	private String photoshootDate;
	public String getPhotoshootDate() {
		return _photoshootDate != null ? _photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@Column(name = "WholeSalerCompanyName")
	private String wholeSalerCompanyName;
	public String getWholeSalerCompanyName() {
		return wholeSalerCompanyName;
	}

	public void setWholeSalerCompanyName(String wholeSalerCompanyName) {
		this.wholeSalerCompanyName = wholeSalerCompanyName;
	}

	@Column(name = "totalCancelledOrders")
	private Integer totalCancelledOrders;
	public Integer getTotalCancelledOrders() {
		return totalCancelledOrders;
	}

	public void setTotalCancelledOrders(Integer totalCancelledOrders) {
		this.totalCancelledOrders = totalCancelledOrders;
	}

	@Column(name = "totalOrderAmount")
	private BigDecimal totalOrderAmount;
	public BigDecimal getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	@Column(name = "totalOrders")
	private Integer totalOrders;
	public Integer getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Integer totalOrders) {
		this.totalOrders = totalOrders;
	}
	
	@Column(name = "totalPONumbers")
	private Integer totalPONumbers;
	public Integer getTotalPONumbers() {
		return totalPONumbers;
	}

	public void setTotalPONumbers(Integer totalPONumbers) {
		this.totalPONumbers = totalPONumbers;
	}

	@Column(name = "totalUnits")
	private BigDecimal totalUnits;
	public BigDecimal getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(BigDecimal totalUnits) {
		this.totalUnits = totalUnits;
	}

	@Column(name = "totalStyles")
	private Integer totalStyles;
	public Integer getTotalStyles() {
		return totalStyles;
	}

	public void setTotalStyles(Integer totalStyles) {
		this.totalStyles = totalStyles;
	}

	@Column(name = "totalColors")
	private Integer totalColors;
	public Integer getTotalColors() {
		return totalColors;
	}

	public void setTotalColors(Integer totalColors) {
		this.totalColors = totalColors;
	}

	@Column(name = "totalColorSet")
	private Integer totalColorSet;
	public Integer getTotalColorSet() {
		return totalColorSet;
	}

	public void setTotalColorSet(Integer totalColorSet) {
		this.totalColorSet = totalColorSet;
	}

	@Column(name = "totalMovie")
	private Integer totalMovie;
	public Integer getTotalMovie() {
		return totalMovie;
	}

	public void setTotalMovie(Integer totalMovie) {
		this.totalMovie = totalMovie;
	}

	@Column(name = "totalpackage1")
	private Integer totalpackage1;	
	
	public Integer getTotalpackage1() {
		return totalpackage1;
	}

	public void setTotalpackage1(Integer totalpackage1) {
		this.totalpackage1 = totalpackage1;
	}

	@Column(name = "totalpackage2")
	private Integer totalpackage2;
	public Integer getTotalpackage2() {
		return totalpackage2;
	}

	public void setTotalpackage2(Integer totalpackage2) {
		this.totalpackage2 = totalpackage2;
	}

	@Column(name = "totalpackage3")
	private Integer totalpackage3;
	public Integer getTotalpackage3() {
		return totalpackage3;
	}

	public void setTotalpackage3(Integer totalpackage3) {
		this.totalpackage3 = totalpackage3;
	}

	@Column(name = "lightpink")
	private Integer lightpink;
	public Integer getLightpink() {
		return lightpink;
	}

	public void setLightpink(Integer lightpink) {
		this.lightpink = lightpink;
	}

	@Column(name = "lightblue")
	private Integer lightblue;
	public Integer getLightblue() {
		return lightblue;
	}

	public void setLightblue(Integer lightblue) {
		this.lightblue = lightblue;
	}

	@Column(name = "pastelyellow")
	private Integer pastelyellow;
	public Integer getPastelyellow() {
		return pastelyellow;
	}

	public void setPastelyellow(Integer pastelyellow) {
		this.pastelyellow = pastelyellow;
	}	
	
	@Column(name = "avgOrder")
	private BigDecimal avgOrder;
	public BigDecimal getAvgOrder() {
		return avgOrder;
	}

	public void setAvgOrder(BigDecimal avgOrder) {
		this.avgOrder = avgOrder;
	}

	@Column(name = "avgOrdersDaily")
	private BigDecimal avgOrdersDaily;
	public BigDecimal getAvgOrdersDaily() {
		return avgOrdersDaily;
	}

	public void setAvgOrdersDaily(BigDecimal avgOrdersDaily) {
		this.avgOrdersDaily = avgOrdersDaily;
	}

	@Column(name = "avgStylesDaily")
	private BigDecimal avgStylesDaily;
	public BigDecimal getAvgStylesDaily() {
		return avgStylesDaily;
	}

	public void setAvgStylesDaily(BigDecimal avgStylesDaily) {
		this.avgStylesDaily = avgStylesDaily;
	}

	@Column(name = "avgUnitsDaily")
	private BigDecimal avgUnitsDaily;
	public BigDecimal getAvgUnitsDaily() {
		return avgUnitsDaily;
	}

	public void setAvgUnitsDaily(BigDecimal avgUnitsDaily) {
		this.avgUnitsDaily = avgUnitsDaily;
	}

	@Column(name = "firstTimeVendor")
	private Integer firstTimeVendor;
	public Integer getFirstTimeVendor() {
		return firstTimeVendor;
	}

	public void setFirstTimeVendor(Integer firstTimeVendor) {
		this.firstTimeVendor = firstTimeVendor;
	}

	@Column(name = "returningVendor")
	private Integer returningVendor;
	public Integer getReturningVendor() {
		return returningVendor;
	}

	public void setReturningVendor(Integer returningVendor) {
		this.returningVendor = returningVendor;
	}

	@Column(name = "WomenOrders")
	private Integer womenOrders;
	public Integer getWomenOrders() {
		return womenOrders;
	}

	public void setWomenOrders(Integer womenOrders) {
		this.womenOrders = womenOrders;
	}

	@Column(name = "WomenCancelled")
	private Integer womenCancelled;
	public Integer getWomenCancelled() {
		return womenCancelled;
	}

	public void setWomenCancelled(Integer womenCancelled) {
		this.womenCancelled = womenCancelled;
	}

	@Column(name = "WomenAmounts")
	private BigDecimal womenAmounts;
	public BigDecimal getWomenAmounts() {
		return womenAmounts;
	}

	public void setWomenAmounts(BigDecimal womenAmounts) {
		this.womenAmounts = womenAmounts;
	}

	@Column(name = "PlusWomenOrders")
	private Integer plusWomenOrders;
	public Integer getPlusWomenOrders() {
		return plusWomenOrders;
	}

	public void setPlusWomenOrders(Integer plusWomenOrders) {
		this.plusWomenOrders = plusWomenOrders;
	}

	@Column(name = "PlusWomenCancelled")
	private Integer plusWomenCancelled;
	public Integer getPlusWomenCancelled() {
		return plusWomenCancelled;
	}

	public void setPlusWomenCancelled(Integer plusWomenCancelled) {
		this.plusWomenCancelled = plusWomenCancelled;
	}

	@Column(name = "plusWomenAmounts")
	private BigDecimal plusWomenAmounts;
	public BigDecimal getPlusWomenAmounts() {
		return plusWomenAmounts;
	}

	public void setPlusWomenAmounts(BigDecimal plusWomenAmounts) {
		this.plusWomenAmounts = plusWomenAmounts;
	}

	@Column(name = "MenOrders")
	private Integer menOrders;
	public Integer getMenOrders() {
		return menOrders;
	}

	public void setMenOrders(Integer menOrders) {
		this.menOrders = menOrders;
	}

	@Column(name = "MenCancelled")
	private Integer menCancelled;
	public Integer getMenCancelled() {
		return menCancelled;
	}

	public void setMenCancelled(Integer menCancelled) {
		this.menCancelled = menCancelled;
	}

	@Column(name = "MenAmounts")
	private BigDecimal menAmounts;
	public BigDecimal getMenAmounts() {
		return menAmounts;
	}

	public void setMenAmounts(BigDecimal menAmounts) {
		this.menAmounts = menAmounts;
	}

	@Column(name = "KidsOrders")
	private Integer kidsOrders;
	public Integer getKidsOrders() {
		return kidsOrders;
	}

	public void setKidsOrders(Integer kidsOrders) {
		this.kidsOrders = kidsOrders;
	}

	@Column(name = "KidsCancelled")
	private Integer kidsCancelled;
	public Integer getKidsCancelled() {
		return kidsCancelled;
	}

	public void setKidsCancelled(Integer kidsCancelled) {
		this.kidsCancelled = kidsCancelled;
	}

	@Column(name = "KidsAmounts")
	private BigDecimal kidsAmounts;
	public BigDecimal getKidsAmounts() {
		return kidsAmounts;
	}

	public void setKidsAmounts(BigDecimal kidsAmounts) {
		this.kidsAmounts = kidsAmounts;
	}
}
