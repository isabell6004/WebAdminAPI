package net.fashiongo.webadmin.model.primary;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author DAHYE
 *
 */
@Entity
@Data
@Table(name = "tblRetailerRating")
public class RetailerRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RetailerRatingID")
	@JsonProperty("RetailerRatingID") 
	private Integer retailerRatingID;
	
	@Column(name="OrderID") 
	@JsonProperty("OrderID") 
	private Integer orderID;
	
	@Column(name="WholeSalerID") 
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	
	@Column(name="RetailerID")
	@JsonProperty("RetailerID") 
	private Integer retailerID;
	
	@Column(name="Comment") 
	@JsonProperty("Comment") 
	private String comment;
	
	@Column(name="Score")
	@JsonProperty("Score") 
	private Integer score;
	
	@Column(name="Active") 
	@JsonProperty("Active")
	private Boolean active;
	
	@Column(name="CreatedOn") 
	@JsonProperty("CreatedOn") 
	private LocalDateTime createdOn;
}
