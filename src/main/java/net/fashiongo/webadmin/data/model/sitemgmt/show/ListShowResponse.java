package net.fashiongo.webadmin.data.model.sitemgmt.show;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;
import net.fashiongo.webadmin.data.entity.primary.show.ListShowWithScheduleEntity;
import org.springframework.util.CollectionUtils;

@Value
@Builder
public class ListShowResponse {
	@JsonProperty("ShowID")
	private Integer showId;

	@JsonProperty("ShowName")
	private String showName;

	@JsonProperty("Location")
	private String location;

	@JsonProperty("Url")
	private String url;

	@JsonProperty("Active")
	private boolean active;

	@JsonProperty("LogoFileName")
	private String logoFileName;

	@JsonProperty("ShowCode")
	private String showCode;

	@JsonProperty("DeleteFlag")
	private int deleteFlag;

	@JsonProperty("row")
	private long rowNumber;

	public static ListShowResponse convertFrom(ListShowWithScheduleEntity entity, long rowNumber) {
		return ListShowResponse.builder()
				.showId(entity.getShowId())
				.showName(entity.getShowName())
				.location(entity.getLocation())
				.url(entity.getUrl())
				.active(entity.isActive())
				.logoFileName(entity.getLogoFileName())
				.showCode(entity.getShowCode())
				.deleteFlag(CollectionUtils.isEmpty(entity.getShowSchedule()) ? 1 : 0)
				.rowNumber(rowNumber)
				.build();
	}
}
