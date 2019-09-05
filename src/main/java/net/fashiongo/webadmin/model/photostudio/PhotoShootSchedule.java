package net.fashiongo.webadmin.model.photostudio;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PhotoShootSchedule {
    private LocalDateTime checkOutDate;
    private LocalDateTime dropOffDate;
    private LocalDateTime prepDate;
    private LocalDateTime retouchDate;
    private LocalDateTime uploadDate;
    private LocalDateTime pickupDate;
}
