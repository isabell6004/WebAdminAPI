package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Transient;

import net.fashiongo.common.conversion.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DailySummaryVo {

	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
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
	
	@Column(name = "totalOrders")
	private Integer totalOrders;
	public Integer getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Integer totalOrders) {
		this.totalOrders = totalOrders;
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

}
