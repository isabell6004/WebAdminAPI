package net.fashiongo.webadmin.model.pojo.vendor.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;

public class SetVendorUnBlockParameter {

    @JsonProperty("WholeSalerID")
    private Integer wholeSalerID;

    @JsonProperty("TypeCode")
    private Integer typeCode;

    public Integer getWholeSalerID() {
        return wholeSalerID;
    }

    public void setWholeSalerID(Integer wholeSalerID) {
        this.wholeSalerID = wholeSalerID;
    }

    public Integer getTypeCode() {
        return StringUtils.isEmpty(typeCode) ? 0 : typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }
}
