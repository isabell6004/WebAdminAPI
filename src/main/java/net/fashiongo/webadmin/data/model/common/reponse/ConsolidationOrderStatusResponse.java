package net.fashiongo.webadmin.data.model.common.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.common.CodeOrderStatus;

import java.util.List;

@Getter
@Builder
public class ConsolidationOrderStatusResponse {

	@JsonProperty("consolidationOrderStatus")
	private List<CodeOrderStatus> consolidationOrderStatus;
}
