package net.fashiongo.webadmin.model.photostudio;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import net.fashiongo.common.conversion.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CommonReportsVo {

	private static final String DATE_PATTERN_YYYYMMDD = "yyyy-MM-dd";
	
	@JsonIgnore
	@Column(name = "CheckOutDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _CheckOutDate;

	@Transient
	private String checkOutDate;
	public String getCheckOutDate() {
		return _CheckOutDate != null ? _CheckOutDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@JsonIgnore
	@Column(name = "photoshootDate")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime _photoshootDate;

	@Transient
	private String photoshootDate;
	public String getPhotoshootDate() {
		return _photoshootDate != null ? _photoshootDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN_YYYYMMDD)) : null;
	}

	@Column(name = "WholeSalerCompanyName")
	private String wholeSalerCompanyName;

	@Column(name = "totalCancelledOrders")
	private Integer totalCancelledOrders;

	@Column(name = "totalOrderAmount")
	private BigDecimal totalOrderAmount;

	@Column(name = "totalOrders")
	private Integer totalOrders;

	@Column(name = "totalPONumbers")
	private Integer totalPONumbers;

	@Column(name = "totalUnits")
	private BigDecimal totalUnits;

	@Column(name = "totalStyles")
	private Integer totalStyles;

	@Column(name = "totalColors")
	private Integer totalColors;

	@Column(name = "totalColorSet")
	private Integer totalColorSet;

	@Column(name = "totalMovie")
	private Integer totalMovie;

	@Column(name = "totalpackage1")
	private Integer totalpackage1;	
	
	@Column(name = "totalpackage2")
	private Integer totalpackage2;

	@Column(name = "totalpackage3")
	private Integer totalpackage3;

	@Column(name = "lightpink")
	private Integer lightpink;

	@Column(name = "lightblue")
	private Integer lightblue;

	@Column(name = "pastelyellow")
	private Integer pastelyellow;

	@Column(name = "avgOrder")
	private BigDecimal avgOrder;

	@Column(name = "avgOrdersDaily")
	private BigDecimal avgOrdersDaily;

	@Column(name = "avgStylesDaily")
	private BigDecimal avgStylesDaily;

	@Column(name = "avgUnitsDaily")
	private BigDecimal avgUnitsDaily;

	@Column(name = "firstTimeVendor")
	private Integer firstTimeVendor;

	@Column(name = "returningVendor")
	private Integer returningVendor;

	@Column(name = "WomenOrders")
	private Integer womenOrders;

	@Column(name = "WomenCancelled")
	private Integer womenCancelled;

	@Column(name = "WomenAmounts")
	private BigDecimal womenAmounts;

	@Column(name = "PlusWomenOrders")
	private Integer plusWomenOrders;

	@Column(name = "PlusWomenCancelled")
	private Integer plusWomenCancelled;

	@Column(name = "plusWomenAmounts")
	private BigDecimal plusWomenAmounts;

	@Column(name = "MenOrders")
	private Integer menOrders;

	@Column(name = "MenCancelled")
	private Integer menCancelled;

	@Column(name = "MenAmounts")
	private BigDecimal menAmounts;

	@Column(name = "KidsOrders")
	private Integer kidsOrders;

	@Column(name = "KidsCancelled")
	private Integer kidsCancelled;

	@Column(name = "KidsAmounts")
	private BigDecimal kidsAmounts;
}
