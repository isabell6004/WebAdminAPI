/**
 * 
 */
package net.fashiongo.webadmin.model.pojo.sitemgmt.parameter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author roy
 *
 */
public class DelSocialMediaParameter {
	@ApiModelProperty(required = true, example="8,9")
	@JsonProperty("SocialMediaIDs")
	private String socialMediaIds;

	public String getSocialMediaIds() {
		return socialMediaIds;
	}

	public void setSocialMediaIds(String socialMediaIds) {
		this.socialMediaIds = socialMediaIds;
	}

}
