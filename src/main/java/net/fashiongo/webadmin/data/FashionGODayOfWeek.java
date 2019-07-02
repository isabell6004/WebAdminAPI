package net.fashiongo.webadmin.data;

import org.springframework.web.context.request.FacesRequestAttributes;

import java.time.DayOfWeek;

public enum FashionGODayOfWeek {

	/**
	 * The singleton instance for the day-of-week of Sunday.
	 * This has the numeric value of {@code 1}.
	 */
	SUNDAY,
	/**
	 * The singleton instance for the day-of-week of Monday.
	 * This has the numeric value of {@code 2}.
	 */
	MONDAY,
	/**
	 * The singleton instance for the day-of-week of Tuesday.
	 * This has the numeric value of {@code 3}.
	 */
	TUESDAY,
	/**
	 * The singleton instance for the day-of-week of Wednesday.
	 * This has the numeric value of {@code 4}.
	 */
	WEDNESDAY,
	/**
	 * The singleton instance for the day-of-week of Thursday.
	 * This has the numeric value of {@code 5}.
	 */
	THURSDAY,
	/**
	 * The singleton instance for the day-of-week of Friday.
	 * This has the numeric value of {@code 6}.
	 */
	FRIDAY,
	/**
	 * The singleton instance for the day-of-week of Saturday.
	 * This has the numeric value of {@code 7}.
	 */
	SATURDAY;

	public static FashionGODayOfWeek of(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
			case MONDAY:
				return FashionGODayOfWeek.MONDAY;
			case TUESDAY:
				return FashionGODayOfWeek.TUESDAY;
			case WEDNESDAY:
				return FashionGODayOfWeek.WEDNESDAY;
			case THURSDAY:
				return FashionGODayOfWeek.THURSDAY;
			case FRIDAY:
				return FashionGODayOfWeek.FRIDAY;
			case SATURDAY:
				return FashionGODayOfWeek.SATURDAY;
			case SUNDAY:
				return FashionGODayOfWeek.SUNDAY;
			default:
				return null;
		}
	}

//	@return the day-of-week, from 1 (Sunday) to 7 (Saturday)
	public int getValue() {
		return ordinal() + 1;
	}
}
