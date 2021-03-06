package net.fashiongo.webadmin.data.model.vendor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;

import java.sql.Timestamp;

@Getter
@Setter
public class VendorBasicInfo {
    private VendorEntity vendor;

    @JsonProperty(value = "IsLockedOut")
    private Boolean isLockedOut;

    @JsonProperty(value = "LastLockoutDate")
    private Timestamp lastLockoutDate;

    @JsonProperty(value = "IsLockedOut2")
    private Long isLockedOut2;

    @JsonProperty(value = "elambsuser")
    private Long elambsuser;

    public VendorBasicInfo() {
    }

    public VendorBasicInfo(Boolean isLockedOut, Timestamp lastLockoutDate, Long isLockedOut2, Long elambsuser) {
        this.isLockedOut = isLockedOut;
        this.lastLockoutDate = lastLockoutDate;
        this.isLockedOut2 = isLockedOut2;
        this.elambsuser = elambsuser;
    }
}
