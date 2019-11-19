package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VendorNameHistoryLog {
    @JsonProperty(value = "OldCompanyName")
    private String oldCompanyName;

    @JsonProperty(value = "NewCompanyName")
    private String newCompanyName;

    @JsonProperty(value = "CreatedOn")
    private LocalDateTime createdOn;
}
