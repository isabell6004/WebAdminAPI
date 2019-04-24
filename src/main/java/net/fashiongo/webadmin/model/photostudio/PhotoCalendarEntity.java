package net.fashiongo.webadmin.model.photostudio;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Photo_Calendar")
@Getter
@Setter
public class PhotoCalendarEntity {

	@Id
	@Column(name = "CalendarID")
	@Setter(AccessLevel.NONE)
	private Integer calendarID;

	@Column(name = "TheDate")
	private LocalDateTime theDate;

	@Column(name = "Available")
	private Boolean available;

	@Column(name = "IsHoliday", insertable = false, updatable = false)
	private Boolean isHoliday;

	@Column(name = "IsModelShot", insertable = false, updatable = false)
	private Boolean isModelShot;

	@Column(name = "DateName", insertable = false, updatable = false)
	private String dateName;

	@Column(name = "MaxUnitPerDay")
	private BigDecimal maxUnitPerDay;

	@Column(name = "CreatedOn", updatable = false)
	private LocalDateTime createdOnDate;

	@Column(name = "CreatedBy")
	private String createdBy;

	@Column(name = "ModifiedOn")
	private LocalDateTime modifiedOnDate;

	@Column(name = "ModifiedBY")
	private String modifiedBY;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "CalendarID", referencedColumnName = "CalendarID")
	private Set<MapPhotoCalendarModel> mapPhotoCalendarModel;
}
