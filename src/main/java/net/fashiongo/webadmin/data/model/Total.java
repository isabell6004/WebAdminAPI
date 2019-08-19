package net.fashiongo.webadmin.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Total {

	@JsonProperty("RecCnt")
	private Integer recCnt;
}
