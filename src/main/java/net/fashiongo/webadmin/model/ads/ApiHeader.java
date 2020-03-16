package net.fashiongo.webadmin.model.ads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiHeader {

    @JsonProperty("isSuccessful")
    private boolean success;
    private int resultCode;
    private String resultMessage;

    public ApiHeader(ErrorCode errorCode, String resultMessage) {
        this.success = errorCode.isSuccess();
        this.resultCode = errorCode.getCode();
        this.resultMessage = resultMessage;
    }
}