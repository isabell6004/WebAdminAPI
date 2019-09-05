package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeaturedItemCount {
    @JsonProperty("FeaturedItemDate")
    private LocalDateTime featuredItemDate;

    @JsonProperty("TotalCount")
    private Long totalCount;

    @JsonProperty("ActiveSum")
    private Integer activeSum;

    @JsonProperty("NullTotal")
    private Integer nullTotal;

    @JsonProperty("BestItemCount")
    private Integer bestItemCount;
}
