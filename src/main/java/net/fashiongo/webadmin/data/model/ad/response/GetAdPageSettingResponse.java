package net.fashiongo.webadmin.data.model.ad.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fashiongo.webadmin.data.model.ad.AdPage;
import net.fashiongo.webadmin.data.model.ad.AdPageSpot;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAdPageSettingResponse {
    @JsonProperty("Table")
    private List<AdPageSpot> adPageSpotList;

    @JsonProperty("Table1")
    private List<AdPage> adPageList;
}
