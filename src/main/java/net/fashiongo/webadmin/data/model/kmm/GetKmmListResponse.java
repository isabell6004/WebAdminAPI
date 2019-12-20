package net.fashiongo.webadmin.data.model.kmm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;

import java.util.List;

@Getter
@Builder
public class GetKmmListResponse {
    @JsonProperty("Table")
    private List<Total> recCnt;

    @JsonProperty("Table1")
    private List<KmmListDetail> kmmList;
}
