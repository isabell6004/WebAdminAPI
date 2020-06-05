package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.utility.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Getter
@Builder
public class VendorDetailDate {

	@JsonProperty("WholeSalerID")
	private Integer wholeSalerId;

	@JsonProperty("LastModifiedDateTime")
	private LocalDateTime lastModifiedDateTime;

	@JsonProperty("LastUser")
	private String lastUser;

	@JsonProperty("UserID")
	private String userID;

	@JsonProperty("ActualOpenDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime actualOpenDate;
}
