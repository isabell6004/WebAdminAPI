package net.fashiongo.webadmin.model.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GnbBannerActiveRequest {
	@JsonProperty("isActive")
	private boolean isActive;
}
