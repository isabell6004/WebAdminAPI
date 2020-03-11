package net.fashiongo.webadmin.service.externalutil.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseHeader {

    @JsonProperty("isSuccessful")
    private boolean isSuccessful;
    private int resultCode;
    private String resultMessage;
}
