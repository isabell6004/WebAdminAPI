package net.fashiongo.webadmin.model.pojo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GnbCollectionSaveRequest {

	private final int minNameLength = 1;
	private final String emptyNameMessage = "Input the Name.";

	private final int maxNameLength = 50;
	private final String maxNameLengthExceededMessage = "Max character count is " + maxNameLength + ".";

	private final String namePattern = "^[^\\s]+(\\s+[^\\s]+)*$";
	private final String namePatternMismatchMessage = "Check if the name start with or end with whitespace.";

	@NotNull(message = emptyNameMessage)
	@Length(min = minNameLength, message = emptyNameMessage)
	@Length(max = maxNameLength, message = maxNameLengthExceededMessage)
	@Pattern(regexp = namePattern, message = namePatternMismatchMessage)
	private String name;

	private String targetUrl;

	@JsonProperty("isActive")
	private boolean isActive;
	
	@JsonProperty("sortNo")
	private Integer sortNo;
}
