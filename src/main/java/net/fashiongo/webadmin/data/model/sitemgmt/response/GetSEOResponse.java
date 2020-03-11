package net.fashiongo.webadmin.data.model.sitemgmt.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.sitemgmt.SEO;

@Getter
@Builder
public class GetSEOResponse {

    @JsonProperty("Table")
    private List<Total> recCnt;;

    @JsonProperty("Table1")
    private List<SEO> seo;
	
}
