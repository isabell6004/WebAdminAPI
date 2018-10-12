package net.fashiongo.webadmin.model.primary;

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
@Table(name = "Code_BodySize")
public class CodeBodySize {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("BodySizeID")
	@Column(name = "BodySizeID")
	private Integer bodySizeID;
	
	@JsonProperty("BodySizeName")
	@Column(name = "BodySizeName")
	private String bodySizeName;
	
	public Integer getBodySizeID() {
		return bodySizeID;
	}

	public void setBodySizeID(Integer bodySizeID) {
		this.bodySizeID = bodySizeID;
	}

	public String getBodySizeName() {
		return bodySizeName;
	}

	public void setBodySizeName(String bodySizeName) {
		this.bodySizeName = bodySizeName;
	}
}
