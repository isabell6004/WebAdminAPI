package net.fashiongo.webadmin.model.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GnbVendorGroupSaveRequest {
	private final int minTitleLength = 1;
	private final String emptyTitleMessage = "Input the title.";

	private final int maxTitleLength = 24;
	private final String maxTitleLengthExceededMessage = "Max character count is " + maxTitleLength + ".";

	private final String titlePattern = "^[^\\s]+(\\s+[^\\s]+)*$";
	private final String titlePatternMismatchMessage = "Check if the title start with or end with whitespace.";

	@NotNull(message = emptyTitleMessage)
	@Length(min = minTitleLength, message = emptyTitleMessage)
	@Length(max = maxTitleLength, message = maxTitleLengthExceededMessage)
	@Pattern(regexp = titlePattern, message = titlePatternMismatchMessage)
	private String title;

	private String targetUrl;

	private int vendorGroupType;

	@JsonProperty("isAlphabeticalOrder")
	private boolean isAlphabeticalOrder;

	private List<Integer> wholeSalerIdList = new ArrayList<>();
}
