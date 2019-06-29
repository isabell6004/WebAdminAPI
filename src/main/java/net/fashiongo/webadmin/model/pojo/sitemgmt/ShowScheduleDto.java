package net.fashiongo.webadmin.model.pojo.sitemgmt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.show.ShowSchedule;

@Getter
@Setter
@Builder
public class ShowScheduleDto {

    private Integer showScheduleId;

    private Integer showId;

    private String dateFrom;

    private String dateTo;

    private Boolean active;

    public static ShowScheduleDto build(ShowSchedule schedule) {

        return builder().showScheduleId(schedule.getShowScheduleID())
                .showId(schedule.getShowID())
                .dateFrom(schedule.getDateFrom() != null ? schedule.getDateFrom().toString() : null)
                .dateTo(schedule.getDateTo() != null ? schedule.getDateTo().toString() : null)
                .active(schedule.getActive())
                .build();
    }
}
