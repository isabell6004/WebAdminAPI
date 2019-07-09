package net.fashiongo.webadmin.model.pojo.sitemgmt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.fashiongo.webadmin.model.primary.show.ListShow;

import java.util.List;

@Getter
@Setter
@Builder
public class ShowInfoDto {

    private Integer showId;

    private String showName;

    private Boolean active;

    private List<ShowScheduleDto> schedules;

    public static ShowInfoDto build(ListShow show, List<ShowScheduleDto> showSchedules) {

        return builder().showId(show.getShowID())
                .showName(show.getShowName())
                .active(show.getActive())
                .schedules(showSchedules)
                .build();
    }
}
