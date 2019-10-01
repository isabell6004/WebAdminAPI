package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

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
	private LocalDateTime actualOpenDate;
}
