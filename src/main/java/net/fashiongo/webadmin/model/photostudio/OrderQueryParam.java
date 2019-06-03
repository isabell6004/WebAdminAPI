package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderQueryParam {
    private Integer pn;

    private Integer ps;

    private String orderBy;

    /* Search Date Target "PhotoshootDate", "OrderDate", "DropOffDate" */
    private String dtype;

    /* Date From */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime df;

    /* Date To */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dt;

    /* Search Type */
    private Integer qt;

    /* Search Keyword */
    private String q;

    /* Category Ids */
    private List<Integer> catids;

    /* Package Ids */
    private List<Integer> packids;

    /* Order Status Ids */
    private List<Integer> ostsids;

    private Boolean isDelayed;

    private Boolean onTime;

    private Boolean notCancelled;

    private Boolean cancelledByFG;

    private Boolean cancelledByVendor;
}
