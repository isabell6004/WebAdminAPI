package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FeaturedItemCount {
    public FeaturedItemCount() {}

    public FeaturedItemCount(LocalDateTime featuredItemDate, Long totalCount, Integer activeSum, Integer nullTotal, Integer bestItemCount) {
        this.featuredItemDate = LocalDate.from(featuredItemDate);
        this.totalCount = totalCount;
        this.activeSum = activeSum;
        this.nullTotal = nullTotal;
        this.bestItemCount = bestItemCount;
    }

    @JsonProperty("FeaturedItemDate")
    private LocalDate featuredItemDate;

    @JsonProperty("TotalCount")
    private Long totalCount;

    @JsonProperty("ActiveSum")
    private Integer activeSum;

    @JsonProperty("NullTotal")
    private Integer nullTotal;

    @JsonProperty("BestItemCount")
    private Integer bestItemCount;
}
