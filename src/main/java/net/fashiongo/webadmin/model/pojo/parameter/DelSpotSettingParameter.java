package net.fashiongo.webadmin.model.pojo.parameter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
* @author Nayeon Kim
*/
public class DelSpotSettingParameter {
    @ApiModelProperty(required = false, example="83")
    @JsonProperty("SpotID")
    private String spotID;

    public Integer getSpotID() {
        return StringUtils.isEmpty(spotID) ? 0 : Integer.parseInt(spotID);
    }
}
