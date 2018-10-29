/**
 * 
 */
package net.fashiongo.webadmin.model.primary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author roy
 *
 */
@Entity
@Table(name = "List_SocialMedia")
public class SocialMedia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SocialMediaID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("SocialMediaID")
	@ApiModelProperty(example="0")
	private Integer socialMediaId;
	
	@NotNull
	@Column(name = "SocialMedia")
	@JsonProperty("SocialMedia")
	@ApiModelProperty(example="SNS Name")
	private String socialMedia;
	
	@Column(name = "ListOrder")
	@JsonProperty("ListOrder")
	@ApiModelProperty(hidden=true)
	private Integer listOrder;
	
	@Column(name = "Icon")
	@JsonProperty("Icon")
	@ApiModelProperty(example="fb.png")
	private String icon;
	
	@Transient
	@JsonProperty("CheckBox")
	@ApiModelProperty(hidden=true)
	private Boolean checkBox;

	public Integer getSocialMediaId() {
		return socialMediaId;
	}

	public String getSocialMedia() {
		return socialMedia;
	}

	public Integer getListOrder() {
		return listOrder;
	}

	public String getIcon() {
		return icon;
	}

	public Boolean getCheckBox() {
		return false;
	}

	public void setSocialMediaId(Integer socialMediaId) {
		this.socialMediaId = socialMediaId.equals(0) ? null : socialMediaId;;
	}

	public void setSocialMedia(String socialMedia) {
		this.socialMedia = socialMedia;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setCheckBox(Boolean checkBox) {
		this.checkBox = checkBox;
	}
	
	
}
