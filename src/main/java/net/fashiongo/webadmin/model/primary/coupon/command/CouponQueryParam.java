package net.fashiongo.webadmin.model.primary.coupon.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponQueryParam {

    @JsonProperty(value = "pn", required = false, defaultValue = "1")
    private int pn;
    @JsonProperty(value = "ps", required = false, defaultValue = "10")
    private int ps;
}
