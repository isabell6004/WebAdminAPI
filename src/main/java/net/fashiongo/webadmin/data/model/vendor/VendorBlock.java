package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorBlock {
    @JsonProperty(value = "BlockID")
    private Integer blockID;

    @JsonProperty(value = "BlockReasonID")
    private Integer blockReasonID;

    @JsonProperty(value = "BlockedOn")
    private LocalDateTime blockedOn;
}
