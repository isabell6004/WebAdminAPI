package net.fashiongo.webadmin.model.photostudio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Builder
public class ModelUnit {
    private BigDecimal availableUnit;

    private BigDecimal bookedUnit;

    @JsonProperty("calendarID")
    private Integer calendarId;

    @JsonProperty("modelID")
    private Integer modelId;

    private String modelName;

    @JsonProperty("modelScheduleID")
    private Integer modelScheduleId;

    public static ModelUnit of(MapPhotoCalendarModel map) {
        return builder()
                .availableUnit(map.getAvailableUnit())
                .bookedUnit(Optional.ofNullable(map.getPhotoBooking())
                        .map(photoBookings -> photoBookings.stream()
                                .map(PhotoBooking::getBookedUnit)
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                        .orElse(null))
                .calendarId(map.getCalendarID())
                .modelId(Optional.ofNullable(map.getPhotoModel())
                        .map(PhotoModel::getModelID)
                        .orElse(null))
                .modelName(Optional.ofNullable(map.getPhotoModel())
                        .map(PhotoModel::getModelName)
                        .orElse(null))
                .modelScheduleId(map.getModelScheduleID())
                .build();
    }
}
