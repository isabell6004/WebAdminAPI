package net.fashiongo.webadmin.model.photostudio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoCalendarResponse {
	private boolean available;

	private double availableUnits;

	private int bookedCnt;

	private int calendarID;

	private String dateName;

	private boolean disabled;

	private Boolean isDelayed;

	private Boolean isHoliday;

	private Boolean isModelShot;

	private double maxUnitPerDay;

	private double minUnit;

	private String theDate;
}
