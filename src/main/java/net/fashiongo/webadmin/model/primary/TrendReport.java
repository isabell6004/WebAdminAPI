package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nayeon Kim
 */
@Entity
@Table(name = "TrendReport")
public class TrendReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -977082821336060207L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("TrendReportID")
	@Column(name = "TrendReportID")
	private Integer trendReportID;
	
	@JsonProperty("Title")
	@Column(name = "Title")
	private String title;
	
	@JsonProperty("Image")
	@Column(name = "Image")
	private String image;
	
	@JsonProperty("DateFrom")
	@Column(name = "DateFrom")
	private LocalDateTime dateFrom;
	
	@JsonProperty("DateTo")
	@Column(name = "DateTo")
	private LocalDateTime dateTo;
	
	@JsonProperty("ListOrder")
	@Column(name = "ListOrder")
	private Integer listOrder;
	
	@JsonProperty("Active")
	@Column(name = "Active")
	private Boolean active;
	
	@JsonProperty("CreatedOn")
	@Column(name = "CreatedOn")
	private LocalDateTime createdOn;
	
	@JsonProperty("CreatedBy")
	@Column(name = "CreatedBy")
	private String createdBy;
	
	@JsonProperty("TRDescription")
	@Column(name = "TRDescription")
	private String tRDescription;
	
	@JsonProperty("MiniImage")
	@Column(name = "MiniImage")
	private String miniImage;
	
	@JsonProperty("CuratedType")
	@Column(name = "CuratedType")
	private Integer curatedType;
	
	@JsonProperty("SquareImage")
	@Column(name = "SquareImage")
	private String squareImage;
	
	@JsonProperty("ModifiedOn")
	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOn;
	
	@JsonProperty("ModifiedBy")
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@JsonProperty("Sticky")
	@Column(name = "Sticky")
	private Boolean sticky;
	
	@JsonProperty("KMMImage")
	@Column(name = "KMMImage")
	private String kMMImage;
	
	@JsonProperty("KMMImage1")
	@Column(name = "KMMImage1")
	private String kMMImage1;
	
	@JsonProperty("KMMImage2")
	@Column(name = "KMMImage2")
	private String kMMImage2;

	@Column(name = "ShowID")
	private Integer showId;

	@Column(name = "showScheduleId")
	private Integer showScheduleId;

	@JsonProperty("ExternalURL")
	@Column(name = "ExternalURL")
	private String externalURL;	
		
	public Integer getTrendReportID() {
		return trendReportID;
	}

	public void setTrendReportID(Integer trendReportID) {
		this.trendReportID = trendReportID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String gettRDescription() {
		return tRDescription;
	}

	public void settRDescription(String tRDescription) {
		this.tRDescription = tRDescription;
	}

	public String getMiniImage() {
		return miniImage;
	}

	public void setMiniImage(String miniImage) {
		this.miniImage = miniImage;
	}

	public Integer getCuratedType() {
		return curatedType;
	}

	public void setCuratedType(Integer curatedType) {
		this.curatedType = curatedType;
	}

	public String getSquareImage() {
		return squareImage;
	}

	public void setSquareImage(String squareImage) {
		this.squareImage = squareImage;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getSticky() {
		return sticky;
	}

	public void setSticky(Boolean sticky) {
		this.sticky = sticky;
	}

	public String getkMMImage() {
		return kMMImage;
	}

	public void setkMMImage(String kMMImage) {
		this.kMMImage = kMMImage;
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

	public Integer getShowId() { return showId; }

	public void setShowId(Integer showId) { this.showId = showId; }

	public Integer getShowScheduleId() { return showScheduleId; }

	public void setShowScheduleId(Integer showScheduleId) { this.showScheduleId = showScheduleId; }
	
	public String getExternalURL() {
		return externalURL;
	}

	public void setExternalURL(String externalURL) {
		this.externalURL = externalURL;
	}	
}
