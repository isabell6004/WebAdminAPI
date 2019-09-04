package net.fashiongo.webadmin.data.model.sitemgmt.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.sitemgmt.BodySizeInfo;
import net.fashiongo.webadmin.data.model.sitemgmt.ColorListInfo;
import net.fashiongo.webadmin.data.model.sitemgmt.FabricInfo;
import net.fashiongo.webadmin.data.model.sitemgmt.LengthInfo;
import net.fashiongo.webadmin.data.model.sitemgmt.PatternInfo;
import net.fashiongo.webadmin.data.model.sitemgmt.StyleInfo;

import java.util.List;

@Getter
@Builder
public class GetProductAttributesTotalResponse {
    @JsonProperty("Table")
    private List<PatternInfo> patternInfolist;

    @JsonProperty("Table1")
    private List<LengthInfo> lengthInfolist;

    @JsonProperty("Table2")
    private List<StyleInfo> styleInfolist;

    @JsonProperty("Table3")
    private List<FabricInfo> fabricInfolist;

    @JsonProperty("Table4")
    private List<BodySizeInfo> bodySizeInfolist;

    @JsonProperty("Table5")
    private List<ColorListInfo> colorListInfolist;
}
