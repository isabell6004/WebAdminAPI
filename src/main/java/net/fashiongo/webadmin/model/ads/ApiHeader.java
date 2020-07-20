package net.fashiongo.webadmin.model.ads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiHeader {

    @JsonProperty("isSuccessful")
    private boolean success;
    private int resultCode;
    private String resultMessage;

    private ApiHeader() {
        // create private default constructor because object mapper needs the default constructor to deserialize.
    }

    public ApiHeader(ErrorCode errorCode, String resultMessage) {
        this.success = errorCode.isSuccess();
        this.resultCode = errorCode.getCode();
        this.resultMessage = resultMessage;
    }
}
